package model;

import java.util.HashMap;

public class Marquage {

    private HashMap<Place, Integer> places;

    public Marquage(HashMap<Place, Integer> places) {
        this.places = places;
    }

    public Marquage() {
        this.places = new HashMap<>();
    }

    public boolean addPlace(Place place, int nbJetons) {
        return this.places.put(place, nbJetons) == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Marquage marquage = (Marquage) o;
        boolean sameFlag = true;
        for (Place place : places.keySet()) {
            if (marquage.places.containsKey(place)) {
                if (!this.places.get(place).equals(marquage.places.get(place))) {
                    sameFlag = false;
                }
            } else {
                sameFlag = false;
            }
        }
        return sameFlag;
    }

    @Override
    public int hashCode() {
        return places != null ? places.hashCode() : 0;
    }

    public HashMap<Place, Integer> getPlaces() {
        return places;
    }

}
