package com.harrydrummond.dxfdetailer.desktopapp.model;

import com.harrydrummond.dxfdetailer.readerapi.Geometry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFLayer;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;
import org.kabeja.parser.ParseException;

import java.io.File;
import java.util.Iterator;

public class FileModel {

    /**
     * Parses DXF File into readable format
     *
     * @param file File .dxf to parse
     * @return DXFDocument if success. Null if failure.
     */
    public @Nullable DXFDocument parseFile(final File file) {
        Parser parser = ParserBuilder.createDefaultParser();

        try {
            parser.parse(file.getAbsolutePath(), DXFParser.DEFAULT_ENCODING);
        } catch (ParseException exception) {
            return null;
        }
        return parser.getDocument();
    }

    /**
     * Get length of all lines on document.
     * @param document DXFDocument to get length of
     * @return long Path Length on all layers of document.
     */
    public long getPathLength(@NotNull final DXFDocument document) {
        long length = 0L;
        Iterator iterator = document.getDXFLayerIterator();
        while (iterator.hasNext()) {
            length += Geometry.calculatePathLengthOnLayer((DXFLayer) iterator.next());
        }
        return round(length);
    }

    /**
     * Max width across document
     * @param document DXFDocument to get width of
     * @return long Maximum Width across
     */
    public long getDocumentMaxWidth(@NotNull final DXFDocument document) {
        long width = 0L;
        Iterator iterator = document.getDXFLayerIterator();
        while (iterator.hasNext()) {
            DXFLayer layer = (DXFLayer) iterator.next();
            long w = (long) layer.getBounds().getWidth();
            // Bounds can be infinity - which we should ignore.
            if (w > width && w != Long.MAX_VALUE ) {
                width = w;
            }
        }
        return round(width);
    }

    /**
     * Max height across document
     * @param document DXFDocument to get height of
     * @return long Maximum Height across.
     */
    public long getDocumentMaxHeight(@NotNull final DXFDocument document) {
        long height = 0L;
        Iterator iterator = document.getDXFLayerIterator();
        while (iterator.hasNext()) {
            DXFLayer layer = (DXFLayer) iterator.next();
            long h = (long) layer.getBounds().getHeight();
            if (h > height && h != Long.MAX_VALUE) {
                height = h;
            }
        }
        return round(height);
    }

    private Long round(long num) {
        return (long) Math.rint(num * 100) / 100;
    }
}