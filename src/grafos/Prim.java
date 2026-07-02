package grafos;

import java.util.*;

public class Prim {
    public void calcularDesdeLista(List<List<Arista>> lista) {
        int n = lista.size();
        boolean[] visitado = new boolean[n];
        PriorityQueue<Arista> pq = new PriorityQueue<>(Comparator.comparingInt(Arista::getPeso));
        List<Arista> mst = new ArrayList<>();

        visitado[0] = true;
        pq.addAll(lista.get(0));

        while (!pq.isEmpty() && mst.size() < n - 1) {
            Arista a = pq.poll();
            if (visitado[a.getDestino()]) continue;

            mst.add(a);
            int v = a.getDestino();
            visitado[v] = true;
            pq.addAll(lista.get(v));
        }

        imprimirMST(mst);
    }

    public void imprimirMST(List<Arista> mst) {
        System.out.println("Árbol de expansión mínima (Prim):");
        int total = 0;
        for (Arista a : mst) {
            System.out.println(a);
            total += a.getPeso();
        }
        System.out.println("Peso total: " + total);
    }
}
