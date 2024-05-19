/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iudigital.info.gestion.controller;

import com.iudigital.info.gestion.dao.TipoDocumentoDao;
import com.iudigital.info.gestion.domain.TipoDocumento;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin
 */
public class TipoDocumentoController {
    
    private TipoDocumentoDao tipoDocumentoDao;

    public TipoDocumentoController() {
        tipoDocumentoDao = new TipoDocumentoDao();
    }

    public List<TipoDocumento> getTipoDocumento() throws SQLException{
        return tipoDocumentoDao.obtenerTipoDocumentos();
    }
    
}
