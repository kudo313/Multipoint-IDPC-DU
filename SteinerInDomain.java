package Multipoint;

import java.util.ArrayList;
import java.util.Random;

public class SteinerInDomain {
    public Domain domain;
    public GraphInDomain graph;
    public int numberOfHerds = 50;
    public int numberOfGener = 20;
    public double pheroEvaporation = 0.1;
    public ArrayList<Double> numerator;
    public double denominator;
    public boolean success ;
    public int alpha = 1;
    public int beta = 3;
    public boolean alive = true;
    public boolean step = false;
    public int numberOfDeath = 0;
    public boolean out = false;
    public int bestWeight = 100000;
    public ArrayList<JourneyInDomain> journeys = new ArrayList<JourneyInDomain>();
    public JourneyInDomain finalJourney = new JourneyInDomain();
    public NotAliveNodes tupleNotAlive = new NotAliveNodes();

    public SteinerInDomain(GraphInDomain graph){
        this.graph = graph;
        this.domain = graph.domain;
        Random rand = new Random();
//        System.out.println(domain.name);
//        System.out.print(graph.sourceNode.name + " ");
//        for (Node u : graph.destiNode){
//            System.out.print(u.name + " ");
//        }
//        System.out.println();
        if (graph.destiNode.size() == 1 && graph.destiNode.get(0) == graph.sourceNode){
            System.out.println("first edi");
            finalJourney.existNodes.add(graph.sourceNode);
            finalJourney.weight = 0;
            bestWeight = 0;
        }
        else {
            for (Node v : graph.vertexList){
                for (Edge u : v.adjacencyList){
                    u.phero = 0.01;
                }
            }

            for (int i = 0; i < numberOfGener; i++) {

                if (out){
                    break;
                }
                for (int j = 0; j < numberOfHerds; j++) {

                    JourneyInDomain tmpJourney = new JourneyInDomain();
                    for (Node tmp : graph.vertexList) {
                        tmp.visited = false;
                    }
                    for (int t = 0; t < graph.destiNode.size(); t++) {

                        Node NodeVisiting = graph.destiNode.get(t);
                        if (NodeVisiting.visited == false && NodeVisiting != graph.sourceNode) {
                            PathInDomain tmpPath = new PathInDomain();
                            Node tmpNode = NodeVisiting;
                            tmpPath.existNode.add(NodeVisiting);
                            graph.sourceNode.visited = true;

                            success = false;
                            alive = true;
                            while (alive) {
                                step = false;
                                numerator = new ArrayList<Double>();
                                denominator = 0;
                                double r = rand.nextDouble();
                                double tmpRate = 0;
                                for (int k = 0; k < tmpNode.backAdjacencyList.size(); k++) {
                                    Edge tmpEdge = tmpNode.backAdjacencyList.get(k);
                                    Double tmpNum = Math.pow(tmpEdge.phero, alpha) / Math.pow(tmpEdge.weight, beta);
                                    numerator.add(tmpNum);
                                    denominator += tmpNum;
                                }
                                for (int k = 0; k < tmpNode.backAdjacencyList.size(); k++) {
                                    Edge tmpEdge = tmpNode.backAdjacencyList.get(k);
                                    tmpRate += numerator.get(k) / denominator;
                                    if (tmpRate > r && tmpPath.existNode.contains(tmpEdge.InNode) == false) {
                                        tmpPath.tempPath.add(tmpEdge);
                                        tmpPath.weight += tmpEdge.weight;
                                        step = true;
                                        if (tmpEdge.InNode.visited == true) {
                                            success = true;
                                            break;
                                        } else {
                                            tmpPath.existNode.add(tmpEdge.InNode);
                                            tmpNode = tmpEdge.InNode;
                                            break;
                                        }

                                    }
                                }
                                if (success == true) {
//                                    System.out.println(tmpPath.weight + "...");
                                    tmpJourney.weight += tmpPath.weight;
                                    for (Node tmp : tmpPath.existNode) {
                                        tmp.visited = true;
                                        tmpJourney.existNodes.add(tmp);
                                    }
                                    for (Edge tmp : tmpPath.tempPath){
                                        tmpJourney.tempTree.add(tmp);
                                    }
                                    alive = false;
                                }
                                if (step == false){
                                    numberOfDeath+=1;
                                    alive = false;
                                    t--;
                                    break;
                                }

                            }
                        }
                        if (numberOfDeath > 1000000){
                            out = true;
                            tupleNotAlive.elementNotAlive.add(graph.sourceNode);
                            tupleNotAlive.elementNotAlive.add(graph.destiNode.get(t+1));
                            break;
                        }
                    }
                    if (out ){
                        break;
                    }
//                    System.out.println("....,,,");
//                    for (Edge ed : tmpJourney.tempTree){
//                        System.out.println(ed.InNode.name + "-" + ed.OutNode.name);
//                    }
                    journeys.add(tmpJourney);
                    if (tmpJourney.weight < bestWeight){
                        bestWeight = tmpJourney.weight;
                        finalJourney = tmpJourney;
                    }
                }
                for (Node tmp : graph.vertexList){
                    for (Edge tmpE : tmp.adjacencyList){
                        tmpE.phero *= (1 - pheroEvaporation);
                    }
                }
                for (JourneyInDomain jn : journeys){
                    for (Edge ed : jn.tempTree){
                        ed.phero += 1/jn.weight;
                    }
                }
            }

        }
//        for (Edge ed : finalJourney.tempTree){
//            System.out.print(" " + ed.InNode.name+ "-" + ed.OutNode.name);
//        }
//        System.out.println();
//        if (out) System.out.println("dead");
//        System.out.println(bestWeight);
//        for (Node u : finalJourney.existNodes){
//            System.out.print(u.name + " ");
//        }
//        System.out.println();
    }
}
