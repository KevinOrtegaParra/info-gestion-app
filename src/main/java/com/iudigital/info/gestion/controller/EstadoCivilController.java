/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iudigital.info.gestion.controller;

import com.iudigital.info.gestion.dao.EstadoCivilDao;
import com.iudigital.info.gestion.domain.EstadoCivil;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin
 */
public class EstadoCivilController {

    private EstadoCivilDao estadoCivilDao;

    public EstadoCivilController() {
        estadoCivilDao = new EstadoCivilDao();
    }

    public List<EstadoCivil> getEstadoCivil() throws SQLException {
        return estadoCivilDao.obtenerEstadoCiviles();
    }
}
