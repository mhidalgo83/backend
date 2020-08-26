package com.lambdaschool.expat.services;

import com.lambdaschool.expat.models.ValidationError;

import java.util.List;

public interface HelperFunctions {
    List<ValidationError> getConstraintViolation(Throwable cause);
}
