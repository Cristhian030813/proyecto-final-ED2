package grafos;

import java.util.*;

public class Kruskal {
    // Método principal: recibe número de vértices y todas las aristas
    public List<Arista> calcular(int numVertices, List<Arista> aristas) {
        // Ordenamos las aristas por peso ascendente
        aristas.sort(Comparator.comparingInt(Arista::getPeso));

        // Estructura Union-Find para detectar ciclos
        UnionFind uf = new UnionFind(numVertices);
        List<Arista> mst = new ArrayList<>();

        for (Arista a : aristas) {
            int u = a.getOrigen();
            int v = a.getDestino();
            // Si no forman ciclo, se añaden al MST
            if (uf.union(u, v)) {
                mst.add(a);
            }
            // Si ya tenemos n-1 aristas, el MST está completo
            if (mst.size() == numVertices - 1) break;
        }
        return mst;
    }

    // Método para imprimir el MST resultante
    public void imprimirMST(List<Arista> mst) {
        System.out.println("Árbol de expansión mínima (Kruskal):");
        int total = 0;
        for (Arista a : mst) {
            System.out.println(a);
            total += a.getPeso();
        }
        System.out.println("Peso total: " + total);
    }

    // Clase interna para Union-Find (Disjoint Set)
    private static class UnionFind {
        private int[] padre;
        private int[] rango;

        public UnionFind(int n) {
            padre = new int[n];
            rango = new int[n];
            for (int i = 0; i < n; i++) {
                padre[i] = i;
                rango[i] = 0;
            }
        }

        public int find(int x) {
            if (padre[x] != x) {
                padre[x] = find(padre[x]); // Compresión de caminos
            }
            return padre[x];
        }

        public boolean union(int x, int y) {
            int raizX = find(x);
            int raizY = find(y);
            if (raizX == raizY) return false; // Ya están conectados

            // Unión por rango
            if (rango[raizX] < rango[raizY]) {
                padre[raizX] = raizY;
            } else if (rango[raizX] > rango[raizY]) {
                padre[raizY] = raizX;
            } else {
                padre[raizY] = raizX;
                rango[raizX]++;
            }
            return true;
        }
    }
}
