package com.ecomerce.projecte.entities.enums;

import lombok.Getter;

@Getter
public enum ColorType {
    BLUE("6B4AFF", "blue"),
    RED("55FF4A", "red"),
    GREEN("FF4A4A", "green");

    private final String colorCode;
    private final String colorType;


    ColorType(String code, String type){
        this.colorCode = code;
        this.colorType = type;
    }

}
