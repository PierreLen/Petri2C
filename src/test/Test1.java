package test;

import modele.*;

public class Test1 {

    public static void main(String[] args) {
        PetriNet pn = new PetriNet();

        Token token1 = new Token();
        token1.setDescription("token1");

        Place p1 = new Place();
        Place p2 = new Place();
        Place p3 = new Place();

        Transition t1 = new Transition();
        Transition t2 = new Transition();
        Transition t3 = new Transition();

        ArcPost ap1 = new ArcPost(t3, p1);
        ArcPost ap2 = new ArcPost(t1, p2);
        ArcPost ap3 = new ArcPost(t2, p3);
        ArcPre apr1 = new ArcPre(t1, p1);
        ArcPre apr2 = new ArcPre(t2, p2);
        ArcPre apr3 = new ArcPre(t3, p3);
        p1.addToken(token1);


        pn.addPetriObjects(token1, p1, p2, t1, t2, ap1, ap2, apr1, apr2, apr3, ap3, p3, t3);
        /*
        System.out.println(p1.getNbJetons());
        System.out.println(p2.getNbJetons());
        //System.out.println(pn.franchir(t2));
        pn.franchir(t1);
        System.out.println(p1.getNbJetons());
        System.out.println(p2.getNbJetons());
        */
        //pn.incidenceMatric();
        //System.out.println(pn.getIncidenceMatrix());
//        Marquage M1 = pn.getCurrentMarquage();
//        pn.franchir(t1);
//        Marquage M2 = pn.getCurrentMarquage();
        //System.out.println(M1.equals(M2));
        GraphMarquage graphMarquage = new GraphMarquage(pn);
        System.out.println(graphMarquage.getGrapheDeMarquage());
        System.out.println(pn.getGraphMarquage(pn));
    }
}
