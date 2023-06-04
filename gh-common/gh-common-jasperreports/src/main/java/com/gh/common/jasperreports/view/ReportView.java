package com.gh.common.jasperreports.view;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.gh.common.core.spring.context.holder.SpringContextHolder;
import com.gh.common.jasperreports.data.dto.ReportBuilderDTO;
import com.gh.common.jasperreports.data.dto.ReportDataDTO;
import com.gh.common.jasperreports.enums.DocType;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ReportView extends AbstractView {

	private String docType;
	private String targetFileName;
	private boolean inline = true;
	private JasperDesign jasperDesign;
	private String pxImageServerAddress;

	public ReportView(JasperDesign jasperDesign,String docType,boolean inline,String targetFileName,String pxImageServerAddress) {
		this.jasperDesign = jasperDesign;
		this.docType = docType;
		this.inline = inline;
		this.targetFileName = StrUtil.isEmpty(targetFileName) ? jasperDesign.getName() : targetFileName;
		this.pxImageServerAddress = pxImageServerAddress;
	}

	public static ReportViewBuilder builder(){
		return new ReportView.ReportViewBuilder();
	}

	protected Map<String, Object> getParamsMap(Map<String, Object> map) throws Exception {
		Map<String, Object> params = new HashMap<>();
		for (String key : map.keySet()) {
			Object val = map.get(key);
			if (val instanceof JRDataSource) {
				continue;
			} else {
				params.put(key, val);
			}
		}
		return params;
	}

	protected JRDataSource getDataSource(Map<String, Object> map) throws Exception {
		for (String key : map.keySet()) {
			Object val = map.get(key);
			if (val instanceof JRDataSource) {
				return (JRDataSource) map.get(key);
			}
		}
		return new JREmptyDataSource();
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> map,
										   HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType(DocType.fromTypeName(this.docType).getTypeContent());
		//response.setContentType("application/octet-stream");
		String contentDisposition = null;
		JasperReport jr =  JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager
			.fillReport(jr, getParamsMap(map), getDataSource(map));
		JRAbstractExporter exporter = null;
		if(DocType.HTML.getTypeName().equals(docType)){
			exporter = new JRHtmlExporter();
			exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, 1.25f);
			exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML,"<div style='font-size:0px;page-break-before:always;'>&nbsp;</div>");
			String pxImageServerAddress = "";
			if(StrUtil.isNotEmpty(this.pxImageServerAddress)){
				pxImageServerAddress = this.pxImageServerAddress;
			}else if(StrUtil.isNotEmpty(request.getHeader("X-Forwarded-Proto"))){
				pxImageServerAddress = (request.getHeader("X-Forwarded-Proto").split(",")[0]
						+ "://"
						+ request.getHeader("X-Forwarded-Host").split(",")[0]
						+ request.getHeader("X-Forwarded-Prefix").split(",")[0]);
			}else {
				Environment ev = SpringContextHolder.getBean(Environment.class);
				String protocol = Boolean.valueOf(ev.getProperty("server.ssl.enabled")) ? "https" : "http";
				String port = ev.getProperty("server.port");
				String host = InetAddress.getLocalHost().getHostAddress();
				pxImageServerAddress = protocol + "://" + host + ":" + port;
			}
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, pxImageServerAddress+"/px?image=");
		}else {
			contentDisposition = "attachment;filename="
				+ URLEncoder.encode(targetFileName+ DocType.fromTypeName(docType).getTypeSuffix(), "UTF-8");
			if(DocType.PDF.getTypeName().equals(docType)){
				exporter = new JRPdfExporter();
			}else if(DocType.XLS.getTypeName().equals(docType)){
				exporter = new JRXlsExporter();
			}else if(DocType.XLSX.getTypeName().equals(docType)){
				exporter = new JRXlsxExporter();
			}else if(DocType.DOC.getTypeName().equals(docType) || DocType.DOCX.getTypeName().equals(docType)){
				exporter = new JRDocxExporter();
			}else if(DocType.RTF.getTypeName().equals(docType)){
				exporter = new JRRtfExporter();
			}else {
				exporter = new JRHtmlExporter();
				if(inline){
					contentDisposition = "inline;";
				}
			}
		}
		response.setHeader("Content-Disposition", contentDisposition);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
		exporter.exportReport();

	}

	public static class ReportViewBuilder {
		private ReportBuilderDTO reportBuilderDTO;
		private ReportViewGenerator reportViewGenerator;

		private ReportViewBuilder(){
			this.reportBuilderDTO = ReportBuilderDTO.builder().build();
			this.reportViewGenerator = SpringContextHolder.getBean(ReportViewGenerator.class);
		}

		public ReportViewBuilder docType(DocType docType){
			this.reportBuilderDTO.setDocType(docType);
			return this;
		}

		public ReportViewBuilder inline(Boolean inline){
			this.reportBuilderDTO.setInline(inline);
			return this;
		}

		public ReportViewBuilder targetFileName(String targetFileName){
			this.reportBuilderDTO.setTargetFileName(targetFileName);
			return this;
		}

		public ReportViewBuilder templateFileName(String templateFileName){
			this.reportBuilderDTO.setTemplateFileName(templateFileName);
			return this;
		}

		public ReportViewBuilder subReportDir(String subReportDir){
			this.reportBuilderDTO.setSubReportDir(subReportDir);
			return this;
		}

		public ReportViewBuilder data(ReportDataDTO data){
			this.reportBuilderDTO.setData(data);
			return this;
		}

		public ReportViewBuilder data(Map<String,Object> data){
			ReportDataDTO reportDataDTO = BeanUtil.mapToBean(data, ReportDataDTO.class, true);
			this.reportBuilderDTO.setData(reportDataDTO);
			return this;
		}

		public ModelAndView build(){
			return this.reportViewGenerator.generate(this.reportBuilderDTO);
		}
	}
}
