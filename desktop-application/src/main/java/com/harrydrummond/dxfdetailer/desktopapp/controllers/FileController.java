package com.harrydrummond.dxfdetailer.desktopapp.controllers;


import com.harrydrummond.dxfdetailer.desktopapp.ColourConstants;
import com.harrydrummond.dxfdetailer.desktopapp.Dialog;
import com.harrydrummond.dxfdetailer.desktopapp.views.View;
import com.harrydrummond.dxfdetailer.readerapi.Geometry;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.kabeja.dxf.DXFDocument;

import java.io.File;

public class FileController {

    private final View view;

    public FileController(final StageController stageController, final View view) {
        this.view = view;

        view.getUploadContainer().setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.COPY, TransferMode.MOVE, TransferMode.LINK);
            view.getUploadContainer().setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.DASHED, new CornerRadii(20), new BorderWidths(15))));
        });
        view.getUploadContainer().setOnDragExited(event -> view.getUploadContainer().setBorder(null));

        view.getUploadContainer().setOnDragDropped(event -> runActionsOnFile(event.getDragboard().getFiles().get(0)));
        view.getBrowseButton().setOnMouseEntered(event -> view.getBrowseButton().setBackground(new Background(new BackgroundFill(ColourConstants.ACCENT, new CornerRadii(10), null))));
        view.getBrowseButton().setOnMouseExited(event -> view.getBrowseButton().setBackground(new Background(new BackgroundFill(ColourConstants.PRIMARY, new CornerRadii(10), null))));

        view.getBrowseButton().setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.dxf");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setTitle("Select DXF File");
            File file = fileChooser.showOpenDialog(stageController.getStage());
            if (file != null) {
                runActionsOnFile(file);
            }
        });
    }

    private void runActionsOnFile(final File file) {
        DXFDocument document = Geometry.parseFile(file);

        if (document == null) {
            view.addInformation(Dialog.INVALID_FILE, 0L, 0L, 0L);
            return;
        }

        view.addInformation(file.getName(), Geometry.getPathLength(document), Geometry.getDocumentMaxHeight(document), Geometry.getDocumentMaxWidth(document));

    }
}