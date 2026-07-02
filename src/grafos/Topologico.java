package grafos;

import java.util.*;

public class Topologico {
    // Método principal: recibe la lista de adyacencia y devuelve el orden topológico
    public List<Integer> ordenar(List<List<Arista>> lista) {
        int n = lista.size();
        boolean[] visitado = new boolean[n];
        Stack<Integer> pila = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (!visitado[i]) {
                dfs(i, visitado, pila, lista);
            }
        }

        List<Integer> orden = new ArrayList<>();
        while (!pila.isEmpty()) {
            orden.add(pila.pop());
        }
        return orden;
    }

    // DFS recursivo para recorrer el grafo
    private void dfs(int u, boolean[] visitado, Stack<Integer> pila, List<List<Arista>> lista) {
        visitado[u] = true;
        for (Arista a : lista.get(u)) {
            int v = a.getDestino();
            if (!visitado[v]) {
                dfs(v, visitado, pila, lista);
            }
        }
        pila.push(u);
    }

    // Método para imprimir el orden topológico
    public void imprimirOrden(List<Integer> orden) {
        System.out.println("Orden topológico:");
        for (int v : orden) {
            System.out.print(v + " ");
        }
        System.out.println();
    }
}
