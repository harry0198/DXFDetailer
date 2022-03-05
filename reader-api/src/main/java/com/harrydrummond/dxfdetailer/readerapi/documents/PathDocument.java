package com.harrydrummond.dxfdetailer.readerapi.documents;

public abstract class PathDocument {


    /**
     * Get length of all lines on document.
     * @return long Path Length on all layers of document.
     */
    public abstract long getPathLength();

    /**
     * Max width across document
     * @return long Maximum Width across
     */
    public abstract long getDocumentMaxWidth();

    /**
     * Max height across document
     * @return long Maximum Height across.
     */
    public abstract long getDocumentMaxHeight();
}