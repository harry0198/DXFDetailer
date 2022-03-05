package com.harrydrummond.dxfdetailer.readerapi.materials;

public class InvalidMaterialThicknessException extends Exception {

    public InvalidMaterialThicknessException() {
        this("This material does not have that thickness");
    }

    public InvalidMaterialThicknessException(String message) {
        super(message);
    }
}