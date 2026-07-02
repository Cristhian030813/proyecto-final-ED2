package grafos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Grafo {
    private int numVertices;
    private int[][] matriz;
    private List<List<Arista>> lista;

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        matriz = new int[numVertices][numVertices];
        lista = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            lista.add(new LinkedList<>());
        }
    }

    public int getNumVertices() {
        return numVertices;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public List<List<Arista>> getLista() {
        return lista;
    }

    public void agregarArista(int origen, int destino, int peso) {
        matriz[origen][destino] = peso;
        matriz[destino][origen] = peso;
        lista.get(origen).add(new Arista(origen, destino, peso));
        lista.get(destino).add(new Arista(destino, origen, peso));
    }

    public void agregarAristaDirigida(int origen, int destino, int peso) {
        matriz[origen][destino] = peso;
        lista.get(origen).add(new Arista(origen, destino, peso));
    }

    public void eliminarArista(int origen, int destino) {
        matriz[origen][destino] = 0;
        matriz[destino][origen] = 0;
        lista.get(origen).removeIf(a -> a.getDestino() == destino);
        lista.get(destino).removeIf(a -> a.getDestino() == origen);
    }

    public void imprimirMatriz() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void imprimirLista() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + ": ");
            for (Arista a : lista.get(i)) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }

    public List<Arista> obtenerTodasAristas() {
        List<Arista> todas = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            for (Arista a : lista.get(i)) {
                if (!todas.contains(a)) {
                    todas.add(a);
                }
            }
        }
        return todas;
    }
}
