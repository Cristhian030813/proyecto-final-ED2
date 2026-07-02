package grafos;

import java.util.Objects;

public class Arista {
    private int origen;
    private int destino;
    private int peso;

    public Arista(int origen, int destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public int getOrigen() {
        return origen;
    }

    public int getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return "(" + origen + " - " + destino + ", peso=" + peso + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Arista)) return false;
        Arista arista = (Arista) o;
        return (origen == arista.origen && destino == arista.destino && peso == arista.peso)
                || (origen == arista.destino && destino == arista.origen && peso == arista.peso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.min(origen, destino), Math.max(origen, destino), peso);
    }
}
