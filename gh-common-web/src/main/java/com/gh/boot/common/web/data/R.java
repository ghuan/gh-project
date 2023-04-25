package com.gh.boot.common.web.data;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gh.boot.common.web.annotation.validator.ValueValidator;
import com.gh.boot.common.web.exception.enums.ResponseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 响应信息主体
 *
 * @param <T>
 * @author huan.gao
 */
@Builder
@ToString
@Accessors(chain = true)
@Data
public class R<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "响应码")
	private Integer code;

	@Schema(description = "是否成功")
	private Boolean success;

	@Schema(description = "响应消息")
	private String msg;

	@Schema(description = "响应数据")
	private T data;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime time;

	public static R<Boolean> success(){
		return success(null);
	}

	public static R<Boolean> success(String msg,Object... args){
		return success(null,msg,args);
	}

	public static <T> R<T> success(T data){
		return success(data,null);
	}

	public static <T> R<T> success(T data,String msg,Object... args){
		return fail(ResponseEnum.OK,data,msg,args);
	}

	public static R<Boolean> fail(){
		return fail(null);
	}

	public static R<Boolean> fail(String msg,Object... args){
		return fail(null,msg,args);
	}

	public static <T> R<T> fail(T data){
		return fail(null,data);
	}

	public static <T> R<T> fail(T data,String msg,Object... args){
		return fail(null,data,msg,args);
	}

	public static R<Boolean> fail(ResponseEnum responseEnum){
		return fail(responseEnum,null);
	}

	public static <T> R<T> fail(ResponseEnum responseEnum,T data){
		return fail(responseEnum,data,null);
	}

	public static R<Boolean> fail(ResponseEnum responseEnum,String msg,Object... args){
		return fail(responseEnum,null,msg,args);
	}

	public static <T> R<T> fail(ResponseEnum responseEnum,T data,String msg,Object... args){
		responseEnum = responseEnum == null ? ResponseEnum.INTERNAL_SERVER_ERROR:responseEnum;
		msg = StrUtil.format(StrUtil.isBlank(msg)?responseEnum.getMessage():msg,args);
		return (R<T>)R.builder()
				.code(responseEnum.getCode())
				.success(NumberUtil.equals(responseEnum.getCode(),ResponseEnum.OK.getCode()))
				.time(LocalDateTime.now())
				.data(data)
				.msg(msg)
				.build();
	}
}
