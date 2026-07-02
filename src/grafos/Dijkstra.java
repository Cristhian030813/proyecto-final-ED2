package grafos;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
    public int[] calcular(Grafo g, int origen) {
        int n = g.getNumVertices();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[origen] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{origen, 0});

        while (!pq.isEmpty()) {
            int[] actual = pq.poll();
            int u = actual[0];
            int d = actual[1];

            if (d > dist[u]) continue;

            for (Arista a : g.getLista().get(u)) {
                int v = a.getDestino();
                int peso = a.getPeso();
                if (dist[u] + peso < dist[v]) {
                    dist[v] = dist[u] + peso;
                    pq.add(new int[]{v, dist[v]});
                }
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
