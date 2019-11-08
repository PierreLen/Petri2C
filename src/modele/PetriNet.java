package modele;

import java.util.*;

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

    public boolean franchir(Transition t){
        LinkedList<Token> lTokens = new LinkedList<>();

        if (!t.isFranchissable())
                return false;

        for (ArcPre arcPre : t.getArcPres()){
            Iterator<Token> it = arcPre.getPlaceO().getTokens().iterator();
            for (int i =0; i < arcPre.getPoids(); i++){
                lTokens.add(it.next());
                arcPre.getPlaceO().getTokens().remove(lTokens.get(lTokens.size()-1));
            }
        }

        for (ArcPost arcPost : t.getArcPosts()){
            for (int i =0; i < arcPost.getPoids(); i++){
                if(!lTokens.isEmpty()){
                    arcPost.getPlaceDest().getTokens().add(lTokens.removeLast());
                }else{
                    Token tempToken = new Token();
                    arcPost.getPlaceDest().getTokens().add(tempToken);
                }


            }
        }

    return true;

    }


}
