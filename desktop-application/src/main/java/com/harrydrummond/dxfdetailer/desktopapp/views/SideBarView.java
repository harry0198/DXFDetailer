package com.harrydrummond.dxfdetailer.desktopapp.views;

import com.harrydrummond.dxfdetailer.desktopapp.ColourConstants;
import com.harrydrummond.dxfdetailer.desktopapp.Dialog;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SideBarView extends VBox {

    public SideBarView() {
        setMinWidth(300);
        setBackground(new Background(new BackgroundFill(ColourConstants.LIGHT_PURPLE, new CornerRadii(0,10,10,0, false),null)));
        setPadding(new Insets(20,20,20,20));
        setSpacing(20);

        Label label = new Label(Dialog.SIDEBAR_TITLE.toUpperCase());
        label.setTextFill(Color.WHITE);
        label.minHeight(150);
        label.setFont(new Font(25));

        SideBarFileViewer sideBarFileViewer = new SideBarFileViewer();
        ScrollPane scrollPane = new ScrollPane(sideBarFileViewer);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        setVgrow(scrollPane, Priority.ALWAYS);

        getChildren().addAll(label, scrollPane);

    }
}