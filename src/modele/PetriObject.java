package modele;

import java.util.Objects;

public abstract class PetriObject {

    private static int LAST_ID = 0;
    protected int id;
    protected String description;

    public PetriObject() {
        this.id = ++LAST_ID;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetriObject that = (PetriObject) o;
        return id == that.id;
    }

    @Override
    public String toString() {
        return
                 description ;
    }
}
