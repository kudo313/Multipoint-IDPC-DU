package Multipoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class test {
    public static void main(String[] args) {
        GraphWithDomain gr = new GraphWithDomain("idpc_ndu_52_6_204");
        gr.simplify();
        for (Node node : gr.vertexList){
            System.out.print(node.name + " ");
        }
//        Domain dm = gr.domainList.get(4);
//        for (Node nd : dm.vertrexList){
//            if (nd.name == 14 ) {
//                dm.sourceNode = nd;
//            }
//            if (nd.name == 41) dm.destiNode.add(nd);
////            if (nd.name == 44) dm.destiNode.add(nd);
//
//        }
//        GraphInDomain tmpGr = new GraphInDomain(gr,dm);
//        for (Node u : tmpGr.vertexList){
//            if (u.name == dm.sourceNode.name){
//                tmpGr.sourceNode = u;
//            }
//        }
//        for (Node u : dm.destiNode){
//            for (Node v : tmpGr.vertexList){
//                if (v.name == u.name){
//                    tmpGr.destiNode.add(v);
//                }
//            }
//        }
//        SteinerInDomain st = new SteinerInDomain(tmpGr);
//        System.out.println(st.out);
//
//    ArrayList<NotAliveNodes> ar  = new ArrayList<NotAliveNodes>();
//    NotAliveNodes a1 = new NotAliveNodes();
//    a1.elementNotAlive.add(gr.vertexList.get(2));
//    NotAliveNodes a2 = new NotAliveNodes();
//    NotAliveNodes a3 = new NotAliveNodes();
//    NotAliveNodes a4 = new NotAliveNodes();
//
//    a2.elementNotAlive.add(gr.vertexList.get(13));
//    a3.elementNotAlive.add(gr.vertexList.get(24));
//    a4.elementNotAlive.add(gr.vertexList.get(21));
//    a1.elementNotAlive.add(gr.vertexList.get(20));
//    a2.elementNotAlive.add(gr.vertexList.get(18));
//    ar.add(a1);
//    ar.add(a2);
//    ar.add(a3);
//    ar.add(a4);
//    for (int i = 0 ; i < ar.size(); i++){
//        for (int k = 0 ; k  < ar.get(i).elementNotAlive.size(); k++){
//            System.out.println(ar.get(i).elementNotAlive.get(k).name);
//        }
//    }

    }
}
