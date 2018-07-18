package com.jleonelli.api;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class ClimaServlet extends HttpServlet {
	
	String condiciones;
	Map<String, PronosticoPorDia> pronosticos;
	
	/**
	 * Inicializar la base de datos in-memory.
	 */
	@Override
	public void init() {
		Planeta Ferengi = new Planeta(500, "horario", 1, 90);
	    Planeta Betasoide = new Planeta(2000, "horario", 3, 90);
	    Planeta Vulcano = new Planeta(1000, "antihorario", 5, 90);
	    
	    SistemaSolarService service = new SistemaSolarService();
	    pronosticos = service.calcularCondicionesClimaticasPorDia(10, Ferengi, Betasoide, Vulcano);
	}
	
	/**
	 * GET endpoint para obtener el clima para determinado dia
	 * necesitamos pasar como parametro en la url ?dia=566 por ejemplo.
	 * 
	 * Este endpoint consume la base de datos in-memory inicializada en el metodo init.
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Gson gson = new Gson();
		String dia = req.getParameter("dia");
		PronosticoPorDia response;
		if (dia != null) {
			response = pronosticos.get(dia);
			String jsonResponse = gson.toJson(response);
			resp.setContentType("application/json");
			resp.getWriter().println(jsonResponse);
		} else {
			resp.setContentType("text/plain");
			resp.getWriter().println("Para usar la api y consultar el clima par aun dia especifico, agregar a la url actual el parametro: "
					+ "?dia={numero de dia deseado}, por ejemplo: http://clima-jleonelli.appspot.com/clima?dia=566");
		}
	}
}
