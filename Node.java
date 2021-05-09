package Multipoint;
import java.util.ArrayList;

public class Node {
    public int name;
    public Domain domain;
    public boolean sourceNode = false;
    public boolean destiNode = false;
    public boolean dead = false;
    public boolean visited = false;
    public Node(int name){
        this.name = name;

    }
    public ArrayList<Edge> adjacencyList = new ArrayList<Edge>();
    public ArrayList<Edge> backAdjacencyList = new ArrayList<Edge>();

}
