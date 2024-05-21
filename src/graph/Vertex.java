package graph;



class Vertex {
    public int number;
    public int distance;
    public int pi;
    public int positionInMinHeap;

    public Vertex(int number, int distance, int pi) {
        this.number = number;
        this.distance = distance;
        this.pi = pi;
    }

}