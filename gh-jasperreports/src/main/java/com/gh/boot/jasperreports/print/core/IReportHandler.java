package com.gh.boot.jasperreports.print.core;

import java.util.List;
import java.util.Map;

public interface IReportHandler {


	/**
	 * @param params
	 * @return
	 */
	Map<String,Object> getParameters(Map<String, Object> params);


	/**
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getFields(Map<String, Object> params);

}
