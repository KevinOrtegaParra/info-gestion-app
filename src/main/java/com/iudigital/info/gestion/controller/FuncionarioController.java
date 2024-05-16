/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iudigital.info.gestion.controller;

import com.iudigital.info.gestion.dao.FuncionarioDao;
import com.iudigital.info.gestion.domain.Funcionario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin
 */
public class FuncionarioController {
    
    private FuncionarioDao funcionarioDao;
    
    public FuncionarioController(){
        funcionarioDao = new FuncionarioDao();
    }
    
    public List<Funcionario> obtenerFuncionarios()throws SQLException{
        
        return funcionarioDao.obtenerFuncionarios();
    }
    
    public void crear(Funcionario funcionario)throws SQLException{
        
        funcionarioDao.crear(funcionario);
    }
    
    public Funcionario obtenerFuncionario(int id)throws SQLException{
        
        return funcionarioDao.obtenerFuncionario(id);
    }
    
    public void actualizar(int id, Funcionario funcionario)throws SQLException{
        
        funcionarioDao.actualizarFuncionario(id, funcionario);
    }
    
    public void eliminar(int id)throws SQLException{
        
        funcionarioDao.eliminarFuncionario(id);
    }
    
}
