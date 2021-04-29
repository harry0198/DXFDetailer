package com.harrydrummond.dxfdetailer.desktopapp.controllers;

import com.harrydrummond.dxfdetailer.desktopapp.Dialog;
import javafx.stage.Stage;


public class Controller {

    private final Stage stage;

    public Controller(final Stage mainStage) {
        this.stage = mainStage;
        stage.setTitle(Dialog.PROGRAM_NAME);
        //stage.getIcons().add(null);//todo
        stage.setOnCloseRequest(event -> {
            handleCloseRequest();
            event.consume();
        });
    }

    private void handleCloseRequest() {
        stage.close();
    }
}