package com.harrydrummond.dxfdetailer.desktopapp.views;

import com.harrydrummond.dxfdetailer.desktopapp.entities.FilePaneEntity;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SideBarFileViewer extends VBox {

    public SideBarFileViewer() {
        setHeight(50);
        setSpacing(5);
        for (int i = 0; i < 25; i++) {
            getChildren().add(new FilePaneEntity(null));
        }
    }
}