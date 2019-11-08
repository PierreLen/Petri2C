package modele;

import java.util.HashSet;
import java.util.Iterator;
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

    public Set<Token> getTokens() {
        return tokens;
    }

    public int getNbJetonsReserve(){
        int i = 0;
        for(Token t: this.tokens){
            if (t.isReserve()){
                i++;
            }

        }
        return i;
    }

    public int getNbJetonsNonReserve(){
        int i = 0;
        for(Token t: this.tokens){
            if (!t.isReserve()){
                i++;
            }

        }
        return i;
    }







}
