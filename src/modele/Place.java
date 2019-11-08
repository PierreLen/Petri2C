package modele;

import java.util.HashSet;
import java.util.Set;

public class Place extends PetriObject {


    private Set<Token> tokens;


    public Place() {
        super();
        this.tokens = new HashSet<>();


    }

    public int getNbJetons() {
        return tokens.size();
    }

    public boolean addToken(Token t) {
        return this.tokens.add(t);
    }

    public boolean removeToken(Token t) {
        return this.tokens.remove(t);
    }





}
