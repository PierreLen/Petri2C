package test;

import modele.*;

import java.util.Map;
import java.util.Set;

public class Test3 {

    public static void main(String[] args) {
        PetriNet pn = new PetriNet();

        Place p1 = new Place("P1");
        Place p2 = new Place("P2");
        Place p3 = new Place("P3");
        Place p4 = new Place("P4");
        Place p5 = new Place("P5");

        Transition t1 = new Transition();
        Transition t2 = new Transition();
        Transition t3 = new Transition();
        Transition t4 = new Transition();
        Transition t5 = new Transition();

        Token token1 = new Token();
        token1.setDescription("token1");

        ArcPre arcPre1 = new ArcPre(t1, p1);
        ArcPre arcPre2 = new ArcPre(t2, p2);
        ArcPre arcPre3 = new ArcPre(t3, p3);
        ArcPre arcPre4 = new ArcPre(t4, p4);
        ArcPre arcPre5 = new ArcPre(t5, p5);
        ArcPost arcPost0 = new ArcPost(t5, p1);
        ArcPost arcPost1 = new ArcPost(t1, p2);
        ArcPost arcPost2 = new ArcPost(t1, p3);
        ArcPost arcPost3 = new ArcPost(t2, p4);
        ArcPost arcPost4 = new ArcPost(t4, p5);
        ArcPost arcPost5 = new ArcPost(t3, p5);
        arcPre5.setPoids(2);

        pn.addPetriObjects(
                p1, p2, p3, p4, p5,
                t1, t2, t3, t4, t5,
                arcPost0, arcPost1, arcPost2, arcPost3, arcPost4, arcPost5,
                arcPre5, arcPre1, arcPre2, arcPre3, arcPre4,
                token1
        );

        p1.addToken(token1);
        GraphMarquage graphMarquage = new GraphMarquage(pn);
        final Map<Marquage, Set<Marquage>> grapheDeMarquage = graphMarquage.getGrapheDeMarquage();
        for (Marquage marquage : grapheDeMarquage.keySet()) {
            System.out.println(marquage);
            System.out.println("\t" + grapheDeMarquage.get(marquage));
        }
        System.out.println(grapheDeMarquage);
        System.out.println("End");

    }
}
