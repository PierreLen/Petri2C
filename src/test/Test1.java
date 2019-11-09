package test;

import modele.*;

public class Test1 {

    public static void main(String[] args) {
        PetriNet pn = new PetriNet();

        Token token1 = new Token();
        token1.setDescription("token1");

        Place p1 = new Place();
        p1.setDescription("p1");
        Place p2 = new Place();
        p2.setDescription("p2");

        Transition t1 = new Transition();
        Transition t2 = new Transition();

        ArcPost ap1 = new ArcPost(t2,p1);
        t2.addArcPost(ap1);
        ArcPost ap2 = new ArcPost(t1, p2);
        t1.addArcPost(ap2);

        ArcPre apr1 = new ArcPre(t1, p1);
        t1.addArcPre(apr1);
        ArcPre apr2 = new ArcPre(t2,p2);
        t2.addArcPre(apr2);

        p1.addToken(token1);



        pn.addPetriObjects(token1,p1,p2,t1,t2,ap1,ap2,apr1,apr2);
        /*
        System.out.println(p1.getNbJetons());
        System.out.println(p2.getNbJetons());
        //System.out.println(pn.franchir(t2));
        pn.franchir(t1);
        System.out.println(p1.getNbJetons());
        System.out.println(p2.getNbJetons());
        */
        System.out.println(pn.incidenceMatric());
    }
}
