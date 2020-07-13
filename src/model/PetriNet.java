package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import org.json.*;
import sample.editorMain.DrawingZone;

public class PetriNet {

    private Set<Place> places;
    private Set<Transition> transitions;
    private Set<ArcPre> arcPres;
    private Set<ArcPost> arcPosts;
    private Set<Token> tokens;

    public PetriNet() {
        this.places = new HashSet<>();
        this.transitions = new HashSet<>();
        this.arcPres = new HashSet<>();
        this.arcPosts = new HashSet<>();
        this.tokens = new HashSet<>();
    }

    /**
     * Ajoute des objets au réseau de Petri
     *
     * @param petriNetComponents des objets du réseau
     */
    public void addPetriObject(PetriNetComponent... petriNetComponents) {
        for (PetriNetComponent petriNetObject : petriNetComponents) {
            if (petriNetObject instanceof ArcPre) {
                arcPres.add((ArcPre) petriNetObject);
            }
            if (petriNetObject instanceof model.ArcPost) {
                arcPosts.add((model.ArcPost) petriNetObject);
            }
            if (petriNetObject instanceof model.Place) {
                places.add((model.Place) petriNetObject);
            }
            if (petriNetObject instanceof model.Transition) {
                transitions.add((model.Transition) petriNetObject);
            }
            if (petriNetObject instanceof model.Token) {
                tokens.add((model.Token) petriNetObject);
            }
        }
    }

    /**
     * Retire un objet du réseau de pétri
     *
     * @param petriObject l'objet à retirer
     * @return true si l'objet est retiré, false sinon
     */
    public boolean removePetriObject(PetriNetComponent petriObject) {
        if (petriObject instanceof ArcPre) {
            return arcPres.remove(petriObject);
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

    public Marquage getCurrentMarquage() {
        Marquage currentMarquage = new Marquage();
        for (Place place : places) {
            currentMarquage.addPlace(place, place.getNbJetons());
        }
        return currentMarquage;
    }

    public Set<Transition> getTransitionsFranchissables() {
        Set<Transition> transitionsFranchissables = new HashSet<>();
        for (Transition transition : transitions) {
            if (transition.isFranchissable()) {
                transitionsFranchissables.add(transition);
            }
        }
        return transitionsFranchissables;
    }


    public boolean franchir(Transition t) {
        LinkedList<Token> lTokens = new LinkedList<>();

        if (!t.isFranchissable())
            return false;

        //        int indexGlobal
        for (ArcPre arcPre : t.getArcPres()) {

            for (int i = 0; i < arcPre.getPoids(); i++) {
                lTokens.add(arcPre.getOrigine().getTokens().get(0));
            }

            arcPre.getOrigine().getTokens().removeAll(lTokens);
        }
        for (ArcPost arcPost : t.getArcPosts()) {
            for (int i = 0; i < arcPost.getPoids(); i++) {
                if (!lTokens.isEmpty()) {
                    arcPost.getDestination().getTokens().add(lTokens.removeLast());
                } else {
                    Token tempToken = new Token();
                    tempToken.setCurrentPlace(arcPost.getDestination());
                    arcPost.getDestination().getTokens().add(tempToken);
                    this.tokens.add(tempToken);
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

    public void restoreFromMarquage(Marquage dump) {
        ArrayList<Token> tokenList = new ArrayList<>(this.tokens);
        int globalIndex = 0;
        for (Place place : dump.getPlaces().keySet()) {
            place.viderPlace();
            // pour chaque token dans la place
            for (int i = 0; i < dump.getPlaces().get(place); i++) {
                try {
                    // on essaye d'en associer un qui existe déja
                    tokenList.get(globalIndex).setCurrentPlace(place);
                    place.addToken(tokenList.get(globalIndex));
                } catch (IndexOutOfBoundsException e) {
                    // sinon on le crée
                    Token t = new Token();
                    t.setCurrentPlace(place);
                    this.tokens.add(t);
                    place.addToken(t);
                }
                globalIndex++;
            }
        }
    }

    public Set<Transition> getTransitions() {
        return transitions;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public String gettoJSON (){
        String json;
        json = "{" + "\"petriNet\": {\"place\":[";

        JSONObject petrinet = new JSONObject();

        JSONObject places = new JSONObject();
        JSONArray arrayPlace = new JSONArray();
        for(Place p : this.places){
            JSONObject place = new JSONObject();
            place.put("Description", p.getDescription());
            place.put("id", p.getPnId());
            place.put("X", p.getX());
            place.put("Y",p.getY());
            arrayPlace.put(place);

        }
        places.put("place",arrayPlace);



        JSONObject transitions = new JSONObject();
        JSONArray arrayTransition = new JSONArray();
        for(Transition t : this.transitions){
            JSONObject transition = new JSONObject();
            transition.put("Description", t.getDescription());
            transition.put("id", t.getPnId());
            transition.put("X", t.getX());
            transition.put("Y",t.getY());
            arrayTransition.put(transition);

        }
        transitions.put("transition",arrayTransition);

        JSONObject arcPres = new JSONObject();
        JSONArray arrayArcPre = new JSONArray();
        for(ArcPre a : this.arcPres){
            JSONObject arc = new JSONObject();
            arc.put("id", a.getPnId());
            arc.put("poids", a.getPoids());
            arc.put("placeOrigine",a.getOrigine().getDescription());
            arc.put("TransitionDestination",a.getDestination().getDescription());
            arrayArcPre.put(arc);

        }
        arcPres.put("arcPre",arrayArcPre);


        petrinet.opt("petriNet");
        petrinet.put("petriNet",places).accumulate("petriNet",transitions).accumulate("petriNet",arcPres);
        return petrinet.toString();
    }

    public void fromJSON(DrawingZone crt){
        String doc = "{\"petriNet\":[{\"place\":[{\"Description\":\"P2\",\"X\":326,\"Y\":153,\"id\":1},{\"Description\":\"P1\",\"X\":211,\"Y\":238,\"id\":2}]},{\"transition\":[{\"Description\":\"T1\",\"X\":283,\"Y\":400,\"id\":3}]},{\"arcPre\":[{\"TransitionDestination\":\"T1\",\"poids\":1,\"placeOrigine\":\"P2\",\"id\":198},{\"TransitionDestination\":\"T1\",\"poids\":1,\"placeOrigine\":\"P1\",\"id\":4}]}]}\n";
        //String doc = "{\"petriNet\": {\"place\":[{\"Description\":\"P2\",\"id\":2,\"X\":292,\"Y\":200},{\"Description\":\"P1\",\"id\":1,\"X\":159,\"Y\":173}],\"Transition\":[{\"Description\":\"T1\",\"id\":3,\"X\":128,\"Y\":298}]}}";
        JSONObject obj = new JSONObject(doc);
        System.out.println(obj.toString());
        System.out.println(obj.getJSONArray("petriNet").get(0));
        JSONArray arrayJSON = obj.getJSONArray("petriNet").getJSONObject(0).getJSONArray("place");
        for(int i =0;i<arrayJSON.length();i++){
            JSONObject temp = arrayJSON.getJSONObject(i);
            Place pTemp = new Place(temp.getInt("X"),temp.getInt("Y"),crt,temp.getString("Description"));
            //System.out.println(pTemp.getX());
            crt.addListenerPlace(pTemp);
            pTemp.update();
        }
        arrayJSON = obj.getJSONArray("petriNet").getJSONObject(1).getJSONArray("transition");
        for (int i =0; i<arrayJSON.length();i++){
            JSONObject temp = arrayJSON.getJSONObject(i);
            Transition tTemp = new Transition(temp.getInt("X"),temp.getInt("Y"),crt,temp.getString("Description"));
            crt.addListenerTransition(tTemp);
            tTemp.updateColor(false);
        }
        arrayJSON = obj.getJSONArray("petriNet").getJSONObject(2).getJSONArray("arcPre");
        for (int i =0; i<arrayJSON.length();i++){
            JSONObject temp = arrayJSON.getJSONObject(i);

            ArcPre arcPreTemp = new ArcPre(temp.getInt("poids"),crt.getPetriNet().getPlaceByDescription(temp.getString("placeOrigine")),crt.getPetriNet().getTransitionByDescription(temp.getString("TransitionDestination")));
            crt.addPetriNet(arcPreTemp);
            crt.addPetriNetPaneChild(arcPreTemp);
            crt.getPetriNet().getTransitionByDescription(temp.getString("TransitionDestination")).addArcPre(arcPreTemp);
            arcPreTemp.update();
        }

    }


    /**
     * ne prends pas en compte si la place n'existe pas
     * @param description
     * @return
     */
    private Place getPlaceByDescription(String description){
        for (Place p : places){
            if(p.getDescription().equals(description)){
                return p;
            }
        }
        return null;
    }

    private Transition getTransitionByDescription(String description){
        for (Transition t : transitions){
            if(t.getDescription().equals(description)){
                return t;
            }
        }
        return null;
    }
}
