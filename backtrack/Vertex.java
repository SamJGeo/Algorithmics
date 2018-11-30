import java.util.LinkedList;

public class Vertex{



    public int index;
    public int distance;
    public LinkedList<AdjListNode> adjList;
    public int predecessor;
    public boolean visited;
    

    public Vertex(int i){
        this.index = i;
        this.visited = false;
        adjList = new LinkedList<AdjListNode>();

    }

    public void setVisted(Boolean a){
        this.visited = a;
    }

    public boolean getVisited(){
        return(this.visited);
    }

    public void setPredecessor(int v){
        this.predecessor = v;
    }

    public int getPredecessor(){
        return(predecessor);
    }

    public int getIndex(){
        return(index);
    }


    public void setIndex(int i){
        this.index = i;
    }

    public LinkedList<AdjListNode> getAdjList(){
        return(adjList);
    }

    public int getDistance(){
        return(distance);
    }

    public void setDistance(int d){
        this.distance = d;
    }


    public void addToAdjList(int n,int d){
        adjList.addLast(new AdjListNode(n,d));
    }



    public int vertexDegree(){
        return(adjList.size());
    }

}