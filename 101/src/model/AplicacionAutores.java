package model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import gui.VentanaBorrarAutor;
import gui.VentanaCambiarTitulo;
import gui.VentanaCrearAutor;
import gui.VentanaInicioSesion;
import gui.VentanaMenuAutor;
import gui.VentanaVerDatos;

public class AplicacionAutores {

	private final String RUTA_FICHERO = "";
	private VentanaInicioSesion ventanaInicioSesion;
	private VentanaCrearAutor ventanaCrearAutor;
	private VentanaMenuAutor ventanaMenuAutor;
	private VentanaVerDatos ventanaVerDatos;
	private VentanaCambiarTitulo ventanaCambiarTitulo;
	private VentanaBorrarAutor ventanaBorrarAutor;

	private void crearFicheroJson() {

	}

	private void guardarFicheroJson(JSONArray autores) {

	}

	private JSONArray obtenerAutoresJson() {

	}

	private int obtenerPosicionAutor(String nombreAutor, JSONArray autores) {

	}

	private JSONObject obtenerAutoresJson(String nombreAutor) {

	}

	public void ejecutar() {

	}

	public void iniciarValidacion(String nombreAutor, String tituloLibroAutor) {

	}

	public void cerrarSesion() {

	}

	public void crearAutor(String nombre, String titulo, String paginas, String editorial) {

	}

	public void cambiarTituloLibro(String nombreAutor, String nuevoTitulo) {

	}

	public void borrarAutor(String nombreAutor) {

	}

	public void mostrarVentanaCrearAutor() {

	}

	public void mostrarVentanaVerDatos(String nombreAutor) {

	}

	public void mostrarVentanaCambiarTitulo(String nombreAutor) {

	}

	public void mostrarVentanaBorrarAutor(String nombreAutor) {

	}

	public void mostrarMenuAutor(String nombreAutor) {

	}
}
