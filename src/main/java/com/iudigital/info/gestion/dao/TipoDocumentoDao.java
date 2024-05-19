/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iudigital.info.gestion.dao;

import com.iudigital.info.gestion.domain.TipoDocumento;
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
public class TipoDocumentoDao {
    
    private static final String GET_TIPOS_DOCUMENTOS = "select * from TipoDocumento";
    
        public List<TipoDocumento> obtenerTipoDocumentos() throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<TipoDocumento> tipoDocumentos = new ArrayList();

        try {

            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_TIPOS_DOCUMENTOS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                TipoDocumento tipoDocumento = new TipoDocumento();
                tipoDocumento.setId(resultSet.getInt("id"));
                tipoDocumento.setNombre(resultSet.getString("nombre"));

                tipoDocumentos.add(tipoDocumento);

            }

            return tipoDocumentos;
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
