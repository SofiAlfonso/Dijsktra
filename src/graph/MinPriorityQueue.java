package graph;



/**
 * MinPriorityQueue
 * Mantenga una cola de prioridad mínima de vértices basada en su distancia desde el vértice fuente.
 * Los vértices se almacenan en una estructura de datos.
 * Esta estructura es utilizada para el algoritmo de Dijkstra.
 */
public class MinPriorityQueue {

    /**
     * array: Arreglo de vértices utilizado para almacenar los elementos de la cola de prioridad mínima.
     */
    Vertex [] array;

    /**
     * heapSize: Tamaño actual de la cola de prioridad mínima.
     */
    int heapSize;

    /**
     * Constructor de la clase MinPriorityQueue.
     * @param heapSize Tamaño inicial de la cola de prioridad mínima.
     */
    public MinPriorityQueue(int heapSize) {
        array = new Vertex[heapSize+1];
        this.heapSize = 0;
    }

    /**
     * parent: Devuelve el índice del padre de un elemento en el árbol binario.
     * @param i Índice del elemento actual.
     * @return Índice del padre del elemento actual.
     */
    public int parent(int i) {
        return i/2;
    }

    /**
     * left: Devuelve el índice del hijo izquierdo de un elemento en el árbol binario.
     * @param i Índice del elemento actual.
     * @return Índice del hijo izquierdo del elemento actual.
     */
    public int left(int i) {
        return i * 2;
    }

    /**
     * right: Devuelve el índice del hijo derecho de un elemento en el árbol binario.
     * @param i Índice del elemento actual.
     * @return Índice del hijo derecho del elemento actual.
     */
    public int right(int i) {
        return i * 2 + 1;
    }

    /**
     * swap: Intercambia dos elementos en el arreglo de vértices.
     * @param i Índice del primer elemento.
     * @param j Índice del segundo elemento.
     */
    public void swap(int i, int j) {
        Vertex temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        array[i].positionInMinHeap = i;
        array[j].positionInMinHeap = j;
    }

    /**
     * minHeapify: Mantiene la propiedad de la cola de prioridad mínima empezando desde un índice dado.
     * @param i Índice a partir del cual se realizará la corrección.
     */
    public void minHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest;
        if(l <= heapSize && array[l].distance < array[i].distance) {
            smallest = l;
        } else {
            smallest = i;
        }
        if(r <= heapSize && array[r].distance < array[smallest].distance) {
            smallest = r;
        }
        if(smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    /**
     * minHeapMinimum: Devuelve el vértice con la mínima distancia en la cola de prioridad mínima.
     * @return Vértice con la mínima distancia.
     */
    public Vertex minHeapMinimum() {
        if(this.heapSize < 1) {
            System.out.println("Heap underflow");
            System.exit(1);
        }
        return array[1];
    }

    /**
     * extractMin: Extrae y devuelve el vértice con la mínima distancia de la cola de prioridad mínima.
     * @return Vértice con la mínima distancia.
     */
    public Vertex extractMin() {
        Vertex min = minHeapMinimum();
        min.positionInMinHeap = -1;
        array[1] = array[heapSize];
        this.heapSize--;
        minHeapify(1);
        return min;
    }

    /**
     * minHeapDecreaseKey: Reduce la distancia de un vértice en la cola de prioridad mínima y reorganiza la cola según sea necesario.
     * @param i Índice del vértice cuya distancia se modificará.
     * @param key Nuevo valor de la distancia.
     */
    public void minHeapDecreaseKey(int i, int key) {
        if(key > array[i].distance) {
            System.out.println("Nuevo valor de clave es mayor que la clave actual");
            System.exit(1);
        }
        array[i].distance = key;
        while(i > 1 && array[parent(i)].distance > array[i].distance) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    /**
     * minHeapInsert: Inserta un nuevo vértice en la cola de prioridad mínima.
     * @param v Vértice a insertar.
     */
    public void minHeapInsert(Vertex v) {
        this.heapSize++;
        if(this.heapSize + 1 > array.length) {
            System.out.println("Heap overflow");
            System.exit(1);
        }
        array[heapSize] = v;
        v.positionInMinHeap = heapSize; // Establecer la posición correctamente
        minHeapDecreaseKey(this.heapSize, v.distance);
    }

    /**
     * isEmpty: Verifica si la cola de prioridad mínima está vacía.
     * @return true si la cola está vacía, false en caso contrario.
     */
    public boolean isEmpty() {
        return this.heapSize==0;
    }
}