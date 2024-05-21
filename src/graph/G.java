package graph;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;

/**
 * Esta clase se encarga de la creación del grafo, contiene una lista para los vertices V, y una lista para las aristas (la lista contiene en cada índice una lista de adyacencia llena de aristas)
 * Siendo el indice, el mismo que el de el nodo de salida, y la lista adyacencia, las aristas que salen de dicho nodo.
 */
public class G {
    // Set of vertices
    Vertex[] V;
    // Adjacency list
    Edge[] adj;

    MinPriorityQueue pq;

    /**
     * Permite añadir una arista a la lista
     * @param u indice de la lista (nodo de salida)
     * @param v número del nodo de llegada
     * @param w peso de la arista
     */
    public void addEdge(int u, int v, int w) {
        Edge e = new Edge(v, w);
        e.next = adj[u];
        adj[u] = e;
    }

    /**
     * Paso inicial del algoritmo de Dijkstra, inicializa los  vertices con distancias infinitas y sin predecesores, y al source con distancia 0
     * @param s número del nodo source
     */
    public void initializeSingleSource(int s) {
        for (int i = 1; i < V.length; i++) {
            V[i] = new Vertex(i, Integer.MAX_VALUE, -1);
        }
        V[s].distance = 0;
    }


    /**
     * Algoritmo de Dijkstra, visita cada vertice y lo agrega a un Minpriority  queue
     * para cada vertice en esta (tomando siempre el de menor distancia o peso), relaja sus aristas
     * y actualiza sus pesos en la Minpriority  queue, partiendo siempre desde el camino más corto ya calculado.
     * @param s nodo source
     */
    public void dijkstra(int s) {
        pq = new MinPriorityQueue(V.length);
        initializeSingleSource(s);

        for (int i = 1; i < V.length; i++) {
            pq.minHeapInsert(V[i]);
        }

        while (!pq.isEmpty()) {
            Vertex u = pq.extractMin();
            Edge e = adj[u.number];
            while (e != null) {
                relax(u.number, e.v, e.w);
                e = e.next;
            }
        }
    }

    public void relax(int u, int v, int w) {
        if (V[v].distance > V[u].distance + w) {
            V[v].distance = V[u].distance + w;
            V[v].pi = u;
            pq.minHeapDecreaseKey(V[v].positionInMinHeap, V[v].distance);
        }
    }

    /**
     * Lee el archivo txt, crea el grafo y agrega las aristas a la lista de adyacencia de cada vertice
     * @param fileName nombre del arcivo txt
     */

    public void readGraph(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            int n = sc.nextInt();
            int m = sc.nextInt();

            this.V = new Vertex[n + 1];
            this.adj = new Edge[n + 1];

            // Inicializar la lista de adyacencia para cada vértice
            for (int i = 1; i <= n; i++) {
                this.adj[i] = null;
            }

            // Leer y agregar cada arista del archivo al grafo
            for (int i = 0; i < m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt();

                // Agregar la arista desde u hasta v con peso w
                addEdge(u, v, w);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Imprime el resultado final de los vertices luego de Dijkstra, el vertice, predecesor y distancia al source
     */
    public void printVerticesWithPreviousNode() {
        for (int i = 1; i < V.length; i++) {
            if (V[i].pi == -1) {
                System.out.println("v: "+V[i].number + "| d: " + V[i].distance + "| pi: " + null);
            } else {
                System.out.println("v: "+V[i].number + "| d: " + V[i].distance + "| pi: " + V[i].pi);
            }
        }
    }

    /**
     * Imprime el grafo como un conjunto de listas de adyacencia
     */
    public void printGraph()
    {
        for(int i=1; i<adj.length; i++)
        {
            Edge head= adj[i];
            System.out.print(i+":");
            while(head!=null)
            {
                System.out.print(" ->"+"| v:"+head.v+ " _ w:"+ head.w+ "|");
                head= head.next;
            }
            System.out.println("");
        }
    }

    /**
     * Imprime el camino más corto del source a otro nodo, lo hace siguiendo los predecesores del nodo destino hasta el nodo source.
     * @param source nodo source
     * @param destination nodo destino
     * @return se usa el retorno solo para garantizar que s no hay camino posible, este no se intente imprimir
     */
    public boolean ShortestPath(int source, int destination) {
        int fnal= destination;
        System.out.println("\nEl camino más corto entre los nodos "+ source + " y "+ destination+ " es:");
        if (V[destination].distance == Integer.MAX_VALUE) {
            System.out.println("No hay camino entre ambos nodos.");
            return true;
        }

        Stack<Integer> pathStack = new Stack<>();
        while (destination != -1) {
            pathStack.push(destination);
            destination = V[destination].pi;
        }

        while (!pathStack.isEmpty()) {
            System.out.print("->"+pathStack.pop());
        }
        System.out.println(" | Su peso es de: " + this.V[fnal].distance);
        return true;


    }
}
