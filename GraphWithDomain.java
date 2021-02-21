package Multipoint;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

import java.util.Iterator;
import java.util.List;


public class GraphWithDomain extends Graph {
    public ArrayList<Domain> domainList = new ArrayList<Domain>();
    public int N_domain;
    public Domain sourceDomain;
    public ArrayList<Domain> destiDomain = new ArrayList<Domain>();

    public GraphWithDomain(String s){
        try {
            File myObj = new File("C:/Users/T540p/IdeaProjects/Multipont/src/"+s + ".txt");
            Scanner myReader = new Scanner(myObj);
            String data;
            int count;
            String[] tmpString ;
            Integer tmpInt;
            if (myReader.hasNextLine()){
                data = myReader.nextLine();
                tmpString = data.split(" ");
                count = 0;
                for (String tmp : tmpString){
                    if (tmp != ""){
                        if (count == 0){
                            N_node = Integer.parseInt(tmp);
                        }
                        if (count == 1){
                            N_domain = Integer.parseInt(tmp);
                        }
                        count++;
                    }
                }
            }
            for (int i = 0; i  < N_node; i++){
                Node tmpNode = new Node(i+1);
                vertexList.add(tmpNode);
            }

            if (myReader.hasNextLine()){
                data = myReader.nextLine();
                count = 0;
                tmpString = data.split(" ");
                for (String tmp : tmpString){
                    if (tmp != ""){
                        if (count == 0){
                            tmpInt = Integer.parseInt(tmp);
                            sourceNode = vertexList.get(tmpInt-1);
                            vertexList.get(tmpInt-1).sourceNode = true;
                        }
                        if (count >= 1){
                            tmpInt = Integer.parseInt(tmp);
                            destiNode.add(vertexList.get(tmpInt-1));
                            vertexList.get(tmpInt-1).destiNode = true;
                        }
                        count++;
                    }
                }
            }
            for (int i =0; i < N_domain; i++){
                Domain tmpDpomain = new Domain(i+1);
                domainList.add(tmpDpomain);
            }
            for (int i =0; i < N_domain; i++){
                if (myReader.hasNextLine()){
                    data = myReader.nextLine();
                    tmpString = data.split(" ");
                    for (String tmp : tmpString){
                        if (tmp != ""){
                            tmpInt = Integer.parseInt(tmp);
                            domainList.get(i).vertrexList.add(vertexList.get(tmpInt-1));
                            vertexList.get(tmpInt-1).domain = domainList.get(i);
                        }
                    }
                }
            }
            for (Domain tmpDomain : domainList){
                for (Node tmpNode : tmpDomain.vertrexList){
                    if (tmpNode.sourceNode){
                        sourceDomain = tmpDomain;
                        tmpDomain.sourceDomain = true;
                    }
                    if (tmpNode.destiNode){
                        destiDomain.add(tmpDomain);
                        tmpDomain.destiDomain = true;
                    }
                }
            }
            while (myReader.hasNextLine()) {
                String[] a ;
                data = myReader.nextLine();
                a = data.split(" ");
                count = 0;
                Edge tmpEdge = new Edge();
                for (String b : a ){
                    if (b != "") {
                        tmpInt = Integer.parseInt(b);
                        if (count == 0){
                            tmpEdge.InNode = vertexList.get(tmpInt-1);
                            vertexList.get(tmpInt-1).adjacencyList.add(tmpEdge);
                        }
                        if (count == 1){
                            tmpEdge.OutNode = vertexList.get(tmpInt-1);
                            vertexList.get(tmpInt-1).backAdjacencyList.add(tmpEdge);

                        }
                        if (count == 2){
                            tmpEdge.weight = tmpInt;
                        }
                        count++;
                    }
                }
                if (tmpEdge.InNode.domain != tmpEdge.OutNode.domain){
                    tmpEdge.InNode.domain.adjacencyList.add(tmpEdge);
                    tmpEdge.OutNode.domain.backAdjacencyList.add(tmpEdge);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void simplify(){
        Iterator  itr = vertexList.iterator();
        while(itr.hasNext()){
            Node tmp = (Node)itr.next();
            repeatRemove(tmp);
        }
        Iterator itr1 = vertexList.iterator();
        while (itr1.hasNext()){
            Node tmp1 = (Node) itr1.next();
            if (tmp1.dead == true){
                itr1.remove();
            }
        }
    }
    public void deleteDeadNode(Node s){
        if (s.dead == true){
            for (Edge tmp : s.adjacencyList){
                tmp.dead  = true;
            }
        }
        Iterator  itr = vertexList.iterator();

    }
    public void repeatRemove(Node s){
        Iterator  itr = s.backAdjacencyList.iterator();
        while (itr.hasNext()){
            Edge x = (Edge)itr.next();
            if (x.dead == true){
                itr.remove();
            }
        }
        if (s.backAdjacencyList.size() == 0 && s.sourceNode == false) {
            s.dead = true;
            deleteDeadNode(s);
            for (Edge tmp: s.adjacencyList){
                repeatRemove(tmp.OutNode);
            }
        }
    }
}
