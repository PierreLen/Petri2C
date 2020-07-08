package sample.editorMain;

import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.*;
import sample.Controller;
import sample.editorMenu.PetriMenuRadioButtons;


public class DrawingZone {


    public Pane petriNetPane;
    /**
     * Controller principal de l'application
     */
    private Controller mainController;
    private boolean dragging = false;
    private Place selectedPlace;
    private Transition selectedTransition;
    private Arc lastArc;
    /**
     * on pourrait le mettre dans le controlleur principal mais ça restreidrait à un seul réseau géré par l'application
     * à la fois
     */
    private PetriNet petriNet;

    /**
     * Méthode appelée après la création de la scene Javafx remplace le constructeur
     */
    public void initialize() {
        petriNet = new PetriNet();
    }

    /**
     * Set le controller principal pour accès. Peut être le mettre dans une interface ou dans une classe à hériter
     *
     * @param mainController le controller principal de l'application
     */
    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    /**
     * Permet principalement de gérer le placement de nouveaux éléments
     *
     * @param mouseEvent l'évent créé par le click
     */
    public void MouseClicked(MouseEvent mouseEvent) {
        // Clic droit pour annuler
        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            resetArcCreation();
            return;
        }
        // permet d'éviter de trigger un click sur un drag
        if (dragging) {
            dragging = false;
            return;
        }
        switch (mainController.getCurrentRadio()) {
            case PLACE:
                createPlace(mouseEvent);
                break;
            case TRANSITION:
                createTransition(mouseEvent);
                break;
            case ARC:
                break;
            case EDITION:
                break;
            case TOKEN:
                break;
            case FRANCHIR:
                break;
        }
    }

    /**
     * Permet de déplacer les éléments du réseau de Petri
     *
     * @param mouseEvent l'évent créé par le drag
     */
    public void MouseDragged(MouseEvent mouseEvent) {
        dragging = true;
    }

    /**
     * Permet d'afficher les arcs en train d'être crées
     *
     * @param mouseEvent l'évent créé par le mouseMove
     */
    public void MouseMove(MouseEvent mouseEvent) {
        if (lastArc != null)
            petriNetPane.getChildren().remove(lastArc);

        if (selectedTransition != null) {
            lastArc = new Arc(selectedTransition.getX(), selectedTransition.getY(), (int) mouseEvent.getX(), (int) mouseEvent.getY(), 1);
            petriNetPane.getChildren().add(lastArc);
        }

        if (selectedPlace != null) {
            lastArc = new Arc(selectedPlace.getX(), selectedPlace.getY(), (int) mouseEvent.getX(), (int) mouseEvent.getY(), 0);
            petriNetPane.getChildren().add(lastArc);
        }
    }

    /**
     * Crée un objet Place et lui ajoute tous ses écouteurs d'événements, puis l'ajoute à la fenêtre graphique
     *
     * @param mouseEvent l'évent créé par le click
     */
    private void createPlace(MouseEvent mouseEvent) {
        Place place = new Place(
                (int) mouseEvent.getX(),
                (int) mouseEvent.getY(),
                this);

        place.setOnMouseClicked(me -> {
            // consume() permet d'éviter le bubble d'évent un peu comme prevent default en js
            me.consume(); // pour éviter de mettre une place
            switch (mainController.getCurrentRadio()) {
                case ARC:
                    if (selectedPlace == null) {
                        selectedPlace = place;
                    }
                    if (selectedTransition != null) {
                        ArcPost arcPost = new ArcPost(selectedTransition, selectedPlace);
                        petriNet.addPetriObject(arcPost);
                        this.petriNetPane.getChildren().add(arcPost);
                        selectedTransition.addArcPost(arcPost);
                        resetArcCreation();
                    }
                    break;
                case TOKEN:
                    if (me.getButton() == MouseButton.SECONDARY) {
                        Token t = place.removeToken();
                        petriNet.removePetriObject(t);
                    } else if (me.getButton() == MouseButton.PRIMARY) {
                        Token t = new Token();
                        place.addToken(t);
                        petriNet.addPetriObject(t);
                    }
                    place.update();
                    break;
            }
        });

        place.setOnMouseDragged(me -> {
            place.setX(place.getX() + (int) (me.getX()));
            place.setY(place.getY() + (int) (me.getY()));
            updateArcs();
        });
        petriNetPane.getChildren().add(place);
        petriNet.addPetriObject(place);
    }

    /**
     * Crée un objet Transition et lui ajoute tous ses écouteurs d'événements, puis l'ajoute à la fenetre graphique
     *
     * @param mouseEvent l'évent créé par le click
     */
    private void createTransition(MouseEvent mouseEvent) {
        Transition transition = new Transition(
                (int) mouseEvent.getX(),
                (int) mouseEvent.getY(),
                this);
        transition.setOnMouseDragged(me -> {
            transition.setX(transition.getX() + (int) (me.getX()));
            transition.setY(transition.getY() + (int) (me.getY()));
            updateArcs();
        });
        transition.setOnMouseClicked(me -> {
            me.consume();
            switch (mainController.getCurrentRadio()) {
                case ARC:
                    if (selectedTransition == null) {
                        selectedTransition = transition;
                    }
                    if (selectedPlace != null) {
                        // on crée l'arc pre
                        ArcPre arcPre = new ArcPre(selectedPlace, selectedTransition);
                        petriNet.addPetriObject(arcPre);
                        this.petriNetPane.getChildren().add(arcPre);
                        selectedTransition.addArcPre(arcPre);
                        resetArcCreation();
                    }
                    break;
                case FRANCHIR:
                    petriNet.franchir(transition);
                    for (Place place : petriNet.getPlaces()) {
                        place.update();
                    }
                    for (Transition petriNetTransition : petriNet.getTransitions()) {
                        petriNetTransition.updateColor(true);
                    }
                    break;
            }
        });
        petriNetPane.getChildren().add(transition);
        petriNet.addPetriObject(transition);
    }

    /**
     * Annule toute création d'arc en cours en remmant à zéro les variables temporaires de création
     */
    private void resetArcCreation() {
        selectedPlace = null;
        selectedTransition = null;
        this.petriNetPane.getChildren().remove(lastArc);
        lastArc = null;
    }

    /**
     * Met a jour la position des arcs de la fenetre graphique pour qu'ils "collent" à leurs places et transitions
     * respectives
     * <p>
     * Pourrait causer des problèmes en cas d'arcs nombreuxs
     */
    private void updateArcs() {
        for (Node child : petriNetPane.getChildren()) {
            if (child instanceof ArcPost) {
                ((ArcPost) child).update();
            }
            if (child instanceof ArcPre) {
                ((ArcPre) child).update();
            }
        }
    }

    /**
     * Met a jour toutes les transitions et leur demande de se colorer en fonction de leur franchissabilité
     *
     * @param shouldColor si la transition doit se colorer ou rester noire
     */
    public void colorizePlaces(boolean shouldColor) {
        for (Transition transition : petriNet.getTransitions()) {
            transition.updateColor(shouldColor);
        }
    }

    public PetriNet getPetriNet() {
        return petriNet;
    }
}
