public class AdjListNode {
    public int vertexIndex;
    public int distance;

    public AdjListNode(int in,int d){
        this.vertexIndex = in;
        this.distance = d;
    }

    public int getVertexIndex(){
        return(vertexIndex);
    }

    public int getVertexDistance(){
        return(distance);
    }


}