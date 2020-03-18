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
        Set<Transition> transitionsFranchissable = net.getTransitionsFranchissable();
        Map<Place, Integer> dumpMap = net.dumpState();

        for (Transition transition : transitionsFranchissable) {
//            PetriNet tempPn = new PetriNet(net);
//            tempPn.franchir(transition);
            net.franchir(transition);
            marquages.add(net.getCurrentMarquage());
            getMarquage(net);
            net.restore(dumpMap);
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
