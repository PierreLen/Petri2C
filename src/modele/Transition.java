package modele;

import java.util.HashSet;
import java.util.Set;

public class Transition extends PetriObject {

    private Set<ArcPost> arcPosts;
    private Set<ArcPre> arcPres;
    private boolean franchissable = true;

    public Transition() {
        super();
        this.arcPosts = new HashSet<>();
        this.arcPres = new HashSet<>();
    }

    public boolean addArcPost(ArcPost arcPost) {
        return arcPosts.add(arcPost);

    }

    public boolean removeArcPost(ArcPost arcPost) {
        return arcPosts.remove(arcPost);
    }


    public boolean addArcPre(ArcPre arcPre){

        return arcPres.add(arcPre);
    }

    public boolean removeArcPre(ArcPre arcPre){

        return arcPres.remove(arcPre);
    }
}
