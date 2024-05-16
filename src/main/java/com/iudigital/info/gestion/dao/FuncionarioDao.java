/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iudigital.info.gestion.dao;

import com.iudigital.info.gestion.domain.Funcionario;
import com.iudigital.info.gestion.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class FuncionarioDao {

    private static final String GET_FUNCIONARIOS = "select * from funcionarios";
    private static final String CREATE_FUNCIONARIO = "insert into funcionarios (tipo_identificasion, num_identificasion, nombre, apellido, estado_civil, sexo, direccion, telefono, fecha_nacimiento)"
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_FUNCIONARIO_BY_ID = "select * from funcionarios WHERE id=?";
    private static final String UPDATE_FUNCIONARIO = "update funcionarios set tipo_identificasion=?, num_identificasion=?, nombre=?, apellido=?, estado_civil=?, sexo=?, direccion=?, telefono=?, fecha_nacimiento=? WHERE id=?";
    private static final String DELETE_FUNCIONARIO = "delete from funcionarios WHERE id=?";

    public List<Funcionario> obtenerFuncionarios() throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Funcionario> funcionarios = new ArrayList();

        try {

            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_FUNCIONARIOS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultSet.getInt("id"));
                funcionario.setTipo_identificasion(resultSet.getString("tipo_identificasion"));
                funcionario.setNum_identificasion(resultSet.getString("num_identificasion"));
                funcionario.setNombre(resultSet.getString("nombre"));
                funcionario.setApellido(resultSet.getString("apellido"));
                funcionario.setEstado_civil(resultSet.getString("estado_civil"));
                funcionario.setSexo(resultSet.getString("sexo").charAt(0));
                funcionario.setDireccion(resultSet.getString("direccion"));
                funcionario.setTelefono(resultSet.getString("telefono"));

                // Obtener la fecha de nacimiento del ResultSet y convertirla a LocalDate
                Date fechaNacimientoDate = resultSet.getDate("fecha_nacimiento");
                LocalDate fechaNacimiento = fechaNacimientoDate.toLocalDate();

                funcionario.setFecha_nacimiento(fechaNacimiento);

                funcionarios.add(funcionario);

            }

            return funcionarios;
        } finally {

            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    public void crear(Funcionario funcionario) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_FUNCIONARIO);

            preparedStatement.setString(1, funcionario.getTipo_identificasion());
            preparedStatement.setString(2, funcionario.getNum_identificasion());
            preparedStatement.setString(3, funcionario.getNombre());
            preparedStatement.setString(4, funcionario.getApellido());
            preparedStatement.setString(5, funcionario.getEstado_civil());
            preparedStatement.setString(6, String.valueOf(funcionario.getSexo()));
            preparedStatement.setString(7, funcionario.getDireccion());
            preparedStatement.setString(8, funcionario.getTelefono());

            LocalDate fechaNacimientoDate = funcionario.getFecha_nacimiento();
            // Convertir LocalDate a java.sql.Date para usarlo en PreparedStatement
            java.sql.Date fechaNacimientoSQL = java.sql.Date.valueOf(fechaNacimientoDate);

            preparedStatement.setDate(9, fechaNacimientoSQL);

            preparedStatement.executeUpdate();
        } finally {

            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public Funcionario obtenerFuncionario(int id) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Funcionario funcionario = null;

        try {

            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_FUNCIONARIO_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                funcionario = new Funcionario();
                funcionario.setId(resultSet.getInt("id"));
                funcionario.setTipo_identificasion(resultSet.getString("tipo_identificasion"));
                funcionario.setNum_identificasion(resultSet.getString("num_identificasion"));
                funcionario.setNombre(resultSet.getString("nombre"));
                funcionario.setApellido(resultSet.getString("apellido"));
                funcionario.setEstado_civil(resultSet.getString("estado_civil"));
                funcionario.setSexo(resultSet.getString("sexo").charAt(0));
                funcionario.setDireccion(resultSet.getString("direccion"));
                funcionario.setTelefono(resultSet.getString("telefono"));

                // Obtener la fecha de nacimiento del ResultSet y convertirla a LocalDate
                Date fechaNacimientoDate = resultSet.getDate("fecha_nacimiento");
                LocalDate fechaNacimiento = fechaNacimientoDate.toLocalDate();

                funcionario.setFecha_nacimiento(fechaNacimiento);
            }

            return funcionario;
        } finally {

            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    public void actualizarFuncionario(int id, Funcionario funcionario) throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_FUNCIONARIO);

            preparedStatement.setString(1, funcionario.getTipo_identificasion());
            preparedStatement.setString(2, funcionario.getNum_identificasion());
            preparedStatement.setString(3, funcionario.getNombre());
            preparedStatement.setString(4, funcionario.getApellido());
            preparedStatement.setString(5, funcionario.getEstado_civil());
            preparedStatement.setString(6, String.valueOf(funcionario.getSexo()));
            preparedStatement.setString(7, funcionario.getDireccion());
            preparedStatement.setString(8, funcionario.getTelefono());

            LocalDate fechaNacimientoDate = funcionario.getFecha_nacimiento();
            // Convertir LocalDate a java.sql.Date para usarlo en PreparedStatement
            java.sql.Date fechaNacimientoSQL = java.sql.Date.valueOf(fechaNacimientoDate);

            preparedStatement.setDate(9, fechaNacimientoSQL);
            preparedStatement.setInt(10, id);

            preparedStatement.executeUpdate();
        } finally {

            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public void eliminarFuncionario(int id) throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_FUNCIONARIO);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            
        } finally {

            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
}
