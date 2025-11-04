package crudserializado.model;

// Clase genérica de ejemplo
public class Envoltorio<T> {
    private T valor;

    public Envoltorio(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Envoltorio{" +
                "valor=" + valor +
                '}';
    }
}
