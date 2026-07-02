package arboles;

public class ArbolBinarioBusqueda<T extends Comparable<T>> {
    private Nodo<T> raiz;

    public Nodo<T> getRaiz() {
        return raiz;
    }

    public void insertar(T valor) {
        raiz = insertarRec(raiz, valor);
    }

    private Nodo<T> insertarRec(Nodo<T> nodo, T valor) {
        if (nodo == null) return new Nodo<>(valor);
        if (valor.compareTo(nodo.valor) < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, valor);
        } else if (valor.compareTo(nodo.valor) > 0) {
            nodo.derecho = insertarRec(nodo.derecho, valor);
        }
        return nodo;
    }

    public Nodo<T> buscar(T valor) {
        return buscarRec(raiz, valor);
    }

    private Nodo<T> buscarRec(Nodo<T> nodo, T valor) {
        if (nodo == null || nodo.valor.equals(valor)) return nodo;
        if (valor.compareTo(nodo.valor) < 0) return buscarRec(nodo.izquierdo, valor);
        return buscarRec(nodo.derecho, valor);
    }

    public void eliminar(T valor) {
        raiz = eliminarRec(raiz, valor);
    }

    private Nodo<T> eliminarRec(Nodo<T> nodo, T valor) {
        if (nodo == null) return null;
        if (valor.compareTo(nodo.valor) < 0) {
            nodo.izquierdo = eliminarRec(nodo.izquierdo, valor);
        } else if (valor.compareTo(nodo.valor) > 0) {
            nodo.derecho = eliminarRec(nodo.derecho, valor);
        } else {
            if (nodo.izquierdo == null) return nodo.derecho;
            else if (nodo.derecho == null) return nodo.izquierdo;
            nodo.valor = minValor(nodo.derecho);
            nodo.derecho = eliminarRec(nodo.derecho, nodo.valor);
        }
        return nodo;
    }

    private T minValor(Nodo<T> nodo) {
        T min = nodo.valor;
        while (nodo.izquierdo != null) {
            min = nodo.izquierdo.valor;
            nodo = nodo.izquierdo;
        }
        return min;
    }

    public void modificar(T nuevo, T viejo) {
        eliminar(viejo);
        insertar(nuevo);
    }

    public void recorrerInorden(Nodo<T> nodo) {
        if (nodo != null) {
            recorrerInorden(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            recorrerInorden(nodo.derecho);
        }
    }
}
