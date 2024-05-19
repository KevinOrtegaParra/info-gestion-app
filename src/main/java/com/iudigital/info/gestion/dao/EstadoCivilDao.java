/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iudigital.info.gestion.dao;

import com.iudigital.info.gestion.domain.EstadoCivil;
import com.iudigital.info.gestion.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class EstadoCivilDao {
    
    private static final String GET_ESTADO_CIVILES = "select * from EstadoCivil";
    
        public List<EstadoCivil> obtenerEstadoCiviles() throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<EstadoCivil> estadoCiviles = new ArrayList();

        try {

            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_ESTADO_CIVILES);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                EstadoCivil estadoCivil = new EstadoCivil();
                estadoCivil.setId(resultSet.getInt("id"));
                estadoCivil.setNombre(resultSet.getString("nombre"));

                estadoCiviles.add(estadoCivil);

            }

            return estadoCiviles;
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
}
