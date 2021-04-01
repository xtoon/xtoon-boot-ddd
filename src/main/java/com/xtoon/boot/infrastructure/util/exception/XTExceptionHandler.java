package com.xtoon.boot.infrastructure.util.exception;

import com.xtoon.boot.interfaces.common.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.connection.PoolException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 *
 * @author haoxin
 * @date 2021-02-02
 **/
@RestControllerAdvice
public class XTExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(XTException.class)
	public Result handleRRException(XTException e){
		logger.error(e.getMessage(), e);
		Result r = new Result();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());
		return r;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public Result handlerNoFoundException(Exception e) {
		logger.error(e.getMessage(), e);
		return Result.error(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Result handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return Result.error("数据库中已存在该记录");
	}

	@ExceptionHandler(AuthorizationException.class)
	public Result handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return Result.error("没有权限，请联系管理员授权");
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public Result handleException(IllegalArgumentException e){
		logger.error(e.getMessage(), e);
		return Result.error(e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public Result handleException(Exception e){
		logger.error(e.getMessage(), e);
		return Result.error("操作失败，"+e.getMessage());
	}

	/**
	 * spring默认上传大小100MB 超出大小捕获异常MaxUploadSizeExceededException
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public Result handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
		logger.error(e.getMessage(), e);
		return Result.error("文件大小超出10MB限制, 请压缩或降低文件质量! ");
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public Result handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		logger.error(e.getMessage(), e);
		return Result.error("字段太长,超出数据库字段的长度");
	}

	@ExceptionHandler(PoolException.class)
	public Result handlePoolException(PoolException e) {
		logger.error(e.getMessage(), e);
		return Result.error("Redis 连接异常!");
	}
}
