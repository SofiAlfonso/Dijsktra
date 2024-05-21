package graph;

/**
 * Esta clase se encarga de las aristas de cada vertice, así consigna un nodo destino v, el peso de la arista y una referencia a la proxima arista del vertice analizado.
 */
class Edge {
    public int v;      // nodo destino
    public int w;      // wpeso
    public Edge next;  // next edge en la lista

    public Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }
}
