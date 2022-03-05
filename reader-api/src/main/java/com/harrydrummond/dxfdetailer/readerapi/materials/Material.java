package com.harrydrummond.dxfdetailer.readerapi.materials;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Material implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final Map<Double, Double> costPerMm;
    private final Map<Double, Double> costToCutPerMm;

    public Material(String name) {
        this.name = name;
        this.costPerMm = new HashMap<>();
        this.costToCutPerMm = new HashMap<>();
    }

    /**
     * Gets the cost of this material per mm based on its thickness from the
     * costPerMm map
     * @param thickness Thickness of the material in millimeters
     * @return Cost of material per 1mm x 1mm from thickness or if inputted thickness does not exist, throws
     * InvalidMaterialException
     * @throws InvalidMaterialThicknessException If inputted material does not exist within this material.
     */
    public double getCostOfMaterial(double thickness) throws InvalidMaterialThicknessException {
        Double cost = costPerMm.get(thickness);
        if (cost == null) {
            throw new InvalidMaterialThicknessException(String.format("This material does not have a thickness of %s mm", thickness));
        }
        return cost;
    }

    /**
     * Gets the cost to cut of this material per mm based on its thickness from the
     * costPerMm map
     * @param thickness Thickness of the material in millimeters
     * @return Cost to cut of material per 1mm x 1mm from thickness or if inputted thickness does not exist, throws
     * InvalidMaterialException
     * @throws InvalidMaterialThicknessException If inputted material does not exist within this material.
     */
    public double getCostToCutOfMaterial(double thickness) throws InvalidMaterialThicknessException {
        Double cost = costToCutPerMm.get(thickness);
        if (cost == null) {
            throw new InvalidMaterialThicknessException(String.format("This material does not have a thickness of %s mm", thickness));
        }
        return cost;
    }

    /**
     * Adds cost of material by thickness to this class. If thickness already exists in the map, it is overwritten with
     * the new value
     * @param thickness Thickness of the material
     * @param cost Cost of the material per 1mm x 1mm
     */
    public void addCostOfThickness(double thickness, double cost) {
        this.costPerMm.put(thickness, cost);
    }
    /**
     * Adds cost of material by thickness to this class. If thickness already exists in the map, it is overwritten with
     * the new value
     * @param thickness Thickness of the material
     * @param cost Cost of the material per 1mm x 1mm
     */
    public void addCostToCutOfThickness(double thickness, double cost) {
        this.costToCutPerMm.put(thickness, cost);
    }


}