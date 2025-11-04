package crudjavafx.service;

import crudjavafx.model.Persona;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaService {
    private final String archivo = "personasfx.dat";
    private List<Persona> personas;

    public PersonaService() {
        personas = leerArchivo();
    }

    public void crear(Persona persona) {
        personas.add(persona);
        guardarArchivo();
    }

    public void eliminar(Persona persona) {
        personas.removeIf(p -> p.getId() == persona.getId());
        guardarArchivo();
    }

    public List<Persona> listar() {
        return personas;
    }

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
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Persona>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
