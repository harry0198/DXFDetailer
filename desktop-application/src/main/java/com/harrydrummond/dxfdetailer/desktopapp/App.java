package com.harrydrummond.dxfdetailer.desktopapp;

import com.harrydrummond.dxfdetailer.desktopapp.controllers.FileController;
import com.harrydrummond.dxfdetailer.desktopapp.controllers.StageController;
import com.harrydrummond.dxfdetailer.desktopapp.model.FileModel;
import com.harrydrummond.dxfdetailer.desktopapp.views.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        StageController controller = new StageController(stage);

        View view = new View();
        FileModel fileModel = new FileModel();
        new FileController(controller,view, fileModel);

        Scene scene = new Scene(view,640,480);
        view.applyFontResizingToPane(scene, view.getUploadContainer());
        view.applyFontResizingToPane(scene, view.getInfoPane());
        stage.setScene(scene);
        stage.setMinWidth(680);
        stage.setMinHeight(540);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}