package com.spring.demo.bean;


public class ServiceResultBuilder {


	public static <T> ServiceResult<T> buildSuccessResult(String code) {
		return buildSuccessResult(null, code, null, 1);
	}

	public static <T> ServiceResult<T> buildSuccessResult(String code, String message) {
		return buildSuccessResult(null, code, message, 0);
	}

	public static <T> ServiceResult<T> buildSuccessResult(T t, String code) {
		return buildSuccessResult(t, code, 1);
	}

	public static <T> ServiceResult<T> buildSuccessResult(String code, String message, long count) {
		return buildSuccessResult(null, code, message, count);
	}

	public static <T> ServiceResult<T> buildSuccessResult(T t, String code, long count) {
		return buildSuccessResult(t, code, null, count);
	}

	public static <T> ServiceResult<T> buildSuccessResult(T t, String code, String message,
													  long count) {
		ServiceResult<T> result = createServiceResultBase(t, code, message, count);
		result.setSuccess(true);
		return result;
	}

	public static <T> ServiceResult<T> buildFailedResult(T t, String code) {
		return buildFailedResult(t, code, 0);
	}

	public static <T> ServiceResult<T> buildFailedResult(String code) {
		return buildFailedResult(null, code, null, 0);
	}

	public static <T> ServiceResult<T> buildFailedResult(String code, String message) {
		return buildFailedResult(null, code, message, 0);
	}

	public static <T> ServiceResult<T> buildFailedResult(String code, long count) {
		return buildFailedResult(null, code, null, count);
	}

	public static <T> ServiceResult<T> buildFailedResult(String code, String message, long count) {
		return buildFailedResult(null, code, message, count);
	}

	public static <T> ServiceResult<T> buildFailedResult(T t, String code, long count) {
		return buildFailedResult(t, code, null, count);
	}

	public static <T> ServiceResult<T> buildFailedResult(T t, String code, String message, long count) {
		ServiceResult<T> result = createServiceResultBase(t, code, message, count);
		return result;
	}


	private static <T> ServiceResult<T> createServiceResultBase(T t, String code, String message, long count) {
		ServiceResult<T> result = new ServiceResult<T>();
		result.setTotal(count);
		result.setCode(code);
		result.setData(t);
		result.setMessage(message);
		return result;
	}

	public static <T> ServiceResult<T> buildSuccessMessageResult(String message) {
		return buildSuccessMessageResult(null, message, 1);
	}

	public static <T> ServiceResult<T> buildSuccessMessageResult(T t, String message) {
		return buildSuccessMessageResult(t, message, 1);
	}

	public static <T> ServiceResult<T> buildSuccessMessageResult(T t,
															 String message, long count) {
		ServiceResult<T> result = new ServiceResult<T>();
		result.setTotal(count);
		result.setMessage(message);
		result.setData(t);
		result.setSuccess(true);
		return result;
	}

	public static <T> ServiceResult<T> buildFailedMessageResult(String message) {
		return buildFailedMessageResult(null, message, 0);
	}

	public static <T> ServiceResult<T> buildFailedMessageResult(T t, String message) {
		return buildFailedMessageResult(t, message, 0);
	}

	public static <T> ServiceResult<T> buildFailedMessageResult(String message,
															long count) {
		return buildFailedMessageResult(null, message, count);
	}

	public static <T> ServiceResult<T> buildFailedMessageResult(T t,
															String message, long count) {
		ServiceResult<T> result = new ServiceResult<T>();
		result.setTotal(count);
		result.setMessage(message);
		result.setData(t);
		return result;
	}
}
