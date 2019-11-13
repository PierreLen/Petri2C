package modele;

public class ArcPost extends Arc {

    private Transition transitionO;
    private Place placeDest;

    public ArcPost(int poids, Transition transitionO, Place placeDest) {
        super(poids);
        this.transitionO = transitionO;
        this.placeDest = placeDest;
    }

    public ArcPost(Transition transitionO, Place placeDest) {
        super();
        this.transitionO = transitionO;
        transitionO.addArcPost(this);
        this.placeDest = placeDest;
    }

//    public ArcPost(ArcPost arcPost){
//        this.placeDest =
//    }

    public Transition getTransitionO() {
        return transitionO;
    }

    public void setTransitionO(Transition transitionO) {
        this.transitionO = transitionO;
    }

    public Place getPlaceDest() {
        return placeDest;
    }

    public void setPlaceDest(Place placeDest) {
        this.placeDest = placeDest;
    }
}
