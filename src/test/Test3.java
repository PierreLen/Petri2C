package test;

import modele.*;

public class Test3 {

    public static void main(String[] args) {
        PetriNet pn = new PetriNet();
        Token token1 = new Token();
        token1.setDescription("token1");
        Place p0 = new Place();
        Place p1 = new Place();
        Place p2 = new Place();
        Place p3 = new Place();
        Place p4 = new Place();

        Transition t0 = new Transition();
        Transition t1 = new Transition();
        Transition t2 = new Transition();
        Transition t3 = new Transition();
        Transition t4 = new Transition();

        ArcPre arcPre0 = new ArcPre(t0, p0);
        ArcPre arcPre1 = new ArcPre(t1, p1);
        ArcPre arcPre2 = new ArcPre(t2, p2);
        ArcPre arcPre3 = new ArcPre(t3, p3);
        ArcPre arcPre4 = new ArcPre(t4, p4);
        ArcPost arcPost0 = new ArcPost(t0, p1);
        ArcPost arcPost1 = new ArcPost(t1, p2);
        ArcPost arcPost2 = new ArcPost(t1, p3);
        ArcPost arcPost3 = new ArcPost(t2, p4);
        ArcPost arcPost4 = new ArcPost(t4, p0);
        ArcPost arcPost5 = new ArcPost(t3, p0);
        arcPre0.setPoids(2);

        pn.addPetriObjects(
                p0, p1, p2, p3, p4,
                t0, t1, t2, t3, t4,
                arcPost0, arcPost1, arcPost2, arcPost3, arcPost4, arcPost5,
                arcPre0, arcPre1, arcPre2, arcPre3, arcPre4,
                token1
        );

        p1.addToken(token1);

        GraphMarquage graphMarquage = new GraphMarquage(pn);
        System.out.println(graphMarquage.getGrapheDeMarquage());
        System.out.println("End");

    }
}
