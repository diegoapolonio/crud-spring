package com.diego.enums;

public enum Status {
    ACTIVE("Ativo"),INACTIVE("Inativo");

    private String value;

    private Status(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
    //to String
    @Override
    public String toString(){
        return value;
    }
}
