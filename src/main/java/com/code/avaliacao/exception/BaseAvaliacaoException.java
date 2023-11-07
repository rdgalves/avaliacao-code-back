package com.code.avaliacao.exception;

import com.code.avaliacao.components.ApplicationContextProvider;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

@NoArgsConstructor
public abstract class BaseAvaliacaoException extends RuntimeException {

    protected MessageSource messageSource;

    public BaseAvaliacaoException(MessageSource messageSource) {
        super();
        this.messageSource = messageSource;
    }


    @Override
    public abstract String getMessage();

    protected String getMessageFromSource(String code, Object[] args) {
        return ApplicationContextProvider.getApplicationContext()
                .getBean(MessageSource.class)
                .getMessage(code, args, Locale.getDefault());
    }

}