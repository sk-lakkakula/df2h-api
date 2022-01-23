package com.df2h.lsk.exception;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class IntegrityViolationException extends MySQLIntegrityConstraintViolationException {
	public IntegrityViolationException(String reason) {
		super(reason);
	}
}
