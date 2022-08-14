package com.nott.ims.exception;

public class BusinessExcpetion extends RuntimeException {

    public BusinessExcpetion() {
        super();
    }

    public BusinessExcpetion(String message) {
        super(message);
    }

    public BusinessExcpetion(String message, Throwable cause) {
        super(message, cause);
    }


    public BusinessExcpetion(Throwable cause) {
        super(cause);
    }


}
