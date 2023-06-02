package com.gh.common.core.annotation.validator;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author zhangguoqing
 * @date 2020/9/29 17:02
 * @description: 校验传递的字段类型是否在规定范围内
 */
public class ValueValidatorImpl implements ConstraintValidator<ValueValidator,String> {

    public String  value;
    @Override
    public void initialize(ValueValidator constraintAnnotation) {
        //传入value 值，可以在校验中使用
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        if (StrUtil.isBlank(str)){
            return true;
        }
        List<?> list = Convert.toList(value);
        return list.contains(str);
    }
}
