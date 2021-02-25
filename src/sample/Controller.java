package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.editorMain.DrawingZone;
import sample.editorMenu.PetriMenu;
import sample.editorMenu.PetriMenuRadioButtons;
import sample.grapheDeMarquage.GraphDeMarquageController;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Controller {


    public Pane graphMarquage;
    public Label labelPosition;

    @FXML
    PetriMenu petriMenuController;

    @FXML
    DrawingZone drawingZoneController;
    private PetriMenuRadioButtons currentRadio;


    public void initialize() {
        drawingZoneController.setMainController(this);
        petriMenuController.setMainController(this);
    }

    public Controller() {
        this.currentRadio = PetriMenuRadioButtons.PLACE;
    }

    /**
     * Génère la fenêtre avec le graph de marquage
     *
     * @param mouseEvent
     */
    public void openMarquage(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("grapheDeMarquage/grapheDeMarquage.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        GraphDeMarquageController controller = loader.<GraphDeMarquageController>getController();
        controller.setPetriNet(this.drawingZoneController.getPetriNet());
        controller.showGraph();
        stage.setTitle("graphe de marquage");
        stage.show();
    }

    public void importReseau(MouseEvent mouseEvent) throws IOException {
        final FileChooser dialog = new FileChooser();
        dialog.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("JSON", "*.json"));
        final File file = dialog.showOpenDialog(labelPosition.getScene().getWindow());
        if (file != null) {
            // Effectuer la sauvegarde
                this.drawingZoneController.getPetriNet().fromJSON(drawingZoneController, file);
        }
    }

    public void exportReseau(MouseEvent mouseEvent) throws IOException {

        //System.out.println(this.drawingZoneController.getPetriNet().gettoJSON());
        //this.drawingZoneController.getPetriNet().gettoJSON();
        PrintWriter writer = new PrintWriter("reseau.json", "UTF-8");
        writer.println(this.drawingZoneController.getPetriNet().gettoJSON());
        writer.close();
    }


    public void setCurrentRadio(PetriMenuRadioButtons rb) {
        currentRadio = rb;
        drawingZoneController.colorizePlaces(rb == PetriMenuRadioButtons.FRANCHIR);
    }

    public PetriMenuRadioButtons getCurrentRadio() {
        return currentRadio;
    }
}