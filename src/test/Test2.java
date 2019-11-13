package test;

import modele.*;

public class Test2 {

    public static void main(String[] args) {
        PetriNet pn = new PetriNet();
        Token token1 = new Token();
        token1.setDescription("token1");
        Token token2 = new Token();
        token1.setDescription("token2");


        Place p1 = new Place();
        Place p2 = new Place();
        Transition t1 = new Transition();
        Transition t2 = new Transition();
        Transition t3 = new Transition();

        ArcPre arcPre1 = new ArcPre(t1, p1);
        ArcPre arcPre2 = new ArcPre(t2, p2);
        ArcPre arcPre3 = new ArcPre(t3, p2);

        ArcPost arcPost1 = new ArcPost(t1, p2);
        ArcPost arcPost2 = new ArcPost(t2, p1);
        ArcPost arcPost3 = new ArcPost(t3, p1);
        p1.addToken(token1);
        p1.addToken(token2);
        pn.addPetriObjects(
                p1, p2,
                t1, t2, t3,
                arcPost1, arcPost2, arcPost3,
                arcPre1, arcPre2, arcPre3);
        GraphMarquage graphMarquage = new GraphMarquage(pn);
        System.out.println(graphMarquage.getGrapheDeMarquage());
        System.out.println("End");
    }

}
