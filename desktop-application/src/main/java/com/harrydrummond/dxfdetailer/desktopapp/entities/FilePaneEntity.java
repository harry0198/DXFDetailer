package com.harrydrummond.dxfdetailer.desktopapp.entities;

import com.harrydrummond.dxfdetailer.desktopapp.ColourConstants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;

public class FilePaneEntity extends BorderPane {

    private final File file;

    public FilePaneEntity(File file) {
        this.file = file;
        setMinWidth(50);
        setMinHeight(30);
        setMaxHeight(30);

        // Border Pane for using left and right panel markers
        Label label = new Label("FILE NAME".toUpperCase());
        Label label2 = new Label("X");


        setLeft(label);
        setRight(label2);

        setAlignment(label, Pos.CENTER);
        setAlignment(label2, Pos.CENTER);


        setOnMouseEntered(event -> setBackground(new Background(new BackgroundFill(ColourConstants.ACCENT_PURPLE, new CornerRadii(10), null))));
        setOnMouseExited(event -> setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null))));
    }


}