package com.gh.boot.demo;

import cn.hutool.json.JSONUtil;
import com.gh.boot.common.core.util.gson.GsonUtil;
import com.gh.boot.demo.data.DstBean;
import com.gh.boot.demo.data.JSONTestClassA;
import com.gh.boot.demo.data.SourceBean;
import org.apache.commons.logging.LogFactory;
import org.springframework.cglib.beans.BeanCopier;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc:
 * @author: tianma
 * @date: 2023/5/5
 */
public class BeanUtilTest {

    private static BeanCopier beanCopier = BeanCopier.create(SourceBean.class, DstBean.class, false);


    public static void main(String[] args) throws Exception {

//        // 设置默认工厂类
        System.setProperty("org.apache.commons.logging.LogFactory", "org.apache.commons.logging.impl.LogFactoryImpl");
        // 设置日志打印类
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        //设置默认日志级别
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.simplelog.defaultlog", "error");

//        //apache > hutool > spring > cglib > getter setter
//        testCopy();
//        //apache > hutool
//        testMapToBean();
//        //apache > hutool
//        testBeanToMap();
        testBeanToJson();
        testJsonToBean();
    }
    /**
     * @desc 截取指定字节长度字符串
     * example: charset=utf8 moreKeep=false => truncate(“中文UTF-8占3个字节长度”,12) => "中文UTF-8"
     * @param str 原字符串
     * @param byteMaxLength 截取字节长度
     * @param charset 中文UTF-8占3个字节 GBK2个字节
     * @param moreKeep 截取长度不够中文长度，则由moreKeep决定是否截取保留此中文
     * @date 2023/5/11 15:54
     * @author tianma
     */
    public static String truncate(String str, int byteMaxLength, Charset charset,boolean moreKeep) {
        if (str == null || str.getBytes(charset).length <= byteMaxLength) {
            return str;
        }

        int length = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            length += Character.toString(c).getBytes(charset).length;
            if (length == byteMaxLength) {
                sb.append(c);
                break;
            }
            if (length > byteMaxLength) {
                if(moreKeep){
                    sb.append(c);
                }
                break;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * @desc 截取指定字节长度字符串
     * example: charset=utf8 moreKeep=false => truncate(“中文UTF-8占3个字节长度”,12) => "中文UTF-8"
     * @param str 原字符串
     * @param byteMaxLength 截取字节长度
     * @param moreKeep 截取长度不够中文长度，则由moreKeep决定是否截取保留此中文
     * @date 2023/5/11 15:54
     * @author tianma
     */
    public static String truncate(String str, int byteMaxLength,boolean moreKeep) {
        return truncate(str,byteMaxLength,Charset.forName("UTF-8"),moreKeep);
    }

    static void testCopy() throws Exception {
        Integer[] times = {1000000, 100000, 10000, 1000, 10};

        for (int i = 0; i < times.length; i++) {
            System.out.println("\n=================Copy" + times[i] + "===============");
            Integer time = times[i];
            doCopy(time, (x, y) -> org.apache.commons.beanutils.PropertyUtils.copyProperties(y, x), "org.apache.commons.beanutils.PropertyUtils.copyProperties");
            doCopy(time, (x, y) -> org.apache.commons.beanutils.BeanUtils.copyProperties(y, x), "org.apache.commons.beanutils.BeanUtils.copyProperties");
            doCopy(time, (x, y) -> org.springframework.beans.BeanUtils.copyProperties(y, x), "org.springframework.beans.BeanUtils.copyProperties");
            doCopy(time, (x, y) -> cn.hutool.core.bean.BeanUtil.copyProperties(y, x), "cn.hutool.core.bean.BeanUtil.copyProperties");
            doCopy(time, (x, y) -> beanCopier.copy(x, y, null), "net.sf.cglib.beans.BeanCopier.copy");
            doCopy(time, (x, y) -> {
                y.setEat(x.getEat());
                y.setTitle(x.getTitle());
                y.setAge(x.getAge());
            }, "getter/setter");
        }
    }

    static void testMapToBean() throws Exception {
        Integer[] times = {1000000, 100000, 10000, 1000, 10};

        for (int i = 0; i < times.length; i++) {
            System.out.println("\n=================MapToBean" + times[i] + "===============");
            Integer time = times[i];
            doMapToBean(time, (x, y) -> org.apache.commons.beanutils.BeanUtils.populate(y, x), "org.apache.commons.beanutils.BeanUtils.populate");
            doMapToBean(time, (x, y) -> cn.hutool.core.bean.BeanUtil.mapToBean(x, y, false, null), "cn.hutool.core.bean.BeanUtil.mapToBean");
        }
    }

    static void testBeanToMap() throws Exception {
        Integer[] times = {1000000, 100000, 10000, 1000, 10};
        for (int i = 0; i < times.length; i++) {
            System.out.println("\n=================BeanToMap" + times[i] + "===============");
            Integer time = times[i];
            doBeanToMap(time, (x) -> org.apache.commons.beanutils.BeanUtils.describe(x), "org.apache.commons.beanutils.BeanUtils.describe");
            doBeanToMap(time, (x) -> cn.hutool.core.bean.BeanUtil.beanToMap(x), "cn.hutool.core.bean.BeanUtil.beanToMap");
        }
    }

    static void testBeanToJson() throws Exception {
        Integer[] times = {1000000, 100000, 10000, 1000, 10};
        for (int i = 0; i < times.length; i++) {
            System.out.println("\n=================testBeanToJson" + times[i] + "===============");
            Integer time = times[i];
            doBeanToJson(time, (x) -> GsonUtil.toJson(x), "GsonUtils");
            doBeanToJson(time, (x) -> JSONUtil.toJsonStr(x), "cn.hutool.core.bean.JSONUtil");
        }
    }

    static void testJsonToBean() throws Exception {
        Integer[] times = {1000000, 100000, 10000, 1000, 10};
        for (int i = 0; i < times.length; i++) {
            System.out.println("\n=================testJsonToBean" + times[i] + "===============");
            Integer time = times[i];
            doJsonToBean(time, (x,y) -> GsonUtil.fromJson(x,y), "GsonUtils");
            doJsonToBean(time, (x,y) -> JSONUtil.toBean(x,y), "cn.hutool.core.bean.JSONUtil");
        }
    }

    static void doCopy(Integer time, BeanCopy beanCopy, String type) throws Exception {
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < time; j++) {
            SourceBean sourceBean = new SourceBean(j, "source" + j, false, "abc");
            DstBean dstBean = new DstBean();
            beanCopy.copy(sourceBean, dstBean);
        }
        System.out.printf("执行%d次用时%dms---------%s%n", time, System.currentTimeMillis() - startTime, type);
    }

    static void doMapToBean(Integer time, MapBean f, String type) throws Exception {
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < time; j++) {
            Map<String, Object> m = new HashMap<>();
            m.put("age", j);
            m.put("name", "source" + j);
            m.put("eat", false);
            m.put("source", "dsdf");
            f.mapToBean(m, SourceBean.class);
        }
        System.out.printf("执行%d次用时%dms---------%s%n", time, System.currentTimeMillis() - startTime, type);
    }

    static void doMapToBean1(Integer time, MapBean1 f, String type) throws Exception {
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < time; j++) {
            Map<String, Object> m = new HashMap<>();
            m.put("age", j);
            m.put("name", "source" + j);
            m.put("eat", false);
            m.put("source", "dsdf");
            f.mapToBean1(m, new SourceBean(1, "", true, ""));
        }
        System.out.printf("执行%d次用时%dms---------%s%n", time, System.currentTimeMillis() - startTime, type);
    }

    static void doBeanToMap(Integer time, BeanMap f, String type) throws Exception {
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < time; j++) {
            SourceBean sourceBean = new SourceBean(j, "source" + j, false, "abc");
            f.beanToMap(sourceBean);
        }
        System.out.printf("执行%d次用时%dms---------%s%n", time, System.currentTimeMillis() - startTime, type);
    }
    static void doJsonToBean(Integer time, JSONBean f, String type) throws Exception {
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < time; j++) {
            String c = "{\"a\":\"a\",\"b\":1,\"c\":2,\"d\":4.2545,\"a\":5.2545,\"f\":false,\"g\":true,\"h\":123243343434,\"i\":123243343434}\n";
            f.toBean(c, JSONTestClassA.class);
        }
        System.out.printf("执行%d次用时%dms---------%s%n", time, System.currentTimeMillis() - startTime, type);
    }
    static void doBeanToJson(Integer time, BeanJson f, String type) throws Exception {
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < time; j++) {
            JSONTestClassA c = JSONTestClassA.builder().a("a")
                    .b(1)
                    .c(2)
                    .d(4.2545d)
                    .e(5.2545d)
                    .f(false)
                    .g(true)
                    .h(123243343434L)
                    .i(123243343434L)
                    .j(1.2565F)
                    .k(1.2565F)
                    .l(new Short("12"))
                    .m(new Short("12"))
                    .n(new Byte("12"))
                    .o(new Byte("12"))
                    .p('1')
                    .q('1')
                    .r(new BigDecimal("12.2545"))
                    .s(new Date())
//				.t(new java.sql.Date(new Date().getTime()))
                    .time(LocalDateTime.now())
                    .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                    .build();
            f.toJson(c);
        }
        System.out.printf("执行%d次用时%dms---------%s%n", time, System.currentTimeMillis() - startTime, type);
    }

    interface BeanCopy {
        void copy(SourceBean from, DstBean to) throws Exception;
    }

    interface MapBean {
        void mapToBean(Map m, Class clazz) throws Exception;
    }

    interface MapBean1 {
        void mapToBean1(Map m, Object o) throws Exception;
    }

    interface BeanMap {
        void beanToMap(SourceBean from) throws Exception;
    }

    interface JSONBean {
        void toBean(String json,Class clazz) throws Exception;
    }

    interface BeanJson {
        void toJson(Object bean) throws Exception;
    }
}
