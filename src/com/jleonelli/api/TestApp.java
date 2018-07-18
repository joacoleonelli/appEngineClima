package com.jleonelli.api;

public class TestApp {
  public static void main(String[] args) {
    
    Planeta Ferengi = new Planeta(500, "horario", 1, 90);
    Planeta Betasoide = new Planeta(2000, "horario", 3, 90);
    Planeta Vulcano = new Planeta(1000, "antihorario", 5, 90);
    
    SistemaSolarService service = new SistemaSolarService();
    
    // Llamada al servicio para responder las 3 primeras preguntas.
    System.out.println(service.calcularCondicionesClimaticas(10, Ferengi, Betasoide, Vulcano));
  }
}
