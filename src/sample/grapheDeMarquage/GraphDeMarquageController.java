package sample.grapheDeMarquage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modele.GraphMarquage;
import modele.Marquage;
import modele.PetriNet;

import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphDeMarquageController {

    public VBox marquagePane;
    private PetriNet petriNet;


    public GraphDeMarquageController() {

        System.out.println("Je M'exécute");

    }


    public void setPetriNet(PetriNet petriNet) {
        this.petriNet = petriNet;

    }

    public void showGraph() {
        GraphMarquage graphMarquage = new GraphMarquage(this.petriNet);
        final Map<Marquage, Set<Marquage>> grapheDeMarquage = graphMarquage.getGrapheDeMarquage();

        ArrayList<MarquageModel> tableData = new ArrayList<>();

        for (Marquage marquage : grapheDeMarquage.keySet()) {
            tableData.add(new MarquageModel(marquage.toString(), grapheDeMarquage.get(marquage).toString()));
        }

        ObservableList data = FXCollections.observableList(tableData);

        TableView tableView = new TableView();
        tableView.prefWidthProperty().bind(marquagePane.getScene().widthProperty());
        tableView.prefHeightProperty().bind(marquagePane.getScene().heightProperty());
        tableView.setEditable(false);
        TableColumn marquageColumn = new TableColumn("Marquage");
        marquageColumn.setCellValueFactory(new PropertyValueFactory("currentMarquage"));
        TableColumn nextMarquageColumn = new TableColumn("Marquage suivant");
        nextMarquageColumn.setCellValueFactory(new PropertyValueFactory("nextMarquage"));

        tableView.getColumns().addAll(marquageColumn, nextMarquageColumn);
        tableView.setItems(data);

        System.out.println(grapheDeMarquage);

        marquagePane.getChildren().add(tableView);
    }
}
