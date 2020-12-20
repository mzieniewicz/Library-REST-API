package com.crud.library.facade;

public class SearchException extends Exception {
    public static String EER_NOT_FOUND = "No results found";

    public SearchException(String message) {
        super(message);
    }
}