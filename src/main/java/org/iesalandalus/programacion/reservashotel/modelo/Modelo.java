package org.iesalandalus.programacion.reservashotel.modelo;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Reservas;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;

public class Modelo {
    private int CAPACIDAD;
    private Huespedes huespedes;
    private Habitaciones habitaciones;
    private Reservas reservas;

    public void comenzar(){
        huespedes = new Huespedes(CAPACIDAD);
        habitaciones = new Habitaciones(CAPACIDAD);
        reservas = new Reservas(CAPACIDAD);
    }
    public void terminar(){
        System.out.println("El modelo ha finalizado.");
    }
    public void insertar (Huesped huesped) throws OperationNotSupportedException {
        huespedes.insertar(huesped);
    }
    public Huesped buscar (Huesped huesped){
        huespedes.buscar(huesped);
        return new Huesped(huesped);
    }
    public void borrar (Huesped huesped) throws OperationNotSupportedException {
        huespedes.borrar(huesped);
    }
    public Huesped[] getHuespedes(){
        return new Huesped[CAPACIDAD];
    }
    public void insertar (Habitacion habitacion) throws OperationNotSupportedException {
        habitaciones.insertar(habitacion);
    }
    public Habitacion buscar (Habitacion habitacion){
        habitaciones.buscar(habitacion);
        return new Habitacion(habitacion);
    }
    public void borrar (Habitacion habitacion) throws OperationNotSupportedException {
        habitaciones.borrar(habitacion);
    }
    public Habitacion[] getHabitaciones(){ return new Habitacion[CAPACIDAD]; }
    public Habitacion[] getHabitaciones(TipoHabitacion tipoHabitacion){ return new Habitacion[CAPACIDAD]; }
    public void insertar (Reserva reserva) throws OperationNotSupportedException {
        reservas.insertar(reserva);
    }
    public Reserva buscar (Reserva reserva){
        reservas.buscar(reserva);
        return new Reserva(reserva);
    }
    public void borrar (Reserva reserva) throws OperationNotSupportedException {
        reservas.borrar(reserva);
    }
    public Reserva[] getReservas(){ return new Reserva[CAPACIDAD]; }
    public Reserva[] getReservas(Huesped huesped){ return new Reserva[CAPACIDAD]; }
    public Reserva[] getReservas(TipoHabitacion tipoHabitacion){ return new Reserva[CAPACIDAD]; }
    public Reserva[] getReservasFuturas(Habitacion habitacion){ return new Reserva[CAPACIDAD]; }
    public void realizarCheckin(Reserva reserva, LocalDateTime fecha){
        reservas.realizarCheckin(reserva, fecha);
    }
    public void realizarCheckout(Reserva reserva, LocalDateTime fecha){
        reservas.realizarCheckout(reserva, fecha);
    }
}
