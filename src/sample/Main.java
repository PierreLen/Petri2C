package sample;

import components.ArcPostComponent;
import components.ArcPreComponent;
import components.PlaceComponent;
import components.TransitionComponent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.ArcPre;
import modele.Place;
import modele.Transition;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//
//        Group root = new Group();
        Scene scene = new Scene(root, 700, 700, Color.WHITE);
//        PlaceComponent p1 = new PlaceComponent(new Place(), 200, 100);
//        TransitionComponent t1 = new TransitionComponent(new Transition(), 200, 200);
//        ArcPreComponent apr1 = new ArcPreComponent(p1,t1);
//        PlaceComponent p2 = new PlaceComponent(new Place(), 200, 300);
//        ArcPostComponent apo1 = new ArcPostComponent(t1,p2);
//        TransitionComponent t2 = new TransitionComponent(new Transition(), 200, 400);
//        PlaceComponent p3 = new PlaceComponent(new Place(), 100, 500);
//        PlaceComponent p4 = new PlaceComponent(new Place(), 300, 500);
//
//        root.getChildren().add(p1);
//        root.getChildren().add(apr1);
//        root.getChildren().add(p2);
//        root.getChildren().add(apo1);
//        root.getChildren().add(p3);
//        root.getChildren().add(t1);
//        root.getChildren().add(t2);
//        root.getChildren().add(p4);
        primaryStage.setTitle("Petri2C");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
