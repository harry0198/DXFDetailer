package com.harrydrummond.dxfdetailer.readerapi;

import com.harrydrummond.dxfdetailer.readerapi.documents.DXFPathDocument;
import com.harrydrummond.dxfdetailer.readerapi.documents.PathDocument;
import com.harrydrummond.dxfdetailer.readerapi.materials.InvalidMaterialThicknessException;
import com.harrydrummond.dxfdetailer.readerapi.materials.Material;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class QuoteTest {

    @Test
    void calculateCostToCutPathLength() throws InvalidMaterialThicknessException {
        Material material = new Material("test");
        material.addCostToCutOfThickness(10,10);
        material.addCostToCutOfThickness(40,47);

        Quote quote = new Quote(material, 10, new DXFPathDocument(new File("src/test/resources/House thing.dxf")));
        assertEquals(191700.0, quote.calculateCostToCutPathLength());

        Quote quote2 = new Quote(material, 40, new DXFPathDocument(new File("src/test/resources/House thing.dxf")));
        assertEquals(900990.0, quote2.calculateCostToCutPathLength());

    }

    @Test
    void getMaterial() {
        Material material = new Material("");
        Quote quote = new Quote(material, 10, null);
        assertEquals(material, quote.getMaterial(), "Expected material to be equal to getMaterial");
    }

    @Test
    void getPathDocument() {
        PathDocument pd = new DXFPathDocument(new File("src/test/resources/House thing.dxf"));
        Quote quote = new Quote(new Material(""), 10, pd);
        assertEquals(pd, quote.getPathDocument(), "Expected document to be equal to getPathDocument");
    }
}