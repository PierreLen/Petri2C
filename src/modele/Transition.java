package modele;

import java.util.HashSet;
import java.util.Set;

public class Transition extends PetriObject {

    private Set<ArcPost> arcPosts;
    private boolean franchissable = true;

    public Transition() {
        super();
        this.arcPosts = new HashSet<>();
    }

    public boolean addArcPost(ArcPost arcPost) {
        return arcPosts.add(arcPost);

    }

    public boolean removeArcPost(ArcPost arcPost) {
        return arcPosts.remove(arcPost);
    }
}
