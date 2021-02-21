package Multipoint;
import java.util.ArrayList;

public class JourneyWithDomain {
    public ArrayList<Domain> existDomain = new ArrayList<Domain>();
    public ArrayList<Edge> tempTree = new ArrayList<Edge>();
    public int weight;
    public boolean equals(JourneyWithDomain jn){
        boolean tmp = true;
        for (Edge ed : jn.tempTree){
            if (this.tempTree.contains(ed) == false){
                tmp = false;
            }
        }
        return tmp;
    }
}
