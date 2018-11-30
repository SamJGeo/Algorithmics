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

//  DIJKSTRAS VARIABLES
    public Vertex vList[];
    protected ArrayList<Integer> vListIndexes;

    public Graph(int numVertices){
        //initialising attributes of graph
        this.num_vertices = numVertices;
        this.vertices= new Vertex[numVertices];
        this.vListIndexes = new ArrayList<Integer>();
        //creating all the vertices
        for(int i = 0; i < numVertices; i++){
            vListIndexes.add(i, i);
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

    //recursive function for printing the path
    public void printOut(Vertex in){
        if(in.getPredecessor()==-1){
            System.out.print(in.getIndex() + " ");
        } else{
            System.out.print(in.getIndex()+ " ");
            printOut(vertices[in.getPredecessor()]);
        }
    }


    public void dijkstra(Vertex s,Vertex d){

        //initialising distances from the source
        for(AdjListNode a:s.getAdjList()){
            vertices[a.getVertexIndex()].setDistance(a.getVertexDistance());
            vertices[a.getVertexIndex()].setPredecessor(s.getIndex());
        }

        //setting the predecessor of the source as -1 so we know when to terminate
        Vertex currentV = s;
        s.setPredecessor(-1);

        //The list of vertices which have been visted
        LinkedList<Vertex> vList = new LinkedList<Vertex>();
        vList.add(s);
        //holds the indexes of nodes which havent been visited
        //vlistindexes is an AL because i need to find min and remove it which may be in the middle of the list
        vListIndexes.remove(vListIndexes.indexOf(s.getIndex()));

        int min;
        while(vList.size()<num_vertices){
            //getting the min distance for a vertex that has not been visited
            min=vListIndexes.get(0);
            for(int k:vListIndexes){
                if(vertices[k].getDistance()<vertices[min].getDistance()){
                    min = k;
                }
            }
            //removing that from the list of possible to visit vertices
            vListIndexes.remove(vListIndexes.indexOf(min));
            //add the closest node to the VList and 
            vList.add(vertices[min]);

            currentV = vertices[min];
            //for every adjacent node check if the distance through current node is shorter than its already set distance
            for(AdjListNode n:currentV.getAdjList()){
                if(n.getVertexDistance()+currentV.getDistance() < vertices[n.getVertexIndex()].getDistance()){
                    //if it is shorted then update the distance and the nodes predecessor
                    vertices[n.getVertexIndex()].setDistance(n.getVertexDistance()+currentV.getDistance());
                    vertices[n.getVertexIndex()].setPredecessor(currentV.getIndex());
                }


            }
        }


        //check if there is a path and print it
        vertices[s.getIndex()].setPredecessor(-1);
        if(d.getDistance() == Integer.MAX_VALUE){
            System.out.format("There is no path between vertex %d and %d",s.getIndex(),s.getIndex());
        } else{
            System.out.format("The shortest path from vertex %d to vertex %d is %d \n",s.getIndex(),d.getIndex(),d.getDistance());
            System.out.print("Shortest Path: ");
            printOut(d);
        }
    }


}