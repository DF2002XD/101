package model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

	private final String RUTA_FICHERO = "autores.json";
	private VentanaInicioSesion ventanaInicioSesion;
	private VentanaCrearAutor ventanaCrearAutor;
	private VentanaMenuAutor ventanaMenuAutor;
	private VentanaVerDatos ventanaVerDatos;
	private VentanaCambiarTitulo ventanaCambiarTitulo;
	private VentanaBorrarAutor ventanaBorrarAutor;

	private void crearFicheroJson() {
		File file = new File(RUTA_FICHERO);
		if (!file.exists()) {
			try {
				FileWriter writer = new FileWriter(file);
				writer.write("[]");
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void guardarFicheroJson(JSONArray autores) {
		try (FileWriter file = new FileWriter(RUTA_FICHERO)) {
			file.write(autores.toString(2));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private JSONArray obtenerAutoresJson() {
		try (FileReader reader = new FileReader(RUTA_FICHERO)) {
			JSONTokener tokener = new JSONTokener(reader);
			return new JSONArray(tokener);
		} catch (IOException e) {
			e.printStackTrace();
			return new JSONArray();
		}
	}

	private int obtenerPosicionAutor(String nombreAutor, JSONArray autores) {
		for (int i = 0; i < autores.length(); i++) {
			if (autores.getJSONObject(i).getString("nombre").equals(nombreAutor)) {
				return i;
			}
		}
		return -1;
	}

	private JSONObject obtenerAutorJson(String nombreAutor) {
		JSONArray autores = obtenerAutoresJson();
		int posicion = obtenerPosicionAutor(nombreAutor, autores);
		if (posicion != -1) {
			return autores.getJSONObject(posicion);
		}
		return null;
	}

	public void ejecutar() {
		crearFicheroJson();
		ventanaInicioSesion = new VentanaInicioSesion(this);
		ventanaInicioSesion.setVisible(true);
	}

	public void iniciarValidacion(String nombreAutor, String tituloLibroAutor) {
		JSONObject autor = obtenerAutorJson(nombreAutor);
		if (autor != null && autor.getString("titulo").equals(tituloLibroAutor)) {
			ventanaInicioSesion.setVisible(false);
			mostrarMenuAutor(nombreAutor);
		} else {
			JOptionPane.showMessageDialog(ventanaInicioSesion, "Autor o título del libro incorrectos",
					"Error de validación", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void cerrarSesion() {
		ventanaMenuAutor.dispose();
		ventanaInicioSesion.setVisible(true);
	}

	public void crearAutor(String nombre, String titulo, String paginas, String editorial) {
		JSONArray autores = obtenerAutoresJson();
		JSONObject nuevoAutor = new JSONObject();
		nuevoAutor.put("nombre", nombre);
		nuevoAutor.put("titulo", titulo);
		nuevoAutor.put("paginas", paginas);
		nuevoAutor.put("editorial", editorial);
		autores.put(nuevoAutor);
		guardarFicheroJson(autores);
		JOptionPane.showMessageDialog(ventanaCrearAutor, "Autor creado con éxito", "Éxito",
				JOptionPane.INFORMATION_MESSAGE);
		ventanaCrearAutor.dispose();
		ventanaInicioSesion.setVisible(true);
	}

	public void cambiarTituloLibro(String nombreAutor, String nuevoTitulo) {
		JSONArray autores = obtenerAutoresJson();
		int posicion = obtenerPosicionAutor(nombreAutor, autores);
		if (posicion != -1) {
			JSONObject autor = autores.getJSONObject(posicion);
			autor.put("titulo", nuevoTitulo);
			guardarFicheroJson(autores);
			JOptionPane.showMessageDialog(ventanaCambiarTitulo, "Título cambiado con éxito", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
			ventanaCambiarTitulo.dispose();
			mostrarMenuAutor(nombreAutor);
		}
	}

	public void borrarAutor(String nombreAutor) {
		JSONArray autores = obtenerAutoresJson();
		int posicion = obtenerPosicionAutor(nombreAutor, autores);
		if (posicion != -1) {
			autores.remove(posicion);
			guardarFicheroJson(autores);
			JOptionPane.showMessageDialog(ventanaBorrarAutor, "Autor borrado con éxito", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
			ventanaBorrarAutor.dispose();
			cerrarSesion();
		}
	}

	public void mostrarVentanaCrearAutor() {
		ventanaCrearAutor = new VentanaCrearAutor(this);
		ventanaCrearAutor.setVisible(true);
	}

	public void mostrarVentanaVerDatos(String nombreAutor) {
		JSONObject autor = obtenerAutorJson(nombreAutor);
		if (autor != null) {
			ventanaVerDatos = new VentanaVerDatos(this, autor.getString("nombre"), autor.getString("paginas"),
					autor.getString("editorial"));
			ventanaVerDatos.setVisible(true);
		}
	}

	public void mostrarVentanaCambiarTitulo(String nombreAutor) {
		ventanaCambiarTitulo = new VentanaCambiarTitulo(this, nombreAutor);
		ventanaCambiarTitulo.setVisible(true);
	}

	public void mostrarVentanaBorrarAutor(String nombreAutor) {
		ventanaBorrarAutor = new VentanaBorrarAutor(this, nombreAutor);
		ventanaBorrarAutor.setVisible(true);
	}

	public void mostrarMenuAutor(String nombreAutor) {
		ventanaInicioSesion.setVisible(false);
		ventanaMenuAutor = new VentanaMenuAutor(this, nombreAutor);
		ventanaMenuAutor.setVisible(true);
	}
}
