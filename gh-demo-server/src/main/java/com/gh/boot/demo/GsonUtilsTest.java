package com.gh.boot.demo;

import com.gh.boot.common.core.util.gson.GsonUtil;
import com.gh.boot.demo.data.JSONTestClassA;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @desc:
 * @author: tianma
 * @date: 2023/5/18
 */
public class GsonUtilsTest {
    public static void main(String[] args) throws InterruptedException {
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
                .t(new java.sql.Date(new Date().getTime()))
                .time(LocalDateTime.now())
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .build();
//		final CountDownLatch latch = new CountDownLatch(4000);
//		for(int i=0;i<1000;i++){
//			new Thread(() -> {
//				System.out.println(GsonUtils.getGson(false,true).hashCode());
//				latch.countDown();
//			}).start();
//			new Thread(() -> {
//				System.out.println(GsonUtils.getGson(true,true).hashCode());
//				latch.countDown();
//			}).start();
//			new Thread(() -> {
//				System.out.println(GsonUtils.getGson(false,false).hashCode());
//				latch.countDown();
//			}).start();
//			new Thread(() -> {
//				System.out.println(GsonUtils.getGson(true,false).hashCode());
//				latch.countDown();
//			}).start();
//		}
//		latch.await();
//		System.out.println(GsonUtils.gsonInstanceMap.size());
        System.out.println(GsonUtil.toJson(c,false,true));
        System.out.println(GsonUtil.toJson(c,true,true));
        System.out.println(GsonUtil.fromJson(GsonUtil.toJson(c,true,true), Map.class));
        System.out.println(GsonUtil.fromJson(GsonUtil.toJson(c,true,true),JSONTestClassA.class));
    }
}
