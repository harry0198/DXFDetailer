package com.harrydrummond.dxfdetailer.readerapi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

public class Geometry {

    public static Long round(long num) {
        return (long) Math.rint(num * 100) / 100;
    }
}