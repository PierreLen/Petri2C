package modele;

import java.util.HashSet;
import java.util.Set;

public class Transition extends PetriObject {
    private static int LAST_TRANSITION_CREATED = 0;

    private Set<ArcPost> arcPosts;
    private Set<ArcPre> arcPres;

    public Transition(Transition t) {
        this.arcPosts = new HashSet<>(t.arcPosts);
        this.arcPres = new HashSet<>(t.arcPres);
        this.id = t.id;
        this.description = t.description;
    }

    public Transition() {
        super();
        this.arcPosts = new HashSet<>();
        this.arcPres = new HashSet<>();
        this.description = "T" + (++LAST_TRANSITION_CREATED);
    }

    public boolean addArcPost(ArcPost arcPost) {
        return arcPosts.add(arcPost);

    }

    public boolean removeArcPost(ArcPost arcPost) {
        return arcPosts.remove(arcPost);
    }


    public boolean addArcPre(ArcPre arcPre) {

        return arcPres.add(arcPre);
    }

    public boolean removeArcPre(ArcPre arcPre) {

        return arcPres.remove(arcPre);
    }

    public Set<ArcPost> getArcPosts() {
        return arcPosts;
    }

    public boolean isFranchissable() {
        for (ArcPre arcPre : this.getArcPres()) {
            if (arcPre.getPlaceO().getNbJetons() < arcPre.getPoids()) {
//                System.out.println(arcPre.getPlaceO().getNbJetons());
                return false;
            }
        }
        return true;
    }

    public Set<ArcPre> getArcPres() {
        return arcPres;
    }
}
