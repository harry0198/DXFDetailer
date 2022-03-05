package com.harrydrummond.dxfdetailer.readerapi.documents;

import com.harrydrummond.dxfdetailer.readerapi.Geometry;

import org.jetbrains.annotations.NotNull;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFEntity;
import org.kabeja.dxf.DXFLayer;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.ParseException;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class DXFPathDocument extends PathDocument {

    private final DXFDocument dxfDocument;

    public DXFPathDocument(final File file) {
        Parser parser = ParserBuilder.createDefaultParser();

        try {
            parser.parse(file.getAbsolutePath(), DXFParser.DEFAULT_ENCODING);
        } catch (ParseException exception) {
            throw new IllegalArgumentException("Failed to instantiate object! " +
                    "File is not in the correct format for DXFPathDocument. Ensure it is of type DXF!");
        }

        this.dxfDocument = parser.getDocument();
    }

    @SuppressWarnings(value = "unchecked")
    public long calculatePathLengthOnLayer(final DXFLayer layer) {
        // Gets List of all Valid DXFEntity Types as String
        Iterator<String> entityIterator = layer.getDXFEntityTypeIterator();
        long length = 0L;
        // Iterates through all entity types on layer
        while(entityIterator.hasNext()) {
            String next = entityIterator.next();
            // Gets all DXFEntity of type on current iteration
            List<DXFEntity> entityList = layer.getDXFEntities(next);

            length += entityList.stream().mapToLong(x -> (long) x.getLength()).sum();
        }
        return length * 10;
    }

    public long getPathLength() {
        long length = 0L;
        Iterator iterator = dxfDocument.getDXFLayerIterator();
        while (iterator.hasNext()) {
            length += calculatePathLengthOnLayer((DXFLayer) iterator.next());
        }
        return Geometry.round(length);
    }



    public long getDocumentMaxHeight() {
        long height = 0L;
        Iterator iterator = dxfDocument.getDXFLayerIterator();
        while (iterator.hasNext()) {
            DXFLayer layer = (DXFLayer) iterator.next();
            long h = (long) layer.getBounds().getHeight();
            if (h > height && h != Long.MAX_VALUE) {
                height = h;
            }
        }
        return Geometry.round(height) * 10;
    }

    public long getDocumentMaxWidth() {
        long width = 0L;
        Iterator iterator = dxfDocument.getDXFLayerIterator();
        while (iterator.hasNext()) {
            DXFLayer layer = (DXFLayer) iterator.next();
            long w = (long) layer.getBounds().getWidth();
            // Bounds can be infinity - which we should ignore.
            if (w > width && w != Long.MAX_VALUE ) {
                width = w;
            }
        }
        return Geometry.round(width) * 10;
    }
}