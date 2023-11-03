package com.code.avaliacao.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public abstract class BaseAvaliacaoException extends RuntimeException {

    @Autowired
    protected MessageSource messageSource;

    public BaseAvaliacaoException() {
        super();
    }

    @Override
    public abstract String getMessage();
}