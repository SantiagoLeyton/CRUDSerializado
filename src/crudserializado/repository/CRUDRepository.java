package crudserializado.repository;
import java.util.List;

public interface CRUDRepository<T> {
    void crear(T objeto);
    T leer(int id);
    List<T> listar();
    void actualizar(T objeto);
    void eliminar(int id);
}
