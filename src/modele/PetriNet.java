package modele;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.*;

public class PetriNet {

    private Set<ArcPost> arcPosts;
    private Set<Token> tokens;
    private Set<ArcPre> arcPres;
    private Set<Place> places;
    private Set<Transition> transitions;
    private Map<Place, Map<Transition, Integer>> incidenceMatrix;

    public PetriNet() {
        this.arcPosts = new HashSet<>();
        this.tokens = new HashSet<>();
        this.arcPres = new HashSet<>();
        this.places = new HashSet<>();
        this.transitions = new HashSet<>();
        this.incidenceMatrix = null;
    }

    public void addPetriObjects(PetriObject... petriObjects) {
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

    public boolean franchir(Transition t) {
        LinkedList<Token> lTokens = new LinkedList<>();

        if (!t.isFranchissable())
            return false;

        for (ArcPre arcPre : t.getArcPres()) {
            Iterator<Token> it = arcPre.getPlaceO().getTokens().iterator();
            for (int i = 0; i < arcPre.getPoids(); i++) {
                lTokens.add(it.next());
                arcPre.getPlaceO().getTokens().remove(lTokens.get(lTokens.size() - 1));
            }
        }

        for (ArcPost arcPost : t.getArcPosts()) {
            for (int i = 0; i < arcPost.getPoids(); i++) {
                if (!lTokens.isEmpty()) {
                    arcPost.getPlaceDest().getTokens().add(lTokens.removeLast());
                } else {
                    Token tempToken = new Token();
                    arcPost.getPlaceDest().getTokens().add(tempToken);
                }
            }

            if (!lTokens.isEmpty()) {
                for (Token token : lTokens) {
                    this.removePetriObject(token);
                }
            }
        }
        return true;
    }

    /**
     * Calcul la matrice d'incidence du réseau
     */
    public void incidenceMatric() {

        Map<Place, Map<Transition, Integer>> matrix = new HashMap<>();

        for (Place p : this.places) {
            Map<Transition, Integer> transitionMatrix = new HashMap<>();
            for (Transition transition : this.transitions) {
                transitionMatrix.put(transition, 0);
            }
            matrix.put(p, transitionMatrix);
        }
        for (Place p : this.places) {
            Map temp = matrix.get(p);
            for (Transition transition : this.transitions) {

                for (ArcPost arcPost : transition.getArcPosts()) {
                    if (arcPost.getPlaceDest() == p) {
                        temp.put(transition, (int) temp.get(transition) - arcPost.getPoids());
                    }
                }
                for (ArcPre arcPre : transition.getArcPres()) {
                    if (arcPre.getPlaceO() == p) {
                        temp.put(transition, (int) temp.get(transition) + arcPre.getPoids());
                    }
                }

            }
            matrix.put(p, temp);
        }

        this.incidenceMatrix = matrix;

    }

    public Map<Place, Map<Transition, Integer>> getIncidenceMatrix() {
        return incidenceMatrix;
    }

    public Marquage getCurrentMarquage() {
        Marquage currentMarquage = new Marquage();
        for (Place p : this.places) {
            currentMarquage.addPlace(p, p.getNbJetons());
        }
        return currentMarquage;
    }

    public Map<Marquage, Set<Marquage>> getGraphMarquage(PetriNet petriNet) {
        Map<Marquage, Set<Marquage>> grapheMarquage = new HashMap<>();
        grapheMarquage = petriNet.getGrapheMarquageSetMap(grapheMarquage);


        return grapheMarquage;
    }




    private Set<Marquage> getAcces(Transition t) {
        Set<Marquage> newMarquagesAccesible = new HashSet<>();

            if (this.franchir(t)) {
                newMarquagesAccesible.add(this.getCurrentMarquage());
            }

        return newMarquagesAccesible;

    }

    //TODO ajouter des la récursivité
    // TODO ajouter la verification borné ou non avec la classe marquage
    private Map<Marquage, Set<Marquage>> getGrapheMarquageSetMap(Map<Marquage, Set<Marquage>> graphe){

        PetriNet pn = this;
        for (Transition t : this.getTransitionsFranchissable()){
            Marquage prevMarquage = pn.getCurrentMarquage();
            PetriNet pnTemp = pn;
            if(pn.franchir(t)){

                if(graphe.get(prevMarquage) == null){
                    //on as jamais été sur cette place
                    System.out.println("la1");
                    Set<Marquage> marquageList = new HashSet<>();
                    marquageList.add(pn.getCurrentMarquage());
                    graphe.put(prevMarquage, marquageList);

                }else{

                    //TODO a revoir

                    System.out.println("la2");
                    if(graphe.get(prevMarquage) == pn.getAcces(t)){
                        return graphe;
                    }else{

                        //gerer les nouveau marquage
                        Set<Marquage> grapheTemp =new HashSet<>();
                        grapheTemp.addAll(pn.getAcces(t));
                        graphe.put(prevMarquage, grapheTemp);
                    }
                }

            }
        }

        return graphe;
    }

    public Set<Transition> getTransitionsFranchissable(){
        Set<Transition> setTransition= new HashSet<>();
        for (Transition t : this.transitions){
            if(t.isFranchissable()){
                setTransition.add(t);
            }

        }
        return setTransition;
    }
}
