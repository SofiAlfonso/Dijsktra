package graph;

/**
 * Edge
 * Representa una arista en un grafo, con un vértice destino, un peso y una referencia a la siguiente arista en la lista.
 */
class Edge {
    /**
     * v: Vértice destino de la arista.
     */
    public int v;
    /**
     * w: Peso de la arista.
     */
    public int w;
    /**
     * next: Referencia a la siguiente arista en la lista.
     */
    public Edge next;

    /**
     * Constructor de la clase Edge.
     * @param v Vértice destino de la arista.
     * @param w Peso de la arista.
     */
    public Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }
}
