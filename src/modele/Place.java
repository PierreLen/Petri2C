package modele;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Place extends PetriObject {
    private static int LAST_PLACE_CREATED = 0;
    private List<Token> tokens;

    public Place() {
        super();
        this.tokens = new LinkedList<Token>();
        this.description = "P" + (++LAST_PLACE_CREATED);
    }

    public Place(Place place){
        this.id = place.id;
        this.tokens = new LinkedList<>(place.tokens);
        this.description = place.description;
    }

    public int getNbJetons() {
        return tokens.size();
    }

    public boolean addToken(Token t) {
        if (this.tokens.add(t)) {
            t.setCurrentPlace(this);
            return true;
        }
        return false;
    }

    public boolean removeToken(Token t) {
        return this.tokens.remove(t);
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public int getNbJetonsReserve() {
        int i = 0;
        for (Token t : this.tokens) {
            if (t.isReserve()) {
                i++;
            }
        }
        return i;
    }

    public int getNbJetonsNonReserve() {
        int i = 0;
        for (Token t : this.tokens) {
            if (!t.isReserve()) {
                i++;
            }
        }
        return i;
    }
}
