package com.gh.boot.jasperreports.service.imp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.gh.boot.common.core.data.AppResource;
import com.gh.boot.common.core.exception.ExceptionCodeEnum;
import com.gh.boot.common.core.util.ResourceUtil;
import com.gh.boot.jasperreports.data.dto.ReportBuilderDTO;
import com.gh.boot.jasperreports.data.dto.ReportDataDTO;
import com.gh.boot.jasperreports.enums.DocType;
import com.gh.boot.jasperreports.service.IReportService;
import com.gh.boot.jasperreports.view.ReportView;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
* description：机构service
 */
@Service
public class ReportServiceImpl implements IReportService {

	protected final String path = "prints";
	protected final String suffix = ".jrxml";
	protected final String subReportDir = "SUBREPORT_DIR";//父报表加载子报表传递的子报表所在绝对路径

	@Value("${jasperreports.px-image-server-address:}")
	private String pxImageServerAddress;//报表px图片所在服务地址，用于报表px图片地址修正

	@Resource
	private Validator validator;

	@Override
	public ModelAndView buildReport(ReportBuilderDTO reportBuilderDTO) {
		//参数校验
		Set<ConstraintViolation<Object>> validate = validator.validate(reportBuilderDTO);
		for (ConstraintViolation<Object> model : validate) {
			throw ExceptionCodeEnum.BAD_REQUEST.newException(model.getMessage());
		}
		try{
//			String tokenValue = parameters.get(token)+"";
//			if(VTools.StringIsEmpty(tokenValue)){
//				throw new BusinessException("当前链接已失效，请尝试重新登陆");
//			}
//			//重新设置authentication
//			OAuth2Authentication oAuth2Auth = redisTokenStore.readAuthentication(tokenValue);
//			Authentication authentication = oAuth2Auth.getUserAuthentication();
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//			if(SecurityUtils.getMsfUser() == null){
//				throw new BusinessException("当前链接已失效，请尝试重新登陆");
//			}
			String templateFileName = reportBuilderDTO.getTemplateFileName();
			DocType docType = reportBuilderDTO.getDocType();
			String docTypeName = docType == null ? DocType.HTML.getTypeName() : docType.getTypeName();
			Boolean isInline = reportBuilderDTO.getInline();
			String targetFileName = reportBuilderDTO.getTargetFileName();
			targetFileName = StrUtil.isEmpty(targetFileName) ? DatePattern.PURE_DATETIME_FORMAT.format(new Date()) : targetFileName;
			String reportFilePath = path+"/"+templateFileName+suffix;
			if(StrUtil.isEmpty(docTypeName)){
				docTypeName = DocType.HTML.getTypeName();
			}
			isInline = isInline == null ? true : isInline;
			AppResource appResource = getReportResource(reportFilePath,templateFileName);
			InputStream inputStream = appResource.getInputStream();
			JasperDesign jd = JRXmlLoader.load(inputStream);
			addDefaultStyle(jd,getBaseStyle());
			jd.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			fixMissingParamAndField(jd);
			Map<String, Object> map = new HashMap<>();
			if(reportBuilderDTO.getData()!=null){
				ReportDataDTO reportDataDTO = reportBuilderDTO.getData();
				map = BeanUtil.beanToMap(reportDataDTO);
				List ds = reportDataDTO.getList();
				if(CollectionUtil.isNotEmpty(ds)){
					map.remove("list");
					map.put("ds", recursiveGetDataSource(ds));
				}
			}
			//子报表设置parameter:subReportDir
			if(StrUtil.isNotEmpty(reportBuilderDTO.getSubReportDir())){
				map.remove("subReportDir");
				map.put(reportBuilderDTO.getSubReportDir(),appResource.getParentPath());
			}else{
				map.put(subReportDir,appResource.getParentPath());
			}
			return new ModelAndView(new ReportView(jd,docTypeName,isInline,targetFileName,pxImageServerAddress), map);
		}catch (Exception e){
			e.printStackTrace();
			throw ExceptionCodeEnum.INTERNAL_SERVER_ERROR.newException(e.getMessage(),e);
		}
	}

	/**
	 * @desc 递归获取数据
	 * @date 2023/5/4 10:32
	 * @author tianma
	 */
	private JRMapCollectionDataSource recursiveGetDataSource(List list){
		if(CollectionUtil.isEmpty(list)){
			return new JRMapCollectionDataSource(new ArrayList<>());
		}
		return new JRMapCollectionDataSource((Collection<Map<String, ?>>) list.stream().map(d -> {
			Map<String, Object> map = BeanUtil.beanToMap(d,false,true);
			if(MapUtil.isNotEmpty(map)){
				map.keySet().forEach(k -> {
					if(map.get(k) instanceof List){
						map.put(k,recursiveGetDataSource((List) map.get(k)));
					}
				});
			}
			return map;
		}).collect(Collectors.toList()));
	}

	/**
	 * @desc 获取报表jrxml文件
	 * @date 2023/4/28 15:19
	 * @author tianma
	 */
	private AppResource getReportResource(String reportFilePath, String reportName){
		StringBuilder errorMsg = new StringBuilder();
		try{
			AppResource appResource = ResourceUtil.getResource(reportFilePath);
			if(appResource == null){
				reportFilePath = path+"/"+ reportName.substring(0, 1).toLowerCase() + reportName.substring(1)+suffix;
				appResource = ResourceUtil.getResource(reportFilePath);
				if(appResource == null){
					errorMsg.append("未找到报表配置文件[").append(reportFilePath);
					reportFilePath = path+"/"+ reportName.substring(0, 1).toUpperCase() + reportName.substring(1)+suffix;
					appResource = ResourceUtil.getResource(reportFilePath);
					if(appResource == null){
						errorMsg.append(",").append(reportFilePath).append("]");
						throw ExceptionCodeEnum.NOT_FOUND.newException(errorMsg.toString());
					}
				}
			}
			return appResource;
		}catch (IOException e){
			throw ExceptionCodeEnum.NOT_FOUND.newException("获取报表jrxml文件异常",e);
		}
	}

	private JRDesignStyle getBaseStyle(){
		JRDesignStyle base = new JRDesignStyle();
		base = new JRDesignStyle();
		base.setName("myBaseStyleName");
		base.setVerticalAlignment(VerticalAlignEnum.MIDDLE);
		// {{{ fix PDF show chinese
		base.setFontName("宋体");
		base.setPdfFontName("STSong-Light");
		base.setPdfEncoding("UniGB-UCS2-H");
		// }}}
		base.setBlankWhenNull(true);
		return base;
	}

	private void addDefaultStyle(JasperDesign design, JRDesignStyle style)
			throws JRException {
		HashMap<String, JRStyle> map = (HashMap<String, JRStyle>) design
				.getStylesMap();
		if (!map.containsKey(style.getName())) {
			design.addStyle(style);
		}
	}

	private void fixMissingParamAndField(JasperDesign design) throws JRException {
		Map<String, JRParameter> params =  design.getParametersMap();        //parameters
		Map<String, JRField> fields =  design.getFieldsMap();                //fields
		List<JRChild> els = getDesignElements(design);
		JRDesignStyle base = (JRDesignStyle) design.getStylesMap().get("myBaseStyleName");
		for(JRChild child:els){
			if(!(child instanceof JRDesignElement)){
				continue;
			}
			JRDesignElement e = (JRDesignElement) child;
			e.setStyle(base);                //设置base样式
			if(!(e instanceof JRDesignTextField)){
				if(e instanceof JRDesignStaticText){
					fixPDFFontEncoding((JRDesignStaticText) e, base);
				}
				continue;
			}
			JRDesignTextField textField = (JRDesignTextField) e;
			fixPDFFontEncoding(textField, base);
			if(textField.getExpression() == null || textField.getExpression().getText().length()<5){
				continue;
			}
			String exp = textField.getExpression().getText();
			String name = getExpressionValue(exp);
			if(exp.charAt(1) == 'P'){
				if(params.containsKey(name)){
					continue;
				}
				JRDesignParameter p = new JRDesignParameter();
				p.setName(name);
				Class<?> clazz = textField.getExpression().getValueClass();
				p.setValueClass(clazz);
				design.addParameter(p);
			}else if(exp.charAt(1) == 'F'){
				if(fields.containsKey(name)){
					continue;
				}
				JRDesignField f = new JRDesignField();
				f.setName(name);
				Class<?> clazz = textField.getExpression().getValueClass();
				f.setValueClass(clazz);
				design.addField(f);
			}
		}
	}

	private  List<JRChild> getDesignElements(JasperDesign design){
		List<JRChild> els = new ArrayList<JRChild>();
		if(design.getBackground() != null){
			List<JRChild> els_background = ((JRDesignBand) design.getBackground()).getChildren();
			els.addAll(els_background);
		}
		if(design.getTitle() != null){
			List<JRChild> els_title = ((JRDesignBand) design.getTitle()).getChildren();
			els.addAll(els_title);
		}
		if(design.getPageHeader() != null){
			List<JRChild> els_pageHeader = ((JRDesignBand) design.getPageHeader()).getChildren();
			els.addAll(els_pageHeader);
		}
		if(design.getColumnHeader() != null){
			List<JRChild> els_columnHeader = ((JRDesignBand) design.getColumnHeader()).getChildren();
			els.addAll(els_columnHeader);
		}
		if(design.getDetailSection().getBands().length >0 && design.getDetailSection().getBands()[0] != null){
			List<JRChild> els_detail = ((JRDesignBand) design.getDetailSection().getBands()[0]).getChildren();
			els.addAll(els_detail);
		}
		if(design.getColumnFooter() != null){
			List<JRChild> els_columnFooter = ((JRDesignBand) design.getColumnFooter()).getChildren();
			els.addAll(els_columnFooter);
		}
		if(design.getPageFooter() != null){
			List<JRChild> els_pageFooter = ((JRDesignBand) design.getPageFooter()).getChildren();
			els.addAll(els_pageFooter);
		}
		if(design.getLastPageFooter() != null){
			List<JRChild> els_lastPageFooter = ((JRDesignBand) design.getLastPageFooter()).getChildren();
			els.addAll(els_lastPageFooter);
		}
		if(design.getSummary() != null){
			List<JRChild> els_summary = ((JRDesignBand) design.getSummary()).getChildren();
			els.addAll(els_summary);
		}
		return els;
	}

	private  void fixPDFFontEncoding(JRDesignStaticText t,JRDesignStyle s){
		t.setFontName(s.getFontName());
		t.setPdfFontName(s.getPdfFontName());
		t.setPdfEncoding(s.getPdfEncoding());
	}

	private  void fixPDFFontEncoding(JRDesignTextField t,JRDesignStyle s){
		t.setFontName(s.getFontName());
		t.setPdfFontName(s.getPdfFontName());
		t.setPdfEncoding(s.getPdfEncoding());
	}

	private  String getExpressionValue(String str){
		return str.substring(3, str.length()-1);
	}
}
