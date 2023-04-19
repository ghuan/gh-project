package com.gh.boot.jasperreports.print.core.view;

import cn.hutool.core.util.StrUtil;
import com.gh.boot.jasperreports.print.core.DocType;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ReportView extends AbstractView {

	private String docType;
	private String fileName;
	private boolean inline = true;
	private JasperDesign jasperDesign;
	private String pxImageUrl;

	public ReportView(JasperDesign jasperDesign,String docType,boolean inline,String fileName,String pxImageUrl) {
		this.jasperDesign = jasperDesign;
		this.docType = docType;
		this.inline = inline;
		this.fileName = StrUtil.isEmpty(fileName) ? jasperDesign.getName() : fileName;
		this.pxImageUrl = pxImageUrl;
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
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, (StrUtil.isEmpty(this.pxImageUrl)
				?
				(request.getHeader("X-Forwarded-Proto").split(",")[0]
					+ "://"
					+ request.getHeader("X-Forwarded-Host").split(",")[0]
					+ request.getHeader("X-Forwarded-Prefix").split(",")[0])
				:
				this.pxImageUrl
				)+"/print_image?image=");
		}else {
			contentDisposition = "attachment;filename="
				+ URLEncoder.encode(fileName+ DocType.fromTypeName(docType).getTypeSuffix(), "UTF-8");
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
}
