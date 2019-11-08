package modele;

public class ArcPre extends Arc {

    private Transition transitionDest;
    private Place placeO;

    public ArcPre(Transition transitionDest, Place placeO) {
        super();
        this.transitionDest = transitionDest;
        this.placeO = placeO;
    }

    public ArcPre(int poids, Transition transitionDest, Place placeO) {
        super(poids);
        this.transitionDest = transitionDest;
        this.placeO = placeO;
    }

    public Transition getTransitionDest() {
        return transitionDest;
    }

    public Place getPlaceO() {
        return placeO;
    }

    public void setTransitionDest(Transition transitionDest) {
        this.transitionDest = transitionDest;
    }

    public void setPlaceO(Place placeO) {
        this.placeO = placeO;
    }

}
