package com.gh.jasperreports.print.bean;

import com.gh.jasperreports.print.core.IReportHandler;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class TestHandler implements IReportHandler {
	@Override
	public Map<String, Object> getParameters(Map<String, Object> params) {
//		UserInfo u = SecurityUtils.getUserInfo();
		Map<String,Object> result = new HashMap<>();
		result.put("title","江干区报表");
		result.put("CXRQ",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return result;
	}

	@Override
	public List<Map<String, Object>> getFields(Map<String, Object> params) {
		List<Map<String,Object>> list = new ArrayList<>();
		for(int i=0;i<5;i++){
			Map<String,Object> map = new HashMap<>();
			map.put("KSMC","全科"+i);
			map.put("ZYHM","123456"+i);
			map.put("BRXM","高欢"+i);
			map.put("FPHM","MS45613"+i);
			map.put("FYHJ","12.32"+i);
			map.put("ZFHJ","12.36"+i);
			map.put("JKHJ","52.12"+i);
			List children = new ArrayList<>();
			for(int J=0;J<5;J++){
				Map<String,Object> map1 = new HashMap<>();
				map1.put("KSMC1","全科"+i);
				map1.put("ZYHM1","123456"+i);
				children.add(map1);
			}
			map.put("CHILDREN",new JRMapCollectionDataSource(children));
			list.add(map);
		}
		return list;
	}
}
