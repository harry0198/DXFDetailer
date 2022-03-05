package com.harrydrummond.dxfdetailer.readerapi.materials;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaterialTest {

    @Test
    public void TestMaterialClass() {
        // Create class
        Material material = new Material("Wood");
        // Check class has been successfully instantiated
        assertNotNull(material);
    }

    @Test
    public void TestCostOfMaterial() throws InvalidMaterialThicknessException {
        // Create class
        Material material = new Material("Wood");
        // Add thickness to material with cost
        material.addCostOfThickness(10, 10);
        // Get cost of material by thickness
        double cost = material.getCostOfMaterial(10);
        // Check it equals 10
        assertEquals(10, cost, "Cost was expected to be 10");
    }

    @Test
    public void TestCostOfMaterialThrows() {
        // Create class
        Material material = new Material("Wood");
        // Add thickness to material with cost
        material.addCostOfThickness(10, 10);
        // Get cost of material by thickness and check exception is thrown
        Assertions.assertThrows(
                InvalidMaterialThicknessException.class,
                () -> material.getCostOfMaterial(15),
                "InvalidMaterialThicknessException was expected");
    }

    @Test
    public void TestCostToCutOfMaterial() throws InvalidMaterialThicknessException {
        // Create class
        Material material = new Material("Wood");
        // Add thickness to material with cost
        material.addCostToCutOfThickness(10, 10);
        // Get cost of material by thickness
        double cost = material.getCostToCutOfMaterial(10);
        // Check it equals 10
        assertEquals(10, cost, "Cost was expected to be 10");
    }

    @Test
    public void TestCostToCutOfMaterialThrows() {
        // Create class
        Material material = new Material("Wood");
        // Add thickness to material with cost
        material.addCostToCutOfThickness(10, 10);
        // Get cost of material by thickness and check exception is thrown
        Assertions.assertThrows(
                InvalidMaterialThicknessException.class,
                () -> material.getCostToCutOfMaterial(15),
                "InvalidMaterialThicknessException was expected");
    }


}