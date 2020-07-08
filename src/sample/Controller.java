package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.editorMain.DrawingZone;
import sample.editorMenu.PetriMenu;
import sample.editorMenu.PetriMenuRadioButtons;
import sample.grapheDeMarquage.GraphDeMarquageController;

import java.io.IOException;

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


    public void setCurrentRadio(PetriMenuRadioButtons rb) {
        currentRadio = rb;
        drawingZoneController.colorizePlaces(rb == PetriMenuRadioButtons.FRANCHIR);
    }

    public PetriMenuRadioButtons getCurrentRadio() {
        return currentRadio;
    }
}