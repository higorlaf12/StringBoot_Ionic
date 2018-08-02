package com.springAprendendo.br.aprendendo.resources.resources.execptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandarError {
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String messagem) {
        errors.add(new FieldMessage(fieldName,messagem));
    }

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }
}
