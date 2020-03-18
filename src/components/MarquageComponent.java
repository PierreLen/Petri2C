package components;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import modele.Marquage;
import modele.Place;

public class MarquageComponent extends PetriObjectComponent {

    private Marquage marquage;
    public MarquageComponent(Marquage m,int x, int y) {
        super(x, y);
        this.marquage = m;
        int i = 0;
        for (Place p : marquage.getPlaces().keySet()){
            this.getChildren().add(ecrirePlace(p,i));
            i++;
        }
    }

    private Text ecrirePlace(Place p, int numero){
        Text placeName = new Text(p.getDescription() + "  " + p.getNbJetons());
        placeName.setFont(new Font(25));
        placeName.setTranslateX(0);
        placeName.setTranslateY(30 + 35*numero);
        return placeName;
    }

    public Marquage getMarquage() {
        return marquage;
    }
}
