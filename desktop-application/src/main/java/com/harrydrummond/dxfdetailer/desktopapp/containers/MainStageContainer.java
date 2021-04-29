package com.harrydrummond.dxfdetailer.desktopapp.containers;

import com.harrydrummond.dxfdetailer.desktopapp.ColourConstants;
import com.harrydrummond.dxfdetailer.desktopapp.views.MainFloorView;
import com.harrydrummond.dxfdetailer.desktopapp.views.SideBarView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class MainStageContainer extends HBox{


    public MainStageContainer() {
        setBackground(new Background(new BackgroundFill(ColourConstants.DARK_PURPLE, null, null)));

        // Left side
        SideBarView sideBarView = new SideBarView();
        setHgrow(sideBarView, Priority.NEVER);


        // Right side
        MainFloorView mainFloorView = new MainFloorView();
        setHgrow(mainFloorView, Priority.ALWAYS);

        getChildren().addAll(sideBarView, mainFloorView);
    }
}