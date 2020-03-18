package modele;

public class Token extends PetriObject {

    private Place currentPlace;
    private boolean reserve = false;

    public Token(Place currentPlace) {
        this();
        this.currentPlace = currentPlace;
    }

    public Token() {
        super();
    }

    public Token(Token token) {
        this.id = token.id;
        this.currentPlace = token.currentPlace;
        this.description = token.description;
    }


    public Place getCurrentPlace() {
        return currentPlace;
    }

    public void setCurrentPlace(Place currentPlace) {
        this.currentPlace = currentPlace;
    }

    public void removePlace () {this.currentPlace = null;}

    public void reserver() {
        this.reserve = true;
    }

    public void liberer() {
        this.reserve = false;
    }

    public boolean isReserve() {
        return reserve;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                '}';
    }
}
