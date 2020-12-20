package com.crud.library.service;

public class CopyIsNotAvalible extends RuntimeException{
    public CopyIsNotAvalible() {
        super("A copy is not available, you cannot borrow it!");
    }
}
