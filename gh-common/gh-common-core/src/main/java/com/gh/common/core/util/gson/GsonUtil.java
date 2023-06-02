package com.gh.common.core.util.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GsonUtil {
	/**
	 * 实现格式化的时间字符串转时间对象
	 */
	private static final String DATEFORMAT_default = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 单例模式 双重检测
	 * ConcurrentHashMap 线程安全 不需要volatile修饰
	 */
	private static Map<String,Gson> gsonInstanceMap = new ConcurrentHashMap<>(4);

	/**
	 * json字符串转指定类型对象
	 *
	 * @param json
	 * @param typeToken
	 * @return
	 */
	public static <T> T fromJson(String json, TypeToken<T> typeToken) {
		return getGson(false,false).fromJson(json, typeToken.getType());

	}

	/**
	 * json字符串转指定类型对象
	 *
	 * @param json
	 * @param cls
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> cls) {
		return getGson(false,false).fromJson(json, cls);
	}

	/**
	 * 对象转jsonString
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		return getGson(false,false).toJson(obj);
	}

	/**
	 * 对象转json
	 *
	 * @param obj
	 * @param format
	 * @return
	 */
	public static String toJson(Object obj, boolean format) {
		return getGson(format,false).toJson(obj);
	}

	/**
	 * 对象转json
	 *
	 * @param obj
	 * @param format
	 * @param serializeNulls
	 * @return
	 */
	public static String toJson(Object obj, boolean format,boolean serializeNulls) {
		return getGson(format,serializeNulls).toJson(obj);
	}
	/**
	 * @desc 单例模式获取Gson
	 * @date 2023/5/18 11:16
	 * @author tianma
	 */
	public static Gson getGson(boolean format,boolean serializeNulls){
		String key = format+"_"+serializeNulls;
		Gson gson = gsonInstanceMap.get(key);
		if(gson == null){
			synchronized (GsonUtil.class){
				gson = gsonInstanceMap.get(key);
				if(gson == null){
					gson = createGson(format,serializeNulls);
					gsonInstanceMap.put(key,gson);
				}
			}
		}
		return gson;
	}


	/**
	 * 创建Gson
	 * @param format
	 * @param serializeNulls
	 * @return
	 */
	private static Gson createGson(boolean format,boolean serializeNulls){
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 设置默认时间格式
		 */
		gsonBuilder.setDateFormat(DATEFORMAT_default);
		gsonBuilder.disableHtmlEscaping();
		GsonNumberStrategy numberStrategy = new GsonNumberStrategy();
		gsonBuilder.setObjectToNumberStrategy(numberStrategy);
		if(serializeNulls){//序列化null
			gsonBuilder.serializeNulls();
		}
		/**
		 * 添加格式化设置
		 */
		if (format) {
			gsonBuilder.setPrettyPrinting();
		}
		Gson gson = gsonBuilder.create();
		return gson;
	}
}
