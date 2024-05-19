/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.iudigital.info.gestion.presentacion;

import com.iudigital.info.gestion.controller.FuncionarioController;
import com.iudigital.info.gestion.domain.Funcionario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin
 */
public class InfoGestionApp {

    public static void obtenerFuncionarios(FuncionarioController funcionarioController) {
        try {
            List<Funcionario> funcionarios = funcionarioController.obtenerFuncionarios();
            
            if(funcionarios.isEmpty()){
                System.out.println("No hay funcionarios");
            }else{
                funcionarios.forEach(funcionario -> {
                    System.out.println("id = "+funcionario.getId());
                    System.out.println("tipo de identificasion = "+funcionario.getTipoIdentificacion());
                    System.out.println("numero de identificasion = "+funcionario.getNumIdentificacion());
                    System.out.println("nombre = "+funcionario.getNombre());
                    System.out.println("apellido = "+funcionario.getApellido());
                    System.out.println("estado civil = "+funcionario.getEstadoCivil());
                    System.out.println("sexo = "+funcionario.getSexo());
                    System.out.println("direccion = "+funcionario.getDireccion());
                    System.out.println("telefono = "+funcionario.getTelefono());
                    System.out.println("fecha de nacimiento = "+funcionario.getFechaNacimiento());  
                    System.out.println("----------------------------------------------------------");
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        
        FuncionarioController funcionarioController = new FuncionarioController();
        obtenerFuncionarios(funcionarioController);
    }
}
