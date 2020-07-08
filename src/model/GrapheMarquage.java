package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GrapheMarquage {

    PetriNet petriNet;

    Map<Marquage, Set<Marquage>> grapheDeMarquage = new HashMap<>();

    public GrapheMarquage(PetriNet petriNet) {
        this.petriNet = petriNet;
    }

    public Map<Marquage, Set<Marquage>> getGrapheDeMarquage() {
        remplirGraphDeMarquage(this.petriNet);
        return grapheDeMarquage;
    }

    /**
     *
     * @param net pas convaincu par le paramètre exterieur
     */
    public void remplirGraphDeMarquage(PetriNet net) {
        Set<Marquage> marquages = new HashSet<>();
        Marquage currentMarquage = net.getCurrentMarquage();
        if (!grapheDeMarquage.containsKey(currentMarquage))
            grapheDeMarquage.put(currentMarquage, marquages);
        else return;
        Set<Transition> transitionsFranchissables= net.getTransitionsFranchissables();
        Marquage dump = net.getCurrentMarquage();
        for (Transition transitionsFranchissable : transitionsFranchissables) {
            net.franchir(transitionsFranchissable);
            marquages.add(net.getCurrentMarquage());
            remplirGraphDeMarquage(net);
            net.restoreFromMarquage(dump);
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (Marquage marquage : grapheDeMarquage.keySet()) {
            str += marquage.toString() + '\n';
        }
        return str;
    }
}
