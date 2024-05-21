package graph;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;



/**
 * G
 * Representa un grafo con vértices y aristas, con métodos para calcular el camino más corto utilizando el algoritmo de Dijkstra.
 */
public class G {
    // Conjunto de vértices
    Vertex[] V;
    // Lista de adyacencia
    Edge[] adj;

    MinPriorityQueue pq;

    /**
     * Añade una arista al grafo.
     * @param u Vértice de inicio de la arista.
     * @param v Vértice de fin de la arista.
     * @param w Peso de la arista.
     */
    public void addEdge(int u, int v, int w) {
        Edge e = new Edge(v, w);
        e.next = adj[u];
        adj[u] = e;
    }

    /**
     * Inicializa la distancia de todos los vértices como infinito, excepto el vértice fuente cuya distancia se establece en 0.
     * @param s Vértice fuente.
     */
    public void initializeSingleSource(int s) {
        for (int i = 1; i < V.length; i++) {
            V[i] = new Vertex(i, Integer.MAX_VALUE, -1);
        }
        V[s].distance = 0;
    }

    /**
     * Ejecuta el algoritmo de Dijkstra para encontrar el camino más corto desde el vértice fuente a todos los demás vértices.
     * @param s Vértice fuente.
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

    /**
     * Relaja una arista del grafo, actualizando la distancia mínima al vértice de destino si se encuentra un camino más corto.
     * @param u Vértice de inicio de la arista.
     * @param v Vértice de fin de la arista.
     * @param w Peso de la arista.
     */
    public void relax(int u, int v, int w) {
        if (V[v].distance > V[u].distance + w) {
            V[v].distance = V[u].distance + w;
            V[v].pi = u;
            pq.minHeapDecreaseKey(V[v].positionInMinHeap, V[v].distance);
        }
    }

    /**
     * Lee el grafo desde un archivo de texto y lo construye.
     * @param fileName Nombre del archivo que contiene la información del grafo.
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
     * Imprime los vértices del grafo con el nodo anterior asociado.
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
     * Imprime el grafo en forma de lista de adyacencia.
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
     * Encuentra y muestra el camino más corto entre dos nodos del grafo.
     * @param source Nodo de inicio.
     * @param destination Nodo de destino.
     * @return true si hay un camino entre los nodos, false en caso contrario.
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