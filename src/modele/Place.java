package modele;

import java.util.HashSet;
import java.util.Set;

public class Place extends PetriObject {

    private Set<ArcPre> arcPres;
    private Set<Token> tokens;


    public Place() {
        super();
        this.tokens = new HashSet<>();
        this.arcPres = new HashSet<>();

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

    public boolean addArcPre(ArcPre arcPre){
        return arcPres.add(arcPre);
    }

    public boolean removeArcPre(ArcPre arcPre){
        return arcPres.remove(arcPre);
    }




}
