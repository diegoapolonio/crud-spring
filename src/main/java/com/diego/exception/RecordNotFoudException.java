package com.diego.exception;

public class RecordNotFoudException extends RuntimeException{
 private  static final long serialVaersionUID = 1l;
    public  RecordNotFoudException(long id){
        super("Registro não encontrado com id:"+ id);
    }
}
