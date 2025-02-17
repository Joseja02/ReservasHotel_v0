package org.iesalandalus.programacion.reservashotel.modelo.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Reserva {

    public static final int MAX_NUMERO_MESES_RESERVA = 6;
    private static final int MAX_HORAS_POSTERIOR_CHECKOUT = 12;
    public static final String FORMATO_FECHA_RESERVA = "dd/MM/yyyy";
    public static final String FORMATO_FECHA_HORA_RESERVA = "dd/MM/yyyy HH/mm";
    private Huesped huesped;
    private Habitacion habitacion;
    private Regimen regimen;
    private LocalDate fechaInicioReserva;
    private LocalDate fechaFinReserva;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private double precio;
    private int numeroPersonas;

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Regimen getRegimen() {
        return regimen;
    }

    public void setRegimen(Regimen regimen) {
        this.regimen = regimen;
    }

    public LocalDate getFechaInicioReserva() {
        return fechaInicioReserva;
    }

    public void setFechaInicioReserva(LocalDate fechaInicioReserva) {
        if (fechaInicioReserva == null) {
            throw new NullPointerException("ERROR: La fecha de inicio de una reserva no puede ser nula.");
        }
        if (fechaInicioReserva.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de inicio de la reserva no puede ser anterior al d�a de hoy.");
        }
        if (fechaInicioReserva.isAfter(LocalDate.now().plusMonths(MAX_NUMERO_MESES_RESERVA))) {
            throw new IllegalArgumentException("ERROR: La fecha de inicio de la reserva no puede ser posterior a seis meses.");
        }

        this.fechaInicioReserva = fechaInicioReserva;
    }

    public LocalDate getFechaFinReserva() {
        return fechaFinReserva;
    }

    public void setFechaFinReserva(LocalDate fechaFinReserva) {

        if (fechaFinReserva == null) {
            throw new NullPointerException("ERROR: La fecha de fin de una reserva no puede ser nula.");
        }
        if (fechaFinReserva.isBefore(fechaInicioReserva) || fechaFinReserva.isEqual(fechaInicioReserva)) {
            throw new IllegalArgumentException("ERROR: La fecha de fin de la reserva debe ser posterior a la de inicio.");
        }
        this.fechaFinReserva = fechaFinReserva;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {

        if (checkIn == null) {
            throw new NullPointerException("ERROR: El checkin de una reserva no puede ser nulo.");
        }

        if (checkIn.isBefore(fechaInicioReserva.atStartOfDay())) {
            throw new IllegalArgumentException("ERROR: El checkin de una reserva no puede ser anterior a la fecha de inicio de la reserva.");
        }
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {

        if (checkOut == null) {
            throw new NullPointerException("ERROR: El checkout de una reserva no puede ser nulo.");
        }
        if (checkOut.isBefore(checkIn)) {
            throw new IllegalArgumentException("ERROR: El checkout de una reserva no puede ser anterior al checkin.");
        }
        if (checkOut.isAfter(fechaFinReserva.atTime(LocalTime.of(MAX_HORAS_POSTERIOR_CHECKOUT, 0, 0)))) {
            throw new IllegalArgumentException("ERROR: El checkout de una reserva puede ser como m�ximo 12 horas despu�s de la fecha de fin de la reserva.");
        }

        this.checkOut = checkOut;
    }

    public double getPrecio() {
        setPrecio(precio);
        return precio;
    }

    private void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("Error: Precio menor o igual a 0");
        }

        double costeHabitacion = getHabitacion().getPrecio();
        double costeRegimen = getRegimen().getIncrementoPrecio() * getNumeroPersonas();

        Period period = Period.between(getFechaInicioReserva(), getFechaFinReserva());

        precio = (costeHabitacion + costeRegimen) * period.getDays();

        this.precio = precio;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        if (numeroPersonas <= 0) {
            throw new IllegalArgumentException("ERROR: El n�mero de personas de una reserva no puede ser menor o igual a 0.");
        }

        TipoHabitacion numeroMaximoPersonas = habitacion.getTipoHabitacion();

        if (numeroPersonas > numeroMaximoPersonas.getNumeroMaximoPersonas()) {
            throw new IllegalArgumentException("ERROR: El n�mero de personas de una reserva no puede superar al m�ximo de personas establacidas para el tipo de habitaci�n reservada.");
        }

        this.numeroPersonas = numeroPersonas;
    }

    public Reserva(Huesped huesped, Habitacion habitacion, Regimen regimen, LocalDate fechaInicioReserva, LocalDate fechaFinReserva, int numeroPersonas) {
        if (huesped == null) {
            throw new NullPointerException("ERROR: El hu�sped de una reserva no puede ser nulo.");
        }
        if (habitacion == null) {
            throw new NullPointerException("ERROR: La habitaci�n de una reserva no puede ser nula.");
        }
        if (regimen == null) {
            throw new NullPointerException("ERROR: El r�gimen de una reserva no puede ser nulo.");
        }
        if (fechaInicioReserva == null) {
            throw new NullPointerException("ERROR: La fecha de inicio de una reserva no puede ser nula.");
        }
        if (fechaFinReserva == null) {
            throw new NullPointerException("ERROR: La fecha de fin de una reserva no puede ser nula.");
        }
        if (numeroPersonas <= 0) {
            throw new IllegalArgumentException("ERROR: El n�mero de personas de una reserva no puede ser menor o igual a 0.");
        }

        setHuesped(huesped);
        setHabitacion(habitacion);
        setRegimen(regimen);
        setFechaInicioReserva(fechaInicioReserva);
        setFechaFinReserva(fechaFinReserva);
        setNumeroPersonas(numeroPersonas);
    }

    public Reserva(Reserva reservaCopia) {
        if (reservaCopia == null) {
            throw new NullPointerException("ERROR: No es posible copiar una reserva nula.");
        }
        this.huesped = reservaCopia.getHuesped();
        this.habitacion = reservaCopia.getHabitacion();
        this.regimen = reservaCopia.getRegimen();
        this.fechaInicioReserva = reservaCopia.getFechaInicioReserva();
        this.fechaFinReserva = reservaCopia.getFechaFinReserva();
        this.numeroPersonas = reservaCopia.getNumeroPersonas();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(habitacion, reserva.habitacion) && Objects.equals(fechaInicioReserva, reserva.fechaInicioReserva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(habitacion, fechaInicioReserva);
    }

    @Override
    public String toString() {
        return "Huesped: " + huesped.getNombre() + " " + huesped.getDni() +
                " Habitaci�n:" + habitacion.getIdentificador() + " - " +
                habitacion.getTipoHabitacion() +
                " Fecha Inicio Reserva: " + getFechaInicioReserva().format(DateTimeFormatter.ofPattern(FORMATO_FECHA_RESERVA)) +
                " Fecha Fin Reserva: " + getFechaFinReserva().format(DateTimeFormatter.ofPattern(FORMATO_FECHA_RESERVA)) +
                " Checkin: " + (getCheckIn() == null ? "No registrado" : getCheckIn().format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA_RESERVA))) +
                " Checkout: " + (getCheckOut() == null ? "No registrado" : getCheckOut().format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA_RESERVA))) +
                " Precio: " + String.format("%.2f", getPrecio()) +
                " Personas: " + getNumeroPersonas();
    }
}
