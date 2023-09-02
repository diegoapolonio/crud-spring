package com.diego.enums;

public enum Category {
    BACk_END("Back-End"),FRONT_END("Front-End");

    private String value;

     private Category(String value){
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
