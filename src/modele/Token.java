package modele;

public class Token extends PetriObject{

    private Place currentPlace;

    public Token() {
        super();
    }

    public Token(Place currentPlace) {
        this();
        this.currentPlace = currentPlace;
    }



}
