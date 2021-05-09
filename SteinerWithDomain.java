package Multipoint;
import  java.util.ArrayList;
import java.util.Random;
public class SteinerWithDomain {
    public GraphWithDomain graph;
    public int numberOfHerds = 50;
    public int numberOfGener = 50;
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
    public boolean findLiveWay= false;
    public boolean changeBest = false;
    public ArrayList<JourneyWithDomain> journeys = new ArrayList<JourneyWithDomain>();
    public ArrayList<JourneyWithDomain> deathJourneys = new ArrayList<JourneyWithDomain>();
    public ArrayList<NotAliveNodes> listNotAlive = new ArrayList<NotAliveNodes>();
    public JourneyInDomain finalJourney = new JourneyInDomain();
    public int count ;
    public SteinerWithDomain(GraphWithDomain graph){
        this.graph = graph;
        Random rand = new Random();
        for (Domain dm : graph.domainList){
            dm.destiNode.clear();
        }
        for (Node u : graph.destiNode){
            u.domain.destiNode.add(u);
        }
        for (int i = 0; i < numberOfGener;i++){
            for (int j = 0; j < numberOfHerds; j++){
                System.out.println(".......");
                count++;
                System.out.println(count);
                JourneyWithDomain tmpJourney = new JourneyWithDomain();
                for (Domain tmp : graph.domainList){
                    tmp.visited = false;
                }
                for (int t = 0; t < graph.destiDomain.size(); t++){
                    for (Domain tmp : graph.domainList){
                        tmp.tmpDestiNode.clear();
                    }
                    Domain domainVisiting = graph.destiDomain.get(t);
                    if (domainVisiting.visited == false){
                        PathWithDomain tmpPath = new PathWithDomain();
                        Domain tmpDomain = domainVisiting;
                        tmpPath.existDomain.add(domainVisiting);
                        graph.sourceDomain.visited = true;

                        success = false;
                        alive = true;
                        step = false;
                        while (alive){
                            step = false;

                            numerator = new ArrayList<Double>();
                            denominator = 0;
                            double r = rand.nextDouble();
                            double tmpRate = 0;
                            if (tmpDomain.destiDomain){
                                for (Node nd : graph.destiNode){
                                    if (nd.domain == tmpDomain){
                                        tmpDomain.tmpDestiNode.add(nd);
                                    }
                                }
                            }
                            ArrayList<NotAliveNodes> tmpAr = new ArrayList<NotAliveNodes>();

                            for (int k = 0; k < tmpDomain.backAdjacencyList.size(); k++){
                                tmpAr.clear();
                                Edge tmpEdge = tmpDomain.backAdjacencyList.get(k);
                                for (Node tmpN : tmpDomain.tmpDestiNode){
                                    NotAliveNodes tmp = new NotAliveNodes();
                                    tmp.elementNotAlive.add(tmpEdge.OutNode);
                                    tmp.elementNotAlive.add(tmpN);
                                    tmpAr.add(tmp);
                                }

//                                for (NotAliveNodes nt : tmpAr){
//                                    System.out.println(nt.elementNotAlive.get(0).name+ "- " + nt.elementNotAlive.get(1).name);
//                                }
                                CheckNotAlive ck = new CheckNotAlive(tmpAr,listNotAlive);
//                                System.out.println(ck.check);
                                if (tmpPath.existDomain.contains(tmpEdge.InNode.domain) == false && ck.check == false) {
                                    Double tmpNum = Math.pow(tmpEdge.phero, alpha) / Math.pow(tmpEdge.weight, beta);
                                    numerator.add(tmpNum);
                                    denominator += tmpNum;
                                }
                                else {
                                    numerator.add(0.0);
                                }
                            }
//                            System.out.println(tmpDomain.name);

                            for (int k = 0; k < tmpDomain.backAdjacencyList.size(); k++) {
                                Edge tmpEdge = tmpDomain.backAdjacencyList.get(k);
                                if (denominator == 0){
                                    break;
                                }
                                tmpRate += numerator.get(k) / denominator;
//                                System.out.println(tmpRate+"(" + tmpEdge.InNode.name +  "-" + tmpEdge.OutNode.name +")");

                                if (tmpRate > r && tmpPath.existDomain.contains(tmpEdge.InNode.domain) == false) {

                                    tmpPath.tempTree.add(tmpEdge);
                                    tmpPath.weight += tmpEdge.weight;
                                    step = true;
                                    if (tmpEdge.InNode.domain.visited == true) {
                                        success = true;
                                        break;
                                    } else {
                                        tmpPath.existDomain.add(tmpEdge.InNode.domain);
                                        tmpDomain = tmpEdge.InNode.domain;
                                        tmpDomain.tmpDestiNode.clear();
                                        tmpDomain.tmpDestiNode.add(tmpEdge.InNode);
                                        break;
                                    }
                                }
                            }
                            if (success == true){
                                tmpJourney.weight += tmpPath.weight;
                                for (Domain tmp : tmpPath.existDomain){

                                    tmp.visited = true;
                                    tmpJourney.existDomain.add(tmp);

                                }
                                for (Edge tmp : tmpPath.tempTree){
                                    tmpJourney.tempTree.add(tmp);
                                }
                                alive = false;
                            }
                            if (step == false){
                                alive = false;
                                t--;
                                break;
                            }

                        }
                    }
                }
                out = false;
                for (JourneyWithDomain tmpjn : deathJourneys){
                    if (tmpjn.equals(tmpJourney)){
                        out = true;
                    }
                }
                if (out){
                    if (findLiveWay == false && numberOfDeath < 10000){
                        j--;
                    }
//                    for (Edge ed : tmpJourney.tempTree){
//                        System.out.print(ed.InNode.name + "-" + ed.OutNode.name+ " ");
//                    }
                    continue;
                }
                for (Domain dm : graph.domainList){
                    dm.destiNode.clear();
                }
                for (Node u : graph.destiNode){
                    u.domain.destiNode.add(u);
                }
                graph.sourceDomain.sourceNode = graph.sourceNode;

                for (Edge tmp : tmpJourney.tempTree){
                    if (tmp.InNode.domain.destiNode.contains(tmp.InNode) == false) {
                        tmp.InNode.domain.destiNode.add(tmp.InNode);
                    }
                    tmp.OutNode.domain.sourceNode = tmp.OutNode;
                }

//                for (Edge ed : tmpJourney.tempTree){
//                    System.out.print(" " + ed.InNode.domain.name + "-" + ed.OutNode.domain.name);
//                }
                ArrayList<JourneyInDomain> journeyLevel2 = new ArrayList<JourneyInDomain>();

                for (Domain tmpD : tmpJourney.existDomain){

                    GraphInDomain tmpGr = new GraphInDomain(this.graph,tmpD);
                    for (Node u : tmpGr.vertexList){
                        if (u.name == tmpD.sourceNode.name){
                            tmpGr.sourceNode = u;
                        }
                    }
                    for (Node u : tmpD.destiNode){
                        for (Node v : tmpGr.vertexList){
                            if (v.name == u.name){
                                tmpGr.destiNode.add(v);
                            }
                        }
                    }
                    SteinerInDomain tmpSt = new SteinerInDomain(tmpGr);
                    out = false;
                    if (tmpSt.out){

                        out = true;
                        NotAliveNodes tmpTuple = new NotAliveNodes();
                        for (Node tmpN : graph.vertexList){
                            if (tmpN.name == tmpSt.tupleNotAlive.elementNotAlive.get(0).name){
                                tmpTuple.elementNotAlive.add(tmpN);
                            }
                        }
                        for (Node tmpN : graph.vertexList){
                            if (tmpN.name == tmpSt.tupleNotAlive.elementNotAlive.get(1).name){
                                tmpTuple.elementNotAlive.add(tmpN);
                            }
                        }
//                        for (Node tmpN : graph.vertexList){
//                            System.out.print(tmpN.name + " ");
//                        }
//                        System.out.println();
//                        System.out.println(tmpSt.tupleNotAlive.elementNotAlive.get(0).name+ "-" + tmpSt.tupleNotAlive.elementNotAlive.get(1).name );
                        listNotAlive.add(tmpTuple);
                        numberOfDeath+=1;
//                        for (Edge ed : tmpJourney.tempTree){
//                            if (ed.InNode.domain == tmpSt.domain || ed.OutNode.domain == tmpSt.domain){
//                                ed.phero *= 0.6;
//                            }
////                            ed.phero *= 0.6;
//                        }
                        deathJourneys.add(tmpJourney);
                        break;
                    }
                    journeyLevel2.add(tmpSt.finalJourney);
                }
                if (out){
//                    System.out.println("mccncnncnc");
                    if (findLiveWay == false && numberOfDeath < 10000){
                        j--;
                    }
//                    for (Edge ed : tmpJourney.tempTree){
//                        System.out.print(ed.InNode.name + "-" + ed.OutNode.name+ " ");
//                    }
                    continue;
                }
//                System.out.println("1234566");
                findLiveWay = true;
                journeys.add(tmpJourney);
                for (JourneyInDomain jn : journeyLevel2){
                    tmpJourney.weight += jn.weight;
                }
                if (tmpJourney.weight < bestWeight){
                    finalJourney = new JourneyInDomain();
                    bestWeight = tmpJourney.weight;
                    for (Edge ed : tmpJourney.tempTree){
                        finalJourney.tempTree.add(ed);
                    }
                    for (JourneyInDomain jn : journeyLevel2){
                        for (Node nd : jn.existNodes){
                            finalJourney.existNodes.add(nd);

                        }
                        for (Edge ed : jn.tempTree){
                            finalJourney.tempTree.add(ed);
                        }
                    }
                }
//                System.out.println(tmpJourney.weight);
            }
            for (Domain tmp : graph.domainList){
                for (Edge tmpE : tmp.adjacencyList){
                    tmpE.phero *= (1 - pheroEvaporation);
                }
            }
            for (JourneyWithDomain jn : journeys){
                for (Edge ed : jn.tempTree){
//                    ed.phero %=0.4;
                    ed.phero += 10/ jn.weight;
                }
            }

        }
        System.out.println(bestWeight);
        for (Edge ed : finalJourney.tempTree){
            System.out.print(ed.InNode.name + "-" + ed.OutNode.name+ " ");
        }


    }
}
