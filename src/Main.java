import graph.G;

/**
 * Main
 * La clase principal que contiene el método principal para ejecutar el programa.
 */
public class Main {
    /**
     * Método principal que ejecuta el programa.
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // Crear un objeto de la clase G
        G g = new G();
        // Leer el grafo desde el archivo "graph1.txt"
        g.readGraph("graph1.txt");

        // Imprimir el grafo de entrada
        System.out.println("Grafo de entrada:");
        g.printGraph();
        System.out.println();

        // Calcular y mostrar el camino más corto desde el nodo 1 a todos los demás nodos
        System.out.println("Camino más corto desde el nodo 1 a todos los demás nodos:");

        // Medir el tiempo de ejecución del algoritmo de Dijkstra
        long ti = System.nanoTime(); // Tiempo inicial
        g.dijkstra(1);
        long tf = System.nanoTime(); // Tiempo final
        System.out.println("\nTiempo de ejecución (ns):" + (tf - ti) + "\n");

        // Imprimir los vértices con su nodo predecesor asociado
        g.printVerticesWithPreviousNode();

        // Calcular y mostrar el camino más corto desde el nodo 1 al nodo 5
        g.ShortestPath(1,5);
    }
}