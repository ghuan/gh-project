package com.gh.common.core.annotation.validator;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author tianma
 * @date 11:44 2020/10/26
 */
public class DecimalPrecisionValidatorImpl implements ConstraintValidator<DecimalPrecisionValidator,String> {

    public String  value;

    @Override
    public void initialize(DecimalPrecisionValidator constraintAnnotation) {
        //传入value 值，可以在校验中使用
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        //空的不用判断
        if(StrUtil.isBlank(str)){
            return true;
        }
        try {
            double num = Double.valueOf(str);//把字符串强制转换为数字
            if(str.trim().indexOf(".") == -1){
                return false;
            }
            int fourplace = str.trim().length() - str.trim().indexOf(".") - 1;
            if(fourplace < Convert.toInt(value)){
                return false;
            }else{
                return true;
            }
        } catch (Exception e) {
            return false;//如果抛出异常，返回False
        }
    }

}
