package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {
    SALIR("SALIR"),
    INSERTAR_HUESPED("INSERTAR HUESPED"),
    BUSCAR_HUESPED("BUSCAR HUESPED"),
    BORRAR_HUESPED("BORRAR HUESPED"),
    MOSTRAR_HUESPEDES("MOSTRAR HUESPEDES"),
    INSERTAR_HABITACION("INSERTAR HABITACION"),
    BUSCAR_HABITACION("BUSCAR HABITACION"),
    BORRAR_HABITACION("BORRAR HABITACION"),
    MOSTRAR_HABITACIONES("MOSTRAR HABITACIONES"),
    INSERTAR_RESERVA("INSERTAR RESERVA"),
    ANULAR_RESERVA("ANULAR RESERVA"),
    MOSTRAR_RESERVAS("MOSTRAR RESERVAS"),
    CONSULTAR_DISPONIBILIDAD("CONSULTAR DISPONIBILIDAD");
    private final String mensajeAMostrar;

    private Opcion(String mensajeAMostrar){
        this.mensajeAMostrar = mensajeAMostrar;
    }

    @Override
    public String toString() {
        return valueOf(mensajeAMostrar).ordinal() + " .- " + mensajeAMostrar;
    }
}
