package com.code.avaliacao.exception;

import org.springframework.context.i18n.LocaleContextHolder;

public class DeletarProjetoException extends BaseAvaliacaoException {

    private static final long serialVersionUID = 1L;

    private String messageKey;
    private Object[] params;

    public DeletarProjetoException(String messageKey, Object... params) {
        super();
        this.messageKey = messageKey;
        this.params = params;
    }

    @Override
    public String getMessage() {
        return messageSource.getMessage(messageKey, params, LocaleContextHolder.getLocale());
    }
}

