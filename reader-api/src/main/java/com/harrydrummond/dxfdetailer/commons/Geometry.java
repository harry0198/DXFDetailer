package com.harrydrummond.dxfdetailer.commons;

import org.kabeja.dxf.DXFEntity;
import org.kabeja.dxf.DXFLayer;

import java.util.Iterator;
import java.util.List;

public class Geometry {

    @SuppressWarnings(value = "unchecked")
    public static Long calculatePathLengthOnLayer(final DXFLayer layer) {
        // Gets List of all Valid DXFEntity Types as String
        Iterator<String> entityIterator = layer.getDXFEntityTypeIterator();
        Long length = 0L;
        // Iterates through all entity types on layer
        while(entityIterator.hasNext()) {
            String next = entityIterator.next();
            // Gets all DXFEntity of type on current iteration
            List<DXFEntity> entityList = layer.getDXFEntities(next);

            length += entityList.parallelStream().mapToLong(x -> (long) x.getLength()).sum();
        }
        return length;
    }
}