package modele;

public class Token extends PetriObject{

    private Place currentPlace;
    private boolean reserve = false;

    public Token() {
        super();
    }

    public Token(Place currentPlace) {
        this();
        this.currentPlace = currentPlace;
    }


    public Place getCurrentPlace() {
        return currentPlace;
    }

    public void setCurrentPlace(Place currentPlace) {
        this.currentPlace = currentPlace;
    }

    public void reserver(){
        this.reserve = true;
    }

    public void liberer(){
        this.reserve = false;
    }

    public boolean isReserve() {
        return reserve;
    }


}
