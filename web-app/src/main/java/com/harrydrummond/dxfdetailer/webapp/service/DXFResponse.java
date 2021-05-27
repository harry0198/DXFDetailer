package com.harrydrummond.dxfdetailer.webapp.service;

public class DXFResponse {

    private final long pathLength;
    private final long maxWidth;
    private final long maxHeight;
    private final String fileName;

    public DXFResponse(String fileName, long pathLength, long maxWidth, long maxHeight) {
        this.pathLength = pathLength;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.fileName = fileName;
    }

    public long getPathLength() {
        return pathLength;
    }

    public long getMaxWidth() {
        return maxWidth;
    }

    public long getMaxHeight() {
        return maxHeight;
    }

    public String getFileName() {
        return fileName;
    }
}