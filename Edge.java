package Multipoint;

public class Edge {
    public Node InNode;
    public  Node OutNode;
    public int weight;
    public boolean dead = false;
    public double phero = 0.01;
}
