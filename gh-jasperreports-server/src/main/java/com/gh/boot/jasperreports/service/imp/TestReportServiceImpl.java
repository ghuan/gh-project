package com.gh.boot.jasperreports.service.imp;

import com.gh.boot.jasperreports.data.jp.TestReportChildrenData;
import com.gh.boot.jasperreports.data.jp.TestReportData;
import com.gh.boot.jasperreports.data.jp.TestReportListData;
import com.gh.boot.jasperreports.data.po.*;
import com.gh.boot.jasperreports.enums.DocType;
import com.gh.boot.jasperreports.service.ITestReportService;
import com.gh.boot.jasperreports.view.ReportView;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 *
* description：机构service
 */
@Service
public class TestReportServiceImpl implements ITestReportService {
	@Override
	public ModelAndView getTestReport(TestReportPO testReportPO) {
		TestReportData data = new TestReportData();
		data.setTitle("测试报表");
		data.setCXRQ("2019-02-01");
		List list = new ArrayList<>();
		for(int i=0;i<5;i++){
			TestReportListData listData = new TestReportListData();
			listData.setKSMC("全科"+i);
			listData.setBRXM("高欢"+i);
			listData.setFPHM("MS45613"+i);
			listData.setFYHJ("12.32"+i);
			listData.setZFHJ("12.36"+i);
			listData.setZFHJ("12.36"+i);
			listData.setJKHJ("12.36"+i);
			listData.setZYHM("123456"+i);
			List<TestReportChildrenData> children = new ArrayList<>();
			for(int J=0;J<5;J++){
				TestReportChildrenData testReportChildrenData = new TestReportChildrenData();
				testReportChildrenData.setKSMC1("全科"+i);
				testReportChildrenData.setZYHM1("123456"+i);
				children.add(testReportChildrenData);
			}
			listData.setList(children);
			list.add(listData);
		}
		data.setList(list);
		return ReportView.builder()
				.templateFileName("test")
				.docType(DocType.HTML)
				.inline(false)
				.data(data)
				.build();
	}

}
