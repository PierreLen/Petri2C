package modele;

import java.util.HashSet;
import java.util.Set;

public class PetriNet {

    private Set<ArcPost> arcPosts;
    private Set<Token> tokens;
    private Set<ArcPre> arcPres;
    private Set<Place> places;
    private Set<Transition> transitions;

    public PetriNet() {
        this.arcPosts = new HashSet<>();
        this.tokens = new HashSet<>();
        this.arcPres = new HashSet<>();
        this.places = new HashSet<>();
        this.transitions = new HashSet<>();
    }

    public void addPetriObjects(PetriObject ...petriObjects) {
        for (PetriObject petriObject : petriObjects) {
            if (petriObject instanceof ArcPre) {
                arcPres.add((ArcPre) petriObject);
            }
            if (petriObject instanceof ArcPost) {
                arcPosts.add((ArcPost) petriObject);
            }
            if (petriObject instanceof Place) {
                places.add((Place) petriObject);
            }
            if (petriObject instanceof Transition) {
                transitions.add((Transition) petriObject);
            }
            if (petriObject instanceof Token) {
                tokens.add((Token) petriObject);
            }
        }
    }

    public boolean removePetriObject(PetriObject petriObject) {
        if (petriObject instanceof ArcPre) {
            return arcPres.remove((ArcPre) petriObject);
        }
        if (petriObject instanceof ArcPost) {
            return arcPosts.remove(petriObject);
        }
        if (petriObject instanceof Place) {
            return places.remove(petriObject);
        }
        if (petriObject instanceof Transition) {
            return transitions.remove(petriObject);
        }
        if (petriObject instanceof Token) {
            return tokens.remove(petriObject);
        }
        return false;
    }


}
