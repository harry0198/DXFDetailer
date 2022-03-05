package com.harrydrummond.dxfdetailer.readerapi;

import com.harrydrummond.dxfdetailer.readerapi.documents.PathDocument;
import com.harrydrummond.dxfdetailer.readerapi.materials.InvalidMaterialThicknessException;
import com.harrydrummond.dxfdetailer.readerapi.materials.Material;

public class Quote {

    private final Material material;
    private final double thickness;
    private final PathDocument pathDocument;

    public Quote(final Material material, final double thickness, final PathDocument pathDocument) {
        this.material = material;
        this.thickness = thickness;
        this.pathDocument = pathDocument;
    }

    /**
     * Calculates cost to cut path length of material
     * @return Total cost to cut path length of material
     * @throws InvalidMaterialThicknessException if thickness value does not exist for selected material.
     */
    public double calculateCostToCutPathLength() throws InvalidMaterialThicknessException {
        return material.getCostToCutOfMaterial(thickness) * pathDocument.getPathLength();
    }

    /**
     * Gets selected material for quote
     * @return Material selected for quote
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Gets document being analyse for the quote
     * @return Document being analysed for quote
     */
    public PathDocument getPathDocument() {
        return pathDocument;
    }
}