package Multipoint;
import java.util.ArrayList;


public class GraphInDomain extends Graph {
    public Domain domain;
    public GraphWithDomain graph;
    public GraphInDomain(GraphWithDomain graph,Domain domain){
        this.graph = graph;
        this.domain = domain;
        for (Node originalNode : domain.vertrexList){
            Node copyNode = new Node(originalNode.name);
            vertexList.add(copyNode);

        }
        for (int i = 0;  i  < vertexList.size(); i++){
            Node oriNode = domain.vertrexList.get(i);
            Node copyNode = vertexList.get(i);
            for (int j = 0; j < oriNode.adjacencyList.size(); j++){
                Edge oriEdge = oriNode.adjacencyList.get(j);
                if (oriEdge.InNode.domain == oriEdge.OutNode.domain){
                    Edge copyEdge = new Edge();
                    copyEdge.InNode = copyNode;
                    int pos = domain.vertrexList.indexOf(oriEdge.OutNode);
                    copyEdge.OutNode =vertexList.get(pos);
                    copyEdge.weight = oriEdge.weight;
                    copyNode.adjacencyList.add(copyEdge);
                }
            }
            for (int j = 0; j < oriNode.backAdjacencyList.size(); j++){
                Edge oriEdge = oriNode.backAdjacencyList.get(j);
                if (oriEdge.InNode.domain == oriEdge.OutNode.domain){
                    Edge copyEdge = new Edge();
                    copyEdge.OutNode = copyNode;
                    int pos = domain.vertrexList.indexOf(oriEdge.InNode);
                    copyEdge.InNode =vertexList.get(pos);
                    copyEdge.weight = oriEdge.weight;
                    copyNode.backAdjacencyList.add(copyEdge);
                }
            }
        }
    }

}
