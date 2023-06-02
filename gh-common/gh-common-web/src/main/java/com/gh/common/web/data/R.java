package com.gh.common.web.data;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gh.common.core.exception.ExceptionCodeEnum;
import com.gh.common.core.exception.IExceptionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
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

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime time;

	public static R<Boolean> success(){
		return success(null);
	}

	public static <T> R<T> success(T data){
		return success(data,null);
	}

	public static <T> R<T> success(T data,String msg,Object... args){
		return failure(ExceptionCodeEnum.OK,data,msg,args);
	}

	public static R<Boolean> fail(){
		return fail(null);
	}

	public static <T> R<T> fail(T data){
		return fail(data,null);
	}

	public static <T> R<T> fail(T data,String msg,Object... args){
		return failure(null,data,msg,args);
	}

	public static <T> R<T> failure(IExceptionEnum exceptionEnum, T data,String msg,Object... args){
		exceptionEnum = exceptionEnum == null ? ExceptionCodeEnum.INTERNAL_SERVER_ERROR: exceptionEnum;
		msg = StrUtil.format(StrUtil.isBlank(msg)? exceptionEnum.getMessage():msg,args);
		return (R<T>)R.builder()
				.code(exceptionEnum.getCode())
				.success(NumberUtil.equals(exceptionEnum.getCode(), ExceptionCodeEnum.OK.getCode()))
				.time(LocalDateTime.now())
				.data(data)
				.msg(msg)
				.build();
	}
}
