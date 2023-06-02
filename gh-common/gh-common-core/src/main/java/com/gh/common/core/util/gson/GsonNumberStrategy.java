package com.gh.common.core.util.gson;

import com.google.gson.ToNumberStrategy;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

/**
 * @desc:
 * @author: tianma
 * @date: 2023/5/17
 */
public class GsonNumberStrategy implements ToNumberStrategy {
    @Override
    public Number readNumber(JsonReader jsonReader) throws IOException {
        /**
         * 改写数字的处理逻辑，将数字值分为整型与浮点型。
         */
        double dbNum = jsonReader.nextDouble();
        // 数字超过long的最大值，返回浮点类型
        if (dbNum > Long.MAX_VALUE) {
            return dbNum;
        }

        // 判断数字是否为整数值
        long lngNum = (long) dbNum;
        if (dbNum == lngNum) {
            if(lngNum == (int)lngNum){
                return (int)lngNum;
            }else {
                return lngNum;
            }
        } else {
            return dbNum;
        }
    }
}
