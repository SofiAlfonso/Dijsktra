import graph.G;
/**
 * El programa implementa el algoritmo Dijkstra, usando prgogramación orientada a objetos para analizar el grafos, sus vertices y aristas.
 * Implementa Min Priority Queue y algunos de sus métodods, para manejar los nodos con menor peso o distancia que requiere Dijkstra despues de cada relajación.
 *
 * @author  Tomás Gañan Rivera y Ana Sofia ALfonso Moncada
 * @since   2024-05-16
 */



public class Main {
    /**
 * Se encarga de manejar paso por paso los métodos relacionados con la lectura del grafo, y Dijkstra
 * toma también el tiempo de ejecución del algoritmo.
 * @param args
 */
    public static void main(String[] args) {
        G g = new G();
        g.readGraph("graph1.txt");

        System.out.println("Grafo de entrada:");
        g.printGraph();
        System.out.println();

        // Imprime el resultado del predecesor y la distancia desde el nodo 1 a todos los demás nodos
        System.out.println("Camino más corto desde el nodo 1 a todos los demás nodos:");

        long ti= System.nanoTime();
        g.dijkstra(1);
        long tf=System.nanoTime();
        System.out.println("\nTiempo de ejecución (ns):"+(tf-ti)+ "\n");

        g.printVerticesWithPreviousNode();
        g.ShortestPath(1,5);
    }
}
