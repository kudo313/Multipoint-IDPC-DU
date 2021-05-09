package Multipoint;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> ar = new ArrayList<Integer>();


        for (int i = 0; i < 1; i++) {

            GraphWithDomain gr = new GraphWithDomain("idpc_ndu_302_12_4930");
//            gr.simplify();
            SteinerWithDomain st = new SteinerWithDomain(gr);
            ar.add(st.bestWeight);
        }
        System.out.println();
        for (int i = 0; i < 1; i++){
            System.out.print(ar.get(i) + " ");
        }

    }
}
