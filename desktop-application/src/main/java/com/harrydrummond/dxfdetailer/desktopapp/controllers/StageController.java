package com.harrydrummond.dxfdetailer.desktopapp.controllers;

import com.harrydrummond.dxfdetailer.SystemInfo;
import com.harrydrummond.dxfdetailer.desktopapp.Dialog;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;


public class StageController {

    private final Stage stage;

    public StageController(final Stage mainStage) {
        this.stage = mainStage;
        stage.setTitle(Dialog.PROGRAM_NAME);
        URL resource = getClass().getClassLoader().getResource("icon.png");
        Image image = Toolkit.getDefaultToolkit().getImage(resource);
        stage.getIcons().add(new javafx.scene.image.Image("icon.png"));


        stage.setOnCloseRequest(event -> {
            handleCloseRequest();
            event.consume();
        });
    }



    public Stage getStage() {
        return stage;
    }

    private void handleCloseRequest() {
        stage.close();
    }
}