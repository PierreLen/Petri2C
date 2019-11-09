package modele;

public abstract class Arc extends PetriObject {

    protected int poids;

    public Arc(int poids) {
        this();
        this.poids = poids;
    }

    public Arc() {
        super();
        this.poids = 1;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

}
