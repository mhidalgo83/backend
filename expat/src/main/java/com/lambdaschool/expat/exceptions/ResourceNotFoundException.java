package com.lambdaschool.expat.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super("Error from Expat Journal Application: " + message);
    }
}
