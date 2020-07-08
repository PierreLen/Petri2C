package sample.editorMenu;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import sample.Controller;

public class PetriMenu extends VBox {

    @FXML
    public ToggleGroup PetriMenuToggleGroup;
    @FXML
    public RadioButton radioPlace;
    @FXML
    public RadioButton radioFranchir;
    @FXML
    public RadioButton radioToken;
    @FXML
    public RadioButton radioEdition;
    @FXML
    public RadioButton radioArc;
    @FXML
    public RadioButton radioTransition;

    /**
     * Controller principal de l'application
     */
    private Controller mainController;

    /**
     * Set le controller principal pour accès.
     * Peut être le mettre dans une interface ou dans une classe à hériter
     *
     * @param mainController
     */
    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }


    public PetriMenu() {
    }

    public void initialize(){
        radioPlace.setUserData(PetriMenuRadioButtons.PLACE);
        radioFranchir.setUserData(PetriMenuRadioButtons.FRANCHIR);
        radioToken.setUserData(PetriMenuRadioButtons.TOKEN);
        radioEdition.setUserData(PetriMenuRadioButtons.EDITION);
        radioArc.setUserData(PetriMenuRadioButtons.ARC);
        radioTransition.setUserData(PetriMenuRadioButtons.TRANSITION);

        this.PetriMenuToggleGroup.selectToggle(radioPlace);
//        mainController.setCurrentRadio(PetriMenuRadioButtons.PLACE);
        this.PetriMenuToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                System.out.println(t1);
                mainController.setCurrentRadio((PetriMenuRadioButtons) t1.getUserData());
            }
        });

    }

    public RadioButton getSelectedRadio() {

        try {
            return (RadioButton) PetriMenuToggleGroup.getSelectedToggle();
        } catch (NullPointerException e) {
            return null;
        }

    }

    public void printHello() {
        System.out.println("Hello !!");
    }
}
