package modele;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphMarquage {

    PetriNet petriNet;
    Map<Marquage, Set<Marquage>> grapheDeMarquage = new HashMap<>();

    public GraphMarquage(PetriNet petriNet) {
        this.petriNet = petriNet;
    }

    public Map<Marquage, Set<Marquage>> getGrapheDeMarquage() {
        getMarquage(petriNet);
        return grapheDeMarquage;
    }

    public void getMarquage(PetriNet net) {
        Set<Marquage> marquages = new HashSet<>();
        Marquage currentMarquage = net.getCurrentMarquage();
        if (!grapheDeMarquage.containsKey(currentMarquage))
            grapheDeMarquage.put(currentMarquage, marquages);
        else return;
        Set<Transition> transitionsFranchissable = petriNet.getTransitionsFranchissable();
        for (Transition transition : transitionsFranchissable) {
            PetriNet tempPn = new PetriNet(net);
            tempPn.franchir(transition);
            marquages.add(tempPn.getCurrentMarquage());
            getMarquage(tempPn);
        }
    }


}
