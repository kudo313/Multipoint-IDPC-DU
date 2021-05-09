package Multipoint;
import  java.util.ArrayList;


public class Domain {
    public int name;
    public boolean sourceDomain = false;
    public boolean destiDomain = false;
    public ArrayList<Node> vertrexList = new ArrayList<Node>();
    public ArrayList<Edge> adjacencyList = new ArrayList<Edge>();
    public ArrayList<Edge> backAdjacencyList  = new ArrayList<Edge>();
    public boolean visited = false;
    public Node sourceNode ;
    public ArrayList<Node> tmpDestiNode = new ArrayList<Node>();
    public ArrayList<Node> destiNode = new ArrayList<Node>();

    public Domain(int name){
        this.name = name;

    }

}
