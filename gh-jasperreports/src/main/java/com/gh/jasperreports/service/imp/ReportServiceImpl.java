package com.gh.jasperreports.service.imp;

import cn.hutool.core.util.StrUtil;
import com.gh.jasperreports.print.core.DocType;
import com.gh.jasperreports.print.core.IReportHandler;
import com.gh.jasperreports.service.IReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
* description：机构service
 */
@Service
public class ReportServiceImpl implements IReportService {

	protected final String docType="docType";
	protected final String inline="inline";
	protected final String fileName="fileName";
	protected final String params = "params";//前台url自定义传参，base64加密的对象json字符串
	protected final String path = "prints";
	protected final String suffix = ".jrxml";
	protected final String handler = "handler";//自定义数据处理类
	protected final String handlerFileSuffix="Handler";//默认后台数据处理类后缀 如TestHandler.java
	protected final String subReportDir = "SUBREPORT_DIR";//父报表加载子报表传递的子报表所在绝对路径

	@Value("${web.report.px-image-url:}")
	private String pxImageUrl;//报表px图片完整地址目录配置，用于报表px图片地址修正

	@Autowired
	private Environment env;

	@Override
	public ModelAndView getReport(String reportName,Map<String,Object> parameters) {
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
			if(StrUtil.isEmpty(reportName)){
				throw new BusinessException("pathName[reportName] can not empty");
			}
			String docTypeName = parameters.get(docType)+"";
			String inlineStr = parameters.get(inline)+"";
			String handlerClassName = parameters.get(handler)+"";
			String fileNameStr = parameters.get(fileName)+"";
			String paramsStr = parameters.get(params)+"";
			String reportFilePath = path+"/"+reportName+suffix;
			if(StrUtil.isEmpty(docTypeName)){
				docTypeName = DocType.HTML.getTypeName();
			}
			boolean isInline = true;
			if(StrUtil.isNotEmpty(inlineStr) && "false".equals(inlineStr)){
				isInline = false;
			}
			if(StrUtil.isEmpty(handlerClassName)){
				handlerClassName = reportName+handlerFileSuffix;
			}
			IReportHandler handler;
			String errorMsg = "";
			try{
				handler = SpringContextHolder.getBean(handlerClassName);
			}catch (Exception e){
				try{
					handlerClassName = handlerClassName.substring(0, 1).toLowerCase() + handlerClassName.substring(1);
					handler = SpringContextHolder.getBean(handlerClassName);
				}catch (Exception e1){
					errorMsg += e1.getMessage();
					try{
						handlerClassName = handlerClassName.substring(0, 1).toUpperCase() + handlerClassName.substring(1);
						handler = SpringContextHolder.getBean(handlerClassName);
					}catch (Exception e2){
						errorMsg += " " + e2.getMessage();
						throw new BusinessException(errorMsg);
					}
				}

			}
			errorMsg = "";
			ResourceDTO resourceDTO = VTools.getResource(reportFilePath);
			if(resourceDTO == null){
				reportFilePath = path+"/"+ reportName.substring(0, 1).toLowerCase() + reportName.substring(1)+suffix;
				resourceDTO = VTools.getResource(reportFilePath);
				if(resourceDTO == null){
					errorMsg += "未找到报表配置文件["+ reportFilePath;
					reportFilePath = path+"/"+ reportName.substring(0, 1).toUpperCase() + reportName.substring(1)+suffix;
					resourceDTO = VTools.getResource(reportFilePath);
					if(resourceDTO == null){
						errorMsg += ","+ reportFilePath+"]";
						throw new BusinessException(errorMsg);
					}
				}
			}
			Map<String,Object> paramsMap = new HashMap<>();
			if(!VTools.StringIsEmpty(paramsStr)){
				byte[] paramsBytes = Base64Utils.decodeFromString(paramsStr);
				String paramsJsonStr = new String(paramsBytes);
				paramsMap = GsonUtils.fromJson(paramsJsonStr,Map.class);
			}
			InputStream inputStream = resourceDTO.getInputStream();
			JasperDesign jd = JRXmlLoader.load(inputStream);
			addDefaultStyle(jd,getBaseStyle());
			jd.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			fixMissingParamAndField(jd);
			Map<String, Object> map = handler.getParameters(paramsMap);
			if(map == null){
				map = new HashMap<>();
			}
			//子报表设置parameter:subReportDir
			map.put(subReportDir,resourceDTO.getParentPath());
			List dataList = handler.getFields(paramsMap);
			if(!VTools.ListIsEmpty(dataList)){
				map.put("ds", new JRMapCollectionDataSource(dataList));
			}
			String pxImageUrl = env.getProperty(pxImageUrlCfg);
			return new ModelAndView(new ReportView(jd,docTypeName,isInline,fileNameStr,pxImageUrl), map);
		}catch (Exception e){
			e.printStackTrace();
			throw new BusinessException(e.getMessage(),e);
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
		Map<String, JRParameter> params =  design.getParametersMap();		//parameters
		Map<String, JRField> fields =  design.getFieldsMap();				//fields
		List<JRChild> els = getDesignElements(design);
		JRDesignStyle base = (JRDesignStyle) design.getStylesMap().get("myBaseStyleName");
		for(JRChild child:els){
			if(!(child instanceof JRDesignElement)){
				continue;
			}
			JRDesignElement e = (JRDesignElement) child;
			e.setStyle(base);				//设置base样式
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
