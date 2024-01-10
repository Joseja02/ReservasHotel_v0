package org.iesalandalus.programacion.reservashotel.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Huesped {

    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;
    private String ER_TELEFONO;
    private String ER_CORREO;
    private String ER_DNI;
    public String FORMATO_FECHA;

    private String formateaNombre(String nombre) {

        String nombreFormateado;
        nombreFormateado = nombre.replaceAll("\\s{2,}", " ").trim();

        String resultado = "";
        String[] palabras = nombreFormateado.split(" ");

            for (int i = 0; i < palabras.length; i++) {
                String palabra = palabras[i];
                String inicial = palabra.substring(0, 1);
                String palabraRestante = palabra.substring(1);
                resultado += inicial.toUpperCase() + palabraRestante.toLowerCase() + " ";
            }
        return resultado.trim();
    }
    private static Boolean comprobarLetraDNI(String dni) {

        boolean letraValida = false;
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

        String patron = "^(\\d{8})([A-Za-z])$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(dni);

        if (matcher.matches()) {
            String numeroDNI = matcher.group(1);
            String letraDNI = matcher.group(2);

                try {
                    int numero = Integer.parseInt(numeroDNI);
                    int indice = numero % 23;
                    char letraCalculada = letras.charAt(indice);
                    letraValida = letraCalculada == letraDNI.charAt(0);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: El DNI tiene un formato incorrecto");
                }
        } else {
            throw new IllegalArgumentException("ERROR: El dni del huésped no tiene un formato válido.");
        }
        return letraValida;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre de un huésped no puede ser nulo.");
        }
        if (nombre.trim().isEmpty()){
            throw new IllegalArgumentException("ERROR: El nombre de un huésped no puede estar vacío.");
        }
            this.nombre = formateaNombre(nombre);
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        String validacion = "^\\d{9}$";
        if (telefono == null) {
            throw new NullPointerException("ERROR: El teléfono de un huésped no puede ser nulo.");
        }
        if (telefono.matches(validacion)) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("ERROR: El teléfono del huésped no tiene un formato válido.");
        }
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        String validacion = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,3}$";
        if (correo == null) {
            throw new NullPointerException("ERROR: El correo de un huésped no puede ser nulo.");
        }
        if (correo.matches(validacion)) {
            this.correo = correo;
        } else {
            throw new IllegalArgumentException("ERROR: El correo del huésped no tiene un formato válido.");
        }
    }
    public String getDni() {
        return dni;
    }
    private void setDni(String dni) {

        if (dni == null ) {
            throw new NullPointerException("ERROR: El dni de un huésped no puede ser nulo.");
        }
            comprobarLetraDNI(dni);
            if (!comprobarLetraDNI(dni)){
                throw new IllegalArgumentException("ERROR: La letra del dni del huésped no es correcta.");
            }
            this.dni = dni;
    }
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    private void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new NullPointerException("ERROR: La fecha de nacimiento de un huésped no puede ser nula.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }
    private String getIniciales(){
        String iniciales = "";
        String nombreCompleto = formateaNombre(nombre);
        String[] nombresSeparados = nombreCompleto.split(" ");

        for (int i = 0; i < nombresSeparados.length; i++) {
            String nombre = nombresSeparados[i];
            String inicial = nombre.substring(0,1);
            iniciales += inicial.toUpperCase();
        }
        return iniciales;
    }
    public Huesped(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento){
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
    }
    public Huesped (Huesped huespedCopia){
        if (huespedCopia == null){
            throw new NullPointerException("ERROR: No es posible copiar un huésped nulo.");
        }

        this.nombre = huespedCopia.getNombre();
        this.dni = huespedCopia.getDni();
        this.correo = huespedCopia.getCorreo();
        this.telefono = huespedCopia.getTelefono();
        this.fechaNacimiento = huespedCopia.getFechaNacimiento();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Huesped huesped = (Huesped) o;
        return Objects.equals(dni, huesped.dni);
    }
    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
    @Override
    public String toString() {
        return "nombre=" + nombre + " " + "(" + getIniciales() + ")" +
                ", DNI=" + dni +
                ", correo=" + correo +
                ", teléfono=" + telefono +
                ", fecha nacimiento=" + fechaNacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}