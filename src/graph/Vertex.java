package graph;

/**
 * Vertex
 * Representa un vértice en un grafo, con un número único, una distancia desde un vértice origen, un vértice predecesor y una posición en la cola de prioridad mínima.
 */
class Vertex {
    /**
     * number: Número único que identifica al vértice.
     */
    public int number;
    /**
     * distance: Distancia desde un vértice origen hasta este vértice en particular.
     */
    public int distance;
    /**
     * pi: Número del vértice predecesor en el camino más corto desde el vértice origen.
     */
    public int pi;
    /**
     * positionInMinHeap: Posición del vértice en la cola de prioridad mínima.
     */
    public int positionInMinHeap;

    /**
     * Constructor de la clase Vertex.
     * @param number Número único del vértice.
     * @param distance Distancia desde un vértice origen.
     * @param pi Número del vértice predecesor.
     */
    public Vertex(int number, int distance, int pi) {
        this.number = number;
        this.distance = distance;
        this.pi = pi;
    }

    /**
     * Devuelve una representación en cadena del vértice.
     * @return Representación en cadena del vértice.
     */
    public String toString() {
        return number + " " + distance + pi;
    }
}