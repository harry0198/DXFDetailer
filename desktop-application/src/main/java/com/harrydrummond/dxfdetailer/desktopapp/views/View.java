package com.harrydrummond.dxfdetailer.desktopapp.views;

import com.harrydrummond.dxfdetailer.desktopapp.ColourConstants;
import com.harrydrummond.dxfdetailer.desktopapp.Dialog;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.InputStream;

public class View extends VBox{

    private SimpleDoubleProperty fontSize = new SimpleDoubleProperty(8);

    private final VBox uploadContainer;
    private final GridPane infoPane;
    private final Button browseButton;


    public View() {

        setPadding(new Insets(50));
        Stop[] stops = { new Stop(0, ColourConstants.ACCENT), new Stop(1, ColourConstants.PRIMARY)};
        setBackground(new Background(new BackgroundFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops), null, null)));

        // Create
        HBox masterContainer = new HBox();
        masterContainer.setPrefWidth(700);
        masterContainer.setPrefHeight(400);
        masterContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), null)));
        HBox.setHgrow(masterContainer, Priority.ALWAYS);
        VBox.setVgrow(masterContainer, Priority.ALWAYS);
        masterContainer.setAlignment(Pos.CENTER);
        clipChildren(masterContainer,20.0);

        // Upload container (left side)
        uploadContainer = new VBox();
        uploadContainer.setPadding(new Insets(25));
        uploadContainer.setAlignment(Pos.CENTER);
        uploadContainer.setPrefHeight(masterContainer.getPrefHeight());
        uploadContainer.setPrefWidth(masterContainer.getPrefWidth() / 2);
        HBox.setHgrow(uploadContainer, Priority.ALWAYS);

        InputStream resource = getClass().getClassLoader().getResourceAsStream("upload.jpg");
        ImageView uploadImage = new ImageView(new Image(resource));
        uploadImage.fitHeightProperty().bind(uploadContainer.heightProperty().divide(8));
        uploadImage.fitWidthProperty().bind(uploadImage.fitHeightProperty());

        Label dragAndDropText = new Label(Dialog.DRAG_AND_DROP);
        Label orText = new Label("or");

        browseButton = new Button(Dialog.BROWSE_BUTTON);
        browseButton.setPrefHeight(10);
        browseButton.setPadding(new Insets(5));
        browseButton.setBackground(new Background(new BackgroundFill(ColourConstants.PRIMARY, new CornerRadii(10), null)));
        browseButton.setTextFill(Color.WHITE);
        browseButton.setCursor(Cursor.HAND);
        browseButton.widthProperty().add(dragAndDropText.widthProperty());

        // Information Container (Right side)
        infoPane = new GridPane();
        infoPane.setBackground(new Background(new BackgroundFill(Color.rgb(249,249,249), null,null)));
        infoPane.setPrefHeight(masterContainer.getPrefHeight());
        infoPane.setPrefWidth(masterContainer.getPrefWidth() / 2);
        HBox.setHgrow(infoPane, Priority.ALWAYS);
        infoPane.setAlignment(Pos.CENTER);
        infoPane.setHgap(15);
        infoPane.setVgap(30);

        addInformation("File",0L,0L,0L);
        uploadContainer.getChildren().addAll(uploadImage, dragAndDropText, orText, browseButton);
        masterContainer.getChildren().addAll(uploadContainer, infoPane);

        getChildren().add(masterContainer);

    }

    public void addInformation(String fileName, Long pathL, Long length, Long width) {
        infoPane.getChildren().subList(0, infoPane.getChildren().toArray().length).clear();

        String fileNameFull = fileName;
        if (fileName.length() > 16) {
            fileNameFull = fileName.substring(0,14) + "..";
        }

        infoPane.add(displayInformation(fileNameFull, "FileName"),0,0,1,1);
        infoPane.add(displayInformation(pathL + " cm", "Path Length"),1,0,1,1);
        infoPane.add(displayInformation(length + " cm", "Length"),0,1,1,1);
        infoPane.add(displayInformation(width + " cm", "Width"),1,1,1,1);
    }

    private VBox displayInformation(String line1, String line2) {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);

        Label line1Label = new Label(line1);
        line1Label.setTextFill(ColourConstants.PRIMARY);

        Label line2Label = new Label(line2);
        line2Label.setTextFill(Color.BLACK);

        box.getChildren().addAll(line1Label, line2Label);
        return box;
    }

    public VBox getUploadContainer() {
        return uploadContainer;
    }

    public GridPane getInfoPane() {
        return infoPane;
    }

    public Button getBrowseButton() {
        return browseButton;
    }

    public void applyFontResizingToPane(final Scene scene, final Pane pane) {
        applyFontResizing(scene, pane);
    }

    private void applyFontResizing(Scene scene, Region region) {
        fontSize.bind(scene.widthProperty().add(scene.heightProperty()).divide(50));
        region.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"));
    }

    private void clipChildren(Region region, Double arc) {
        Rectangle outputClip = new Rectangle();
        outputClip.setArcWidth(arc * 3);
        outputClip.setArcHeight(arc * 3);
        region.setClip(outputClip);
        region.layoutBoundsProperty().addListener((x,y,newVal) -> {
            outputClip.setWidth(newVal.getWidth());
            outputClip.setHeight(newVal.getHeight());
        });
    }
}