package com.harrydrummond.dxfdetailer.desktopapp;

import com.harrydrummond.dxfdetailer.SystemInfo;
import com.harrydrummond.dxfdetailer.desktopapp.containers.MainStageContainer;
import com.harrydrummond.dxfdetailer.desktopapp.controllers.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
//        var javaVersion = SystemInfo.javaVersion();
//        var javafxVersion = SystemInfo.javafxVersion();
//
//        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
//        var scene = new Scene(new StackPane(label), 640, 480);
//        stage.setScene(scene);
//        stage.show();

        Controller controller = new Controller(stage);
        Scene scene = new Scene(new MainStageContainer(),640,480, ColourConstants.DARK_PURPLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}