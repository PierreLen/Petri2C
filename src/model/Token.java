package model;

public class Token extends PetriNetComponent {

    private Place currentPlace;
    private boolean reserve = false;

    public Token(Place currentPlace) {
        super();
        this.currentPlace = currentPlace;
    }

    public Token() {
        super();
    }

    public Token(Token token) {
        this.pnId = token.pnId;
        this.currentPlace = token.currentPlace;
        this.description = token.description;
    }

    public Place getCurrentPlace() {
        return currentPlace;
    }

    public void setCurrentPlace(Place currentPlace) {
        this.currentPlace = currentPlace;
    }

    public void removePlace() {
        this.currentPlace = null;
    }

    public void reserver() {
        reserve = true;
    }

    public void liberer() {
        reserve = false;
    }
}
