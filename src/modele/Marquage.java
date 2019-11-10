package modele;

import java.util.HashMap;
import java.util.Objects;


public class Marquage extends PetriObject {
    private HashMap<Place, Integer> places;

    public Marquage(HashMap<Place, Integer> places) {
        this.places = places;
    }

    public Marquage() {
        this.places = new HashMap<>();
    }

    public boolean addPlace(Place p, Integer nbJetons) {
        if (this.places.put(p, nbJetons) == null) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Marquage{" + places +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        Marquage marquage = (Marquage) o;
        //return Objects.equals(places, marquage.places);
        boolean same = true;
        for (Place p : this.places.keySet()) {
            if (marquage.places.containsKey(p)) {
                if (this.places.get(p) != marquage.places.get(p)) {
                    same = false;
                }
            }else{
                same = false;
            }

        }
        return same;
    }

    public HashMap<Place, Integer> getPlaces() {
        return places;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), places);
    }
}
