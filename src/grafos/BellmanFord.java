package grafos;

import java.util.Arrays;

public class BellmanFord {
    public int[] calcular(Grafo g, int origen) {
        int n = g.getNumVertices();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[origen] = 0;

        for (int i = 1; i < n; i++) {
            for (Arista a : g.obtenerTodasAristas()) {
                int u = a.getOrigen();
                int v = a.getDestino();
                int peso = a.getPeso();
                if (dist[u] != Integer.MAX_VALUE && dist[u] + peso < dist[v]) {
                    dist[v] = dist[u] + peso;
                }
            }
        }

        for (Arista a : g.obtenerTodasAristas()) {
            int u = a.getOrigen();
            int v = a.getDestino();
            int peso = a.getPeso();
            if (dist[u] != Integer.MAX_VALUE && dist[u] + peso < dist[v]) {
                throw new IllegalArgumentException("El grafo contiene un ciclo de peso negativo");
            }
        }

        return dist;
    }

    public void imprimirDistancias(int[] dist, int origen) {
        System.out.println("Distancias desde el vértice " + origen + ":");
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("A " + i + ": infinito");
            } else {
                System.out.println("A " + i + ": " + dist[i]);
            }
        }
    }
}
