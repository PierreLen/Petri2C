package modele;

import java.util.Objects;

public abstract class PetriObject {

    protected int id;

    private static int LAST_ID = 0;

    public PetriObject() {
        this.id = ++LAST_ID;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetriObject that = (PetriObject) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "id : " + id;
    }


}
