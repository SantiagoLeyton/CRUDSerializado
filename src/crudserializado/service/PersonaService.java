package crudserializado.service;

import crudserializado.model.Persona;
import crudserializado.repository.CRUDRepository;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaService implements CRUDRepository<Persona> {
    private final String archivo = "personas.dat";
    private List<Persona> personas;

    public PersonaService() {
        personas = leerArchivo();
    }

    @Override
    public void crear(Persona persona) {
        personas.add(persona);
        guardarArchivo();
    }

    @Override
    public Persona leer(int id) {
        return personas.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Persona> listar() {
        return personas;
    }

    @Override
    public void actualizar(Persona persona) {
        Persona p = leer(persona.getId());
        if (p != null) {
            p.setNombre(persona.getNombre());
            p.setEdad(persona.getEdad());
            guardarArchivo();
        }
    }

    @Override
    public void eliminar(int id) {
        personas.removeIf(p -> p.getId() == id);
        guardarArchivo();
    }

    // --- Persistencia ---
    private void guardarArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(personas);
        } catch (IOException e) {
            System.out.println("Error al guardar archivo: " + e.getMessage());
        }
    }

    private List<Persona> leerArchivo() {
        File file = new File(archivo);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Persona>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
