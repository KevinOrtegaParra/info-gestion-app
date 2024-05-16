/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iudigital.info.gestion.domain;

import java.time.LocalDate;

/**
 *
 * @author kevin
 */
public class Funcionario {
    
    private int id;
    private String tipo_identificasion;
    private String num_identificasion;
    private String nombre;
    private String apellido;
    private String estado_civil;
    private char sexo;
    private String direccion;
    private String telefono; 
    private LocalDate fecha_nacimiento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo_identificasion() {
        return tipo_identificasion;
    }

    public void setTipo_identificasion(String tipo_identificasion) {
        this.tipo_identificasion = tipo_identificasion;
    }

    public String getNum_identificasion() {
        return num_identificasion;
    }

    public void setNum_identificasion(String num_identificasion) {
        this.num_identificasion = num_identificasion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido ;
    }
    
    
    
}
