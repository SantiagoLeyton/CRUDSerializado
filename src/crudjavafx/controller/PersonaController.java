package crudjavafx.controller;

import crudjavafx.model.Persona;
import crudjavafx.service.PersonaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class PersonaController {
    @FXML private TableView<Persona> tablaPersonas;
    @FXML private TableColumn<Persona, Integer> colId;
    @FXML private TableColumn<Persona, String> colNombre;
    @FXML private TableColumn<Persona, Integer> colEdad;

    private PersonaService servicio;
    private ObservableList<Persona> listaPersonas;

    @FXML
    public void initialize() {
        servicio = new PersonaService();
        listaPersonas = FXCollections.observableArrayList(servicio.listar());
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        colEdad.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getEdad()).asObject());
        tablaPersonas.setItems(listaPersonas);
    }

    @FXML
    private void agregarPersona() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Agregar Persona (Formato: id,nombre,edad)");
        dialog.setContentText("Ingrese los datos:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(input -> {
            try {
                String[] datos = input.split(",");
                Persona p = new Persona(Integer.parseInt(datos[0].trim()), datos[1].trim(), Integer.parseInt(datos[2].trim()));
                servicio.crear(p);
                listaPersonas.add(p);
            } catch (Exception e) {
                mostrarError("Formato inválido. Use: id,nombre,edad");
            }
        });
    }

    @FXML
    private void eliminarPersona() {
        Persona seleccionada = tablaPersonas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            servicio.eliminar(seleccionada);
            listaPersonas.remove(seleccionada);
        } else {
            mostrarError("Seleccione una persona para eliminar.");
        }
    }

    @FXML
    private void actualizarTabla() {
        listaPersonas.setAll(servicio.listar());
    }

    private void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR, mensaje, ButtonType.OK);
        alerta.showAndWait();
    }
}
