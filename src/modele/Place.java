package modele;

import java.util.HashSet;
import java.util.Set;

public class Place extends PetriObject {
    private static int LAST_PLACE_CREATED = 0;
    private Set<Token> tokens;

    public Place() {
        super();
        this.tokens = new HashSet<>();
        this.description = "P" + (++LAST_PLACE_CREATED);
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

    public Set<Token> getTokens() {
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
