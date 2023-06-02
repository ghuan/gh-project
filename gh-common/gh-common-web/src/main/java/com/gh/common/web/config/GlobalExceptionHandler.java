package com.gh.common.web.config;

import com.gh.common.core.exception.BaseException;
import com.gh.common.core.exception.ExceptionCodeEnum;
import com.gh.common.core.exception.IExceptionEnum;
import com.gh.common.web.data.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.gh.common.web.data.R.fail;
import static com.gh.common.web.data.R.failure;

/**
 * @author huan.gao
 * @date 2019/2/1
 * 全局的的异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 全局异常.
	 *
	 * @param e the e
	 * @return R
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public R exception(Exception e) {
		printException(ExceptionCodeEnum.INTERNAL_SERVER_ERROR,e);
		return fail(null,e.getMessage());
	}

	/**
	 * 基础异常
	 *
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({BaseException.class})
	public R handleException(BaseException ex) {
		//打印日志
		ex.log();
		return failure(ex.getExceptionEnum(),null,ex.getMessage());
	}

	/**
	 * 非法参数验证异常
	 *
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({BindException.class})
	public R handleException(BindException ex) {
		printException(ExceptionCodeEnum.BAD_REQUEST,ex);
		String message = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().get();
		return failure(ExceptionCodeEnum.BAD_REQUEST,null,message);
	}

	/**
	 * 非法参数验证异常
	 *
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public R handleException(MethodArgumentNotValidException ex) {
		printException(ExceptionCodeEnum.BAD_REQUEST,ex);
		String message = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().get();
		return failure(ExceptionCodeEnum.BAD_REQUEST,null,message);
	}


	/**
	 * 获取httpStatus格式化字符串
	 */
	private String getApiCodeString(IExceptionEnum exceptionEnum) {
		if (exceptionEnum != null) {
			return String.format("errorCode: %s, errorMessage: %s", exceptionEnum.getCode(), exceptionEnum.getMessage());
		}
		return null;
	}

	/**
	 * 打印错误码及异常
	 */
	private void printException(IExceptionEnum exceptionEnum, Exception exception) {
		log.error(getApiCodeString(exceptionEnum), exception);
	}

//	/**
//	 * 未登陆异常信息
//	 * @param e the e
//	 * @return R
//	 */
//	@ExceptionHandler(UnLoginException.class)
//	@ResponseStatus(HttpStatus.UNAUTHORIZED)
//	public R unLoginException(Exception e) {
//		log.error("登陆失败 ex={}", e.getMessage(), e);
//		Throwable sqlException = getSQLException(e);
//		if(sqlException != null){
//			return new R<>(sqlException);
//		}else {
//			return new R<>(e);
//		}
//	}
//	/**
//	 * 密码复杂度校验未通过
//	 * @param e the e
//	 * @return R
//	 */
//	@ExceptionHandler(UnComplexPasswordException.class)
//	@ResponseStatus(HttpStatus.UNAUTHORIZED)
//	public R unComplexPasswordException(UnComplexPasswordException e) {
//		log.error("密码复杂度校验未通过 ex={}", e.getMessage(), e);
//		R result = new R(BeanUtils.beanToMap(e),e.getMessage());
//		result.setCode(CommonConstants.FAIL);
//		return result;
//	}
//	/**
//	 * 返回状态码 200
//	 * @param e the e
//	 * @return R
//	 */
//	@ExceptionHandler(OKHttpStatusException.class)
//	@ResponseStatus(HttpStatus.OK)
//	public R okHttpStatusException(Exception e) {
//		log.warn("OKHttpStatus异常信息 ex={}", e.getMessage(), e);
//		Throwable sqlException = getSQLException(e);
//		if(sqlException != null){
//			return new R<>(sqlException);
//		}else {
//			return new R<>(e);
//		}
//	}
//	private Throwable getSQLException(Throwable cause){
//		if(cause == null){
//			return null;
//		}else if(SQLException.class.isAssignableFrom(cause.getClass())){
//			return cause;
//		}else {
//			return getSQLException(cause.getCause());
//		}
//	}
//
//	/**
//	 * 统一处理请求参数校验(实体对象传参)
//	 *
//	 * @param e BindException
//	 * @return FebsResponse
//	 */
//	@ExceptionHandler(BindException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public R bindException(BindException e) {
//		log.error("BindException异常信息 ex={}", e.getMessage(), e);
//		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
//		List<String> errors = new ArrayList<>();
//		for(FieldError fieldError:fieldErrors){
//			errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
//		}
//		R result = new R(e.getMessage(),String.join(",",errors));
//		result.setCode(CommonConstants.FAIL);
//		return result;
//	}
//
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public R methodArgumentNotValidException(MethodArgumentNotValidException e) {
//		log.error("MethodArgumentNotValidException异常信息 ex={}", e.getMessage(), e);
//		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
//		List<String> errors = new ArrayList<>();
//		for(FieldError fieldError:fieldErrors){
//			errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
//		}
//		R result = new R(e.getMessage(),String.join(",",errors));
//		result.setCode(CommonConstants.FAIL);
//		return result;
//	}
//
//	/**
//	 * 统一处理请求参数校验(普通传参)
//	 *
//	 * @param e ConstraintViolationException
//	 * @return FebsResponse
//	 */
//	@ExceptionHandler(value = ConstraintViolationException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public R constraintViolationException(ConstraintViolationException e) {
//		log.error("ConstraintViolationException异常信息 ex={}", e.getMessage(), e);
//		Set<ConstraintViolation<?>>  constraintViolations = e.getConstraintViolations();
//		List<String> errors = new ArrayList<>();
//		for(ConstraintViolation constraintViolation :constraintViolations){
//			errors.add(constraintViolation.getPropertyPath().toString() + ": " + constraintViolation.getMessage());
//		}
//		R result = new R(e.getMessage(),String.join(",",errors));
//		result.setCode(CommonConstants.FAIL);
//		return result;
//	}

}
