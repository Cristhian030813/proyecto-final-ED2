package main;

import arboles.ArbolBinarioBusqueda;
import arboles.Nodo;
import grafos.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArbolBinarioBusqueda<Integer> abb = new ArbolBinarioBusqueda<>();
        Grafo grafo;

        // Inicialización del grafo
        try {
            System.out.print("Ingrese número de vértices del grafo: ");
            int n = sc.nextInt();
            sc.nextLine();
            grafo = new Grafo(n);
        } catch (Exception e) {
            System.out.println("Entrada inválida. Se usará grafo con 10 vértices.");
            grafo = new Grafo(10);
            sc.nextLine();
        }

        int opcion;
        do {
            imprimirMenu();
            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Ingrese un número.");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> insertarAlmacen(abb, sc);
                case 2 -> eliminarAlmacen(abb, sc);
                case 3 -> buscarAlmacen(abb, sc);
                case 4 -> modificarAlmacen(abb, sc);
                case 5 -> recorrerABB(abb);
                case 6 -> agregarRutaNoDirigida(grafo, sc);
                case 7 -> agregarRutaDirigida(grafo, sc);
                case 8 -> eliminarRuta(grafo, sc);
                case 9 -> mostrarGrafo(grafo, sc);
                case 10 -> ejecutarDijkstra(grafo, sc);
                case 11 -> ejecutarBellmanFord(grafo, sc);
                case 12 -> ejecutarPrim(grafo);
                case 13 -> ejecutarKruskal(grafo);
                case 14 -> ejecutarTopologico(grafo);
                case 15 -> usarAristaDirectamente();
                case 16 -> listarTodasAristas(grafo);
                case 17 -> compararMSTs(grafo);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no reconocida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    // --- Menú ---
    private static void imprimirMenu() {
        System.out.println("\n--- MENÚ ---");
        System.out.println("1. Insertar almacén");
        System.out.println("2. Eliminar almacén");
        System.out.println("3. Buscar almacén");
        System.out.println("4. Modificar almacén");
        System.out.println("5. Recorrer ABB");
        System.out.println("6. Agregar ruta no dirigida");
        System.out.println("7. Agregar ruta dirigida");
        System.out.println("8. Eliminar ruta");
        System.out.println("9. Mostrar grafo");
        System.out.println("10. Dijkstra");
        System.out.println("11. Bellman-Ford");
        System.out.println("12. Prim");
        System.out.println("13. Kruskal");
        System.out.println("14. Topológico");
        System.out.println("15. Usar Arista");
        System.out.println("16. Listar aristas");
        System.out.println("17. Comparar MSTs");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }

    // --- Operaciones ABB ---
    private static void insertarAlmacen(ArbolBinarioBusqueda<Integer> abb, Scanner sc) {
        System.out.print("Ingrese código del almacén: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        abb.insertar(codigo);
        System.out.println("Almacén insertado correctamente.");
    }

    private static void eliminarAlmacen(ArbolBinarioBusqueda<Integer> abb, Scanner sc) {
        System.out.print("Ingrese código del almacén a eliminar: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        abb.eliminar(codigo);
        System.out.println("Almacén eliminado (si existía).");
    }

    private static void buscarAlmacen(ArbolBinarioBusqueda<Integer> abb, Scanner sc) {
        System.out.print("Ingrese código del almacén a buscar: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        Nodo<Integer> nodo = abb.buscar(codigo);
        System.out.println(nodo != null ? "Encontrado: " + nodo.valor : "No existe.");
    }

    private static void modificarAlmacen(ArbolBinarioBusqueda<Integer> abb, Scanner sc) {
        System.out.print("Ingrese código viejo: ");
        int viejo = sc.nextInt();
        System.out.print("Ingrese código nuevo: ");
        int nuevo = sc.nextInt();
        sc.nextLine();
        abb.modificar(nuevo, viejo);
        System.out.println("Almacén modificado.");
    }

    private static void recorrerABB(ArbolBinarioBusqueda<Integer> abb) {
        abb.recorrerInorden(abb.getRaiz());
        System.out.println();
    }
    // --- Operaciones Grafo ---
    private static void agregarRutaNoDirigida(Grafo g, Scanner sc) {
        System.out.print("Ingrese vértice origen: ");
        int u = sc.nextInt();
        System.out.print("Ingrese vértice destino: ");
        int v = sc.nextInt();
        System.out.print("Ingrese peso: ");
        int peso = sc.nextInt();
        sc.nextLine();
        if (u < 0 || u >= g.getNumVertices() || v < 0 || v >= g.getNumVertices()) {
            System.out.println("Error: índice inválido.");
            return;
        }
        g.agregarArista(u, v, peso);
        System.out.println("Ruta agregada correctamente.");
    }

    private static void agregarRutaDirigida(Grafo g, Scanner sc) {
        System.out.print("Ingrese vértice origen: ");
        int u = sc.nextInt();
        System.out.print("Ingrese vértice destino: ");
        int v = sc.nextInt();
        System.out.print("Ingrese peso: ");
        int peso = sc.nextInt();
        sc.nextLine();
        g.agregarAristaDirigida(u, v, peso);
        System.out.println("Ruta dirigida agregada correctamente.");
    }

    private static void eliminarRuta(Grafo g, Scanner sc) {
        System.out.print("Ingrese vértice origen: ");
        int u = sc.nextInt();
        System.out.print("Ingrese vértice destino: ");
        int v = sc.nextInt();
        sc.nextLine();
        g.eliminarArista(u, v);
        System.out.println("Ruta eliminada (si existía).");
    }

    private static void mostrarGrafo(Grafo g, Scanner sc) {
        System.out.print("Ingrese 1 para matriz o 2 para lista: ");
        int modo = sc.nextInt();
        sc.nextLine();
        if (modo == 1) g.imprimirMatriz();
        else g.imprimirLista();
    }

    // --- Algoritmos ---
    private static void ejecutarDijkstra(Grafo g, Scanner sc) {
        System.out.print("Ingrese vértice origen: ");
        int src = sc.nextInt();
        sc.nextLine();
        if (src < 0 || src >= g.getNumVertices()) {
            System.out.println("Error: índice inválido.");
            return;
        }
        Dijkstra d = new Dijkstra();
        int[] dist = d.calcular(g, src);
        d.imprimirDistancias(dist, src);
    }

    private static void ejecutarBellmanFord(Grafo g, Scanner sc) {
        System.out.print("Ingrese vértice origen: ");
        int src = sc.nextInt();
        sc.nextLine();
        BellmanFord bf = new BellmanFord();
        int[] dist = bf.calcular(g, src);
        bf.imprimirDistancias(dist, src);
    }

    private static void ejecutarPrim(Grafo g) {
        Prim prim = new Prim();
        prim.calcularDesdeLista(g.getLista());
    }

    private static void ejecutarKruskal(Grafo g) {
        Kruskal kr = new Kruskal();
        List<Arista> todas = g.obtenerTodasAristas();
        List<Arista> mst = kr.calcular(g.getNumVertices(), todas);
        kr.imprimirMST(mst);
    }

    private static void ejecutarTopologico(Grafo g) {
        Topologico topo = new Topologico();
        List<Integer> orden = topo.ordenar(g.getLista());
        topo.imprimirOrden(orden);
    }

    // --- Extras ---
    private static void usarAristaDirectamente() {
        Arista a1 = new Arista(0, 1, 5);
        Arista a2 = new Arista(1, 0, 5);
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a1.equals(a2));
    }

    private static void listarTodasAristas(Grafo g) {
        List<Arista> todas = g.obtenerTodasAristas();
        for (Arista a : todas) System.out.println(a);
    }

    private static void compararMSTs(Grafo g) {
        Prim prim = new Prim();
        prim.calcularDesdeLista(g.getLista());
        Kruskal kr = new Kruskal();
        List<Arista> mst = kr.calcular(g.getNumVertices(), g.obtenerTodasAristas());
        kr.imprimirMST(mst);
    }
}
