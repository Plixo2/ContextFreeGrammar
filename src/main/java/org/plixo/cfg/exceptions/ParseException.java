package org.plixo.cfg.exceptions;

public class ParseException extends Exception {

    public ParseException() {

    }

    public ParseException(String msg) {
        super("Failed to parse: " + msg);
    }
}
