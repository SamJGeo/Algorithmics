/*

This code was written by Sam George, 2261522g
Algorithmics 1 Assessed Exercise

*/

import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;
public class Graph{

    public int num_vertices;
    public Vertex[] vertices;

//  BT VARIABLES
    ArrayList<Vertex> currentPath;
    int bestDist;
    int curDist;


    public Graph(int numVertices){
        //initialising attributes of graph
        this.num_vertices = numVertices;
        this.vertices= new Vertex[numVertices];
        this.currentPath = new ArrayList<Vertex>();
        //creating all the vertices
        for(int i = 0; i < numVertices; i++){
            vertices[i] = new Vertex(i);
            vertices[i].setDistance(Integer.MAX_VALUE);;
        }

    }

    public int size(){
        return(num_vertices);
    }

    public Vertex getVertex(int i){
        return(vertices)[i];
    }



    public void setupBT(Vertex s){
        bestDist = Integer.MAX_VALUE;
        curDist = 0;
        s.setVisted(true);
        currentPath.add(s);
        s.setPredecessor(-1);
    }


    public void backtrack(int n,Vertex d){
        //copy the last vertex so its easier to index
        Vertex prev = currentPath.get(n);

        //loop over all its adjacent vertices
        for(AdjListNode a:prev.getAdjList()){
            //if the vertex has been visted, skip
            if(vertices[a.getVertexIndex()].getVisited()){
                continue;
            }
            //if the current distance is greater than the record distance, skip
            if(curDist+a.getVertexDistance() > bestDist){
                continue;
            }
            //if the current distance is greater than the record distance for that vertex, skip
            if(vertices[a.getVertexIndex()].getDistance() <curDist+a.getVertexDistance()){
                continue;
            }
            //Add the vertex to the current path and update distances/predecessors/visted
            currentPath.add(vertices[a.getVertexIndex()]);
            vertices[a.getVertexIndex()].setVisted(true);
            curDist += a.getVertexDistance();
            vertices[a.getVertexIndex()].setDistance(curDist);
            vertices[a.getVertexIndex()].setPredecessor(prev.getIndex());

            //if the vertex is the destination
            if(vertices[a.getVertexIndex()] == d){
                if(curDist < bestDist){
                    //update best distance
                    bestDist = curDist;
                }
            } else {
                //otherwise continue looping
                backtrack(n+1, d);
            }

            //once all adjacent nodes have been visited. remove ther vertex from the current path
            currentPath.remove(vertices[a.getVertexIndex()]);
            vertices[a.getVertexIndex()].setVisted(false);
            curDist -= a.getVertexDistance();


        }
    }

    public int getBD(){
        return(bestDist);
    }

    //recursive function for printing the path
    public void printOut(Vertex in){
        if(in.getPredecessor()==-1){
            System.out.print(in.getIndex() + " ");
        } else{
            System.out.print(in.getIndex()+ " ");
            printOut(vertices[in.getPredecessor()]);
        }
    }
}