/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.iudigital.info.gestion.presentacion;

import com.iudigital.info.gestion.controller.EstadoCivilController;
import com.iudigital.info.gestion.controller.FuncionarioController;
import com.iudigital.info.gestion.controller.TipoDocumentoController;
import com.iudigital.info.gestion.domain.EstadoCivil;
import com.iudigital.info.gestion.domain.Funcionario;
import com.iudigital.info.gestion.domain.TipoDocumento;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kevin
 */
public class Main extends javax.swing.JFrame {

    private final FuncionarioController funcionarioController;
    private final TipoDocumentoController tipoDocumentoController;
    private final EstadoCivilController estadoCivilController;
    //table funcionario title
    private static final String ids[] = {"id", "Tipo identificasion", "Numero identificasion", "Nombre", "Apellido", "Estado Civil", "Sexo", "Direccion", "Telefono", "Fecha nacimiento"};
    private static final String SELECCIONE = "---SELECCIONE---";

    public Main() {
        initComponents();

        txtFuncionarioIdEdit.setEditable(false);
        funcionarioController = new FuncionarioController();
        tipoDocumentoController = new TipoDocumentoController();
        estadoCivilController = new EstadoCivilController();
        listarFuncionarios();
        listarTipoDocumento();
        listarEstadoCivil();
        addListener();
    }

    private void listarFuncionarios() {
        cbxFuncionarios.removeAllItems();
        Funcionario funcionarioCombo = new Funcionario();
        funcionarioCombo.setNombre(SELECCIONE);
        funcionarioCombo.setApellido("");
        cbxFuncionarios.addItem(funcionarioCombo);

        DefaultTableModel mt = new DefaultTableModel();
        for (String COLUM : ids) {
            mt.addColumn(COLUM);
        }
        tblFuncionario.setModel(mt);
        try {
            List<Funcionario> funcionarios = funcionarioController.obtenerFuncionarios();
            if (funcionarios.isEmpty()) {
                return;
            }
            mt.setRowCount(funcionarios.size());
            int row = 0;
            for (Funcionario funcionario : funcionarios) {
                mt.setValueAt(funcionario.getId(), row, 0);
                mt.setValueAt(funcionario.getTipoIdentificacionNombre(), row, 1);
                mt.setValueAt(funcionario.getNumIdentificacion(), row, 2);
                mt.setValueAt(funcionario.getNombre(), row, 3);
                mt.setValueAt(funcionario.getApellido(), row, 4);
                mt.setValueAt(funcionario.getEstadoCivilNombre(), row, 5);
                mt.setValueAt(funcionario.getSexo(), row, 6);
                mt.setValueAt(funcionario.getDireccion(), row, 7);
                mt.setValueAt(funcionario.getTelefono(), row, 8);
                mt.setValueAt(funcionario.getFechaNacimiento(), row, 9);
                row++;

                cbxFuncionarios.addItem(funcionario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    private void listarTipoDocumento(){
        cbxTipoIdentificasion.removeAllItems();
        cbxTipoIdentificaionEdit.removeAllItems();
        TipoDocumento tipoDocumento = new TipoDocumento();
        tipoDocumento.setNombre(SELECCIONE);
        cbxTipoIdentificasion.addItem(tipoDocumento);
        cbxTipoIdentificaionEdit.addItem(tipoDocumento);
        try {
            List<TipoDocumento> tipoDocumentos = tipoDocumentoController.getTipoDocumento();
            for(TipoDocumento Documento : tipoDocumentos){
                cbxTipoIdentificasion.addItem(Documento);
                cbxTipoIdentificaionEdit.addItem(Documento);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void listarEstadoCivil(){
        cbxEstadoCivil.removeAllItems();
        cbxEstadoCivilEdit.removeAllItems();
        EstadoCivil estadoCivil = new EstadoCivil();
        estadoCivil.setNombre(SELECCIONE);
        cbxEstadoCivil.addItem(estadoCivil);
        cbxEstadoCivilEdit.addItem(estadoCivil);
        try {
            List<EstadoCivil> estados = estadoCivilController.getEstadoCivil();
            for(EstadoCivil estado : estados){
                cbxEstadoCivil.addItem(estado);
                cbxEstadoCivilEdit.addItem(estado);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void addListener() {
        cbxFuncionarios.addItemListener(event -> {
            Funcionario selecFuncionario = (Funcionario) event.getItem();
            if (selecFuncionario.getNombre().equals(SELECCIONE)) {
                txtFuncionarioIdEdit.setText("");
                cbxTipoIdentificaionEdit.setSelectedIndex(0);
                txtNumIdentificasionEdit.setText("");
                txtNombreEdit.setText("");
                txtApellidoEdit.setText("");
                cbxEstadoCivilEdit.setSelectedIndex(0);
                Group2SexoEdit.clearSelection();
                txtDireccionEdit.setText("");
                txtTelefonoEdit.setText("");
                txtFechaNacimientoEdit.setText("");
            } else {
                txtFuncionarioIdEdit.setText(String.valueOf(selecFuncionario.getId()));
                cbxTipoIdentificaionEdit.setSelectedIndex(Integer.parseInt(selecFuncionario.getTipoIdentificacion()));
                txtNumIdentificasionEdit.setText(selecFuncionario.getNumIdentificacion());
                txtNombreEdit.setText(selecFuncionario.getNombre());
                txtApellidoEdit.setText(selecFuncionario.getApellido());
                cbxEstadoCivilEdit.setSelectedIndex(Integer.parseInt(selecFuncionario.getEstadoCivil()));
                if (selecFuncionario.getSexo() == 'M') {
                    jRadioButton3.setSelected(true);
                } else if (selecFuncionario.getSexo() == 'F') {
                    jRadioButton4.setSelected(true);
                }
                txtDireccionEdit.setText(selecFuncionario.getDireccion());
                txtTelefonoEdit.setText(selecFuncionario.getTelefono());
                txtFechaNacimientoEdit.setText(String.valueOf(selecFuncionario.getFechaNacimiento()));
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Group1Sexo = new javax.swing.ButtonGroup();
        Group2SexoEdit = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanels = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPFun = new javax.swing.JPanel();
        lblNumIdentificasion = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        lblEstadoCivil = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtNumIdentificasion = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        lblSexo = new javax.swing.JLabel();
        lblTipoIdentificasion = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();
        cbxTipoIdentificasion = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtFechaNacimiento = new javax.swing.JFormattedTextField();
        cbxEstadoCivil = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFuncionario = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnDelete1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        cbxFuncionarios = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        lblTipoIdentificasionEdit = new javax.swing.JLabel();
        lblNumIdentificasionEdit = new javax.swing.JLabel();
        lblNombreEdit = new javax.swing.JLabel();
        lblTelefonoEdit = new javax.swing.JLabel();
        lblApellidoEdit = new javax.swing.JLabel();
        lblEstadoCivilEdit = new javax.swing.JLabel();
        lblSexoEdit = new javax.swing.JLabel();
        lblDreccionEdit = new javax.swing.JLabel();
        txtNumIdentificasionEdit = new javax.swing.JTextField();
        txtApellidoEdit = new javax.swing.JTextField();
        txtNombreEdit = new javax.swing.JTextField();
        txtDireccionEdit = new javax.swing.JTextField();
        txtTelefonoEdit = new javax.swing.JTextField();
        cbxTipoIdentificaionEdit = new javax.swing.JComboBox<>();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        btnEdit = new javax.swing.JButton();
        lblFechaNacimientoEdit = new javax.swing.JLabel();
        txtFechaNacimientoEdit = new javax.swing.JFormattedTextField();
        lblIdEdit = new javax.swing.JLabel();
        txtFuncionarioIdEdit = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        cbxEstadoCivilEdit = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("GESTIÓN DE PERSONAL IUDIGITAL");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 250, 30));

        jPanel1.setName(""); // NOI18N

        jPFun.setBorder(javax.swing.BorderFactory.createTitledBorder("Digite los siguientes campos"));

        lblNumIdentificasion.setText("Numero de identificasion");

        lblNombre.setText("Nombre");

        lblApellido.setText("Apellido");

        lblEstadoCivil.setText("Estado civil");

        lblDireccion.setText("Direccion");

        lblTelefono.setText("Telefono");

        txtNumIdentificasion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumIdentificasionActionPerformed(evt);
            }
        });

        Group1Sexo.add(jRadioButton1);
        jRadioButton1.setText("Masculino");

        Group1Sexo.add(jRadioButton2);
        jRadioButton2.setText("Femenino");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        lblSexo.setText("Sexo");

        lblTipoIdentificasion.setText("Tipo de identificasion");

        btnCreate.setText("GUARDAR");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        cbxTipoIdentificasion.setToolTipText("");
        cbxTipoIdentificasion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoIdentificasionActionPerformed(evt);
            }
        });

        jLabel11.setText("Fecha de Nacimiento (yyyy-MM-dd)");

        txtFechaNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaNacimientoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPFunLayout = new javax.swing.GroupLayout(jPFun);
        jPFun.setLayout(jPFunLayout);
        jPFunLayout.setHorizontalGroup(
            jPFunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFunLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPFunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPFunLayout.createSequentialGroup()
                        .addGap(309, 309, 309)
                        .addComponent(btnCreate)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPFunLayout.createSequentialGroup()
                        .addGroup(jPFunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPFunLayout.createSequentialGroup()
                                .addGroup(jPFunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNumIdentificasion, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPFunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblApellido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                        .addComponent(lblNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lblTipoIdentificasion))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPFunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellido)
                                    .addComponent(cbxTipoIdentificasion, 0, 170, Short.MAX_VALUE)
                                    .addComponent(txtNumIdentificasion, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addGroup(jPFunLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(jPFunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSexo)
                            .addGroup(jPFunLayout.createSequentialGroup()
                                .addGroup(jPFunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDireccion)
                                    .addComponent(lblTelefono))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPFunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTelefono)
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPFunLayout.createSequentialGroup()
                                        .addComponent(jRadioButton1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButton2))
                                    .addComponent(cbxEstadoCivil, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(35, Short.MAX_VALUE))))
        );
        jPFunLayout.setVerticalGroup(
            jPFunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFunLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPFunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstadoCivil)
                    .addComponent(lblTipoIdentificasion)
                    .addComponent(cbxTipoIdentificasion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPFunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSexo)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(lblNumIdentificasion)
                    .addComponent(txtNumIdentificasion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccion)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(btnCreate))
        );

        tblFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblFuncionario);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 706, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        btnDelete1.setText("Eliminar");
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPFun, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDelete1)
                .addGap(321, 321, 321))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPFun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete1)
                .addGap(110, 110, 110)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanels.addTab("Crear Funcionario", jPanel1);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(" Modifique los siguientes campos"));
        jPanel3.setName(""); // NOI18N

        cbxFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFuncionariosActionPerformed(evt);
            }
        });

        jLabel2.setText("Funcionarios");

        lblTipoIdentificasionEdit.setText("Tipo de identificasion");

        lblNumIdentificasionEdit.setText("Numero de identificasion");

        lblNombreEdit.setText("Nombre");

        lblTelefonoEdit.setText("Telefono");

        lblApellidoEdit.setText("Apellido");

        lblEstadoCivilEdit.setText("Estado civil");

        lblSexoEdit.setText("Sexo");

        lblDreccionEdit.setText("Direccion");

        txtNumIdentificasionEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumIdentificasionEditActionPerformed(evt);
            }
        });

        txtTelefonoEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoEditActionPerformed(evt);
            }
        });

        Group2SexoEdit.add(jRadioButton3);
        jRadioButton3.setText("Masculino");

        Group2SexoEdit.add(jRadioButton4);
        jRadioButton4.setText("Femenino");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        btnEdit.setText("Actualizar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        lblFechaNacimientoEdit.setText("Fecha de Nacimiento (yyyy-MM-dd)");

        txtFechaNacimientoEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaNacimientoEditActionPerformed(evt);
            }
        });

        lblIdEdit.setText("Id");

        btnDelete.setText("Eliminar");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumIdentificasionEdit)
                            .addComponent(lblNombreEdit)
                            .addComponent(lblApellidoEdit)
                            .addComponent(lblTipoIdentificasionEdit)
                            .addComponent(lblIdEdit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumIdentificasionEdit)
                            .addComponent(cbxTipoIdentificaionEdit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFuncionarioIdEdit)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnEdit)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtNombreEdit, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtApellidoEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lblFechaNacimientoEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtFechaNacimientoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblEstadoCivilEdit)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblDreccionEdit)
                                                .addComponent(lblTelefonoEdit)
                                                .addComponent(lblSexoEdit))
                                            .addGap(44, 44, 44)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtTelefonoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtDireccionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(319, 319, 319))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(btnDelete))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(191, 191, 191)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbxEstadoCivilEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27)
                        .addComponent(cbxFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstadoCivilEdit)
                    .addComponent(lblIdEdit)
                    .addComponent(txtFuncionarioIdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxEstadoCivilEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSexoEdit)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4)
                    .addComponent(lblTipoIdentificasionEdit)
                    .addComponent(cbxTipoIdentificaionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDreccionEdit)
                    .addComponent(txtDireccionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumIdentificasionEdit)
                    .addComponent(txtNumIdentificasionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefonoEdit)
                    .addComponent(txtTelefonoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreEdit)
                    .addComponent(txtNombreEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaNacimientoEdit)
                    .addComponent(txtFechaNacimientoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidoEdit))
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete))
                .addContainerGap(153, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 718, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanels.addTab("Actualizar Funcionario", jPanel2);

        getContentPane().add(jPanels, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 730, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNumIdentificasionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumIdentificasionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumIdentificasionActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void cbxFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFuncionariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFuncionariosActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if (cbxTipoIdentificasion.getSelectedItem() == SELECCIONE) {
            JOptionPane.showMessageDialog(null, "Digite el Tipo de Identificación");
            cbxTipoIdentificasion.requestFocus();
            return;
        }
        if (txtNumIdentificasion.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite Numero de Identificación");
            txtNumIdentificasion.requestFocus();
            return;
        }
        if (txtNombre.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite Nombre");
            txtNombre.requestFocus();
            return;
        }
        if (txtApellido.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite Apellido");
            txtApellido.requestFocus();
            return;
        }
        if (cbxEstadoCivil.getSelectedItem() == SELECCIONE) {
            JOptionPane.showMessageDialog(null, "Digite el Estado Sivil");
            cbxEstadoCivil.requestFocus();
            return;
        }
        if (Group1Sexo.getSelection() == null) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción de genero");
            return;
        }
        if (txtDireccion.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite Direccion");
            txtDireccion.requestFocus();
            return;
        }
        if (txtTelefono.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite Telefono");
            txtTelefono.requestFocus();
            return;
        }
        if (txtFechaNacimiento.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite Fecha de Nacimiento");
            txtFechaNacimiento.requestFocus();
            return;
        }

        try {
            Funcionario funcionaro = new Funcionario();
            funcionaro.setTipoIdentificacion(String.valueOf(cbxTipoIdentificasion.getSelectedIndex()));
            funcionaro.setTipoIdentificacionNombre(String.valueOf(cbxTipoIdentificasion.getSelectedItem()));
            funcionaro.setNumIdentificacion(txtNumIdentificasion.getText().trim());
            funcionaro.setNombre(txtNombre.getText().trim());
            funcionaro.setApellido(txtApellido.getText().trim());
            funcionaro.setEstadoCivil(String.valueOf(cbxEstadoCivil.getSelectedIndex()));
            funcionaro.setEstadoCivilNombre(String.valueOf(cbxEstadoCivil.getSelectedItem()));
            funcionaro.setSexo(jRadioButton1.isSelected() ? 'M' : 'F');
            funcionaro.setDireccion(txtDireccion.getText().trim());
            funcionaro.setTelefono(txtTelefono.getText().trim());

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fechaTexto = txtFechaNacimiento.getText().trim();
                LocalDate fechaNacimiento = LocalDate.parse(fechaTexto, formatter);

                funcionaro.setFechaNacimiento(fechaNacimiento);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Use el formato yyyy-MM-dd\nEjemplo = 2003-10-02");
                txtFechaNacimiento.requestFocus();
                return;
            }

            funcionarioController.crear(funcionaro);
            cbxTipoIdentificasion.setSelectedIndex(0);
            txtNumIdentificasion.setText("");
            txtNombre.setText("");
            txtApellido.setText("");
            cbxEstadoCivil.setSelectedIndex(0);
            Group1Sexo.clearSelection();
            txtDireccion.setText("");
            txtTelefono.setText("");
            txtFechaNacimiento.setText("");

            listarFuncionarios();
            JOptionPane.showMessageDialog(null, "Funcionario creado con Éxito!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al crear Funcionario");
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (txtFuncionarioIdEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un Funcionario de la lista");
            cbxFuncionarios.requestFocus();
            return;
        }
        if (cbxTipoIdentificaionEdit.getSelectedItem() == SELECCIONE) {
            JOptionPane.showMessageDialog(null, "Digite el Tipo de Identificación");
            cbxTipoIdentificaionEdit.requestFocus();
            return;
        }
        if (txtNumIdentificasionEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite Numero de Identificación");
            txtNumIdentificasionEdit.requestFocus();
            return;
        }
        if (txtNombreEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite Nombre");
            txtNombreEdit.requestFocus();
            return;
        }
        if (txtApellidoEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite Apellido");
            txtApellidoEdit.requestFocus();
            return;
        }
        if (cbxEstadoCivilEdit.getSelectedItem() == SELECCIONE) {
            JOptionPane.showMessageDialog(null, "Digite el Estado Sivil");
            cbxEstadoCivilEdit.requestFocus();
            return;
        }
        if (Group2SexoEdit.getSelection() == null) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción de genero");
            return;
        }
        if (txtDireccionEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite Direccion");
            txtDireccionEdit.requestFocus();
            return;
        }
        if (txtTelefonoEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite Telefono");
            txtTelefonoEdit.requestFocus();
            return;
        }
        if (txtFechaNacimientoEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite Fecha de Nacimiento");
            txtFechaNacimientoEdit.requestFocus();
            return;
        }
        Funcionario funcionaro = new Funcionario();
        funcionaro.setTipoIdentificacion(String.valueOf(cbxTipoIdentificaionEdit.getSelectedIndex()));
        funcionaro.setTipoIdentificacionNombre(String.valueOf(cbxTipoIdentificaionEdit.getSelectedItem()));
        funcionaro.setNumIdentificacion(txtNumIdentificasionEdit.getText().trim());
        funcionaro.setNombre(txtNombreEdit.getText().trim());
        funcionaro.setApellido(txtApellidoEdit.getText().trim());
        funcionaro.setEstadoCivil(String.valueOf(cbxEstadoCivilEdit.getSelectedIndex()));
        funcionaro.setEstadoCivilNombre(String.valueOf(cbxEstadoCivilEdit.getSelectedItem()));
        funcionaro.setSexo(jRadioButton3.isSelected() ? 'M' : 'F');
        funcionaro.setDireccion(txtDireccionEdit.getText().trim());
        funcionaro.setTelefono(txtTelefonoEdit.getText().trim());

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaTexto = txtFechaNacimientoEdit.getText().trim();
            LocalDate fechaNacimiento = LocalDate.parse(fechaTexto, formatter);

            funcionaro.setFechaNacimiento(fechaNacimiento);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Use el formato yyyy-MM-dd\nEjemplo = 2003-10-02");
            txtFechaNacimientoEdit.requestFocus();
            return;
        }
        int opc = JOptionPane.showConfirmDialog(null, "Desea actualizar el funcionario", "Confirmar salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (opc == 0) {
            try {

                funcionarioController.actualizar(Integer.parseInt(txtFuncionarioIdEdit.getText()), funcionaro);
                cbxTipoIdentificaionEdit.setSelectedIndex(0);
                txtNumIdentificasionEdit.setText("");
                txtNombreEdit.setText("");
                txtApellidoEdit.setText("");
                cbxEstadoCivilEdit.setSelectedIndex(0);
                Group2SexoEdit.clearSelection();
                txtDireccionEdit.setText("");
                txtTelefonoEdit.setText("");
                txtFechaNacimientoEdit.setText("");

                listarFuncionarios();
                JOptionPane.showMessageDialog(null, "Funcionario actualizado con Éxito!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al actualizado Funcionario");
            }
        }

    }//GEN-LAST:event_btnEditActionPerformed

    private void txtFechaNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaNacimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaNacimientoActionPerformed

    private void txtFechaNacimientoEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaNacimientoEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaNacimientoEditActionPerformed

    private void txtTelefonoEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoEditActionPerformed

    private void txtNumIdentificasionEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumIdentificasionEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumIdentificasionEditActionPerformed

    private void cbxTipoIdentificasionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoIdentificasionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoIdentificasionActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (txtFuncionarioIdEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un Funcionario de la lista");
            cbxFuncionarios.requestFocus();
            return;
        }
        int opc = JOptionPane.showConfirmDialog(null, "Desea eliminar el funcionario", "Confirmar salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (opc == 0) {
            try {

                funcionarioController.eliminar(Integer.parseInt(txtFuncionarioIdEdit.getText()));
                cbxTipoIdentificaionEdit.setSelectedIndex(0);
                txtNumIdentificasionEdit.setText("");
                txtNombreEdit.setText("");
                txtApellidoEdit.setText("");
                cbxEstadoCivilEdit.setSelectedIndex(0);
                Group2SexoEdit.clearSelection();
                txtDireccionEdit.setText("");
                txtTelefonoEdit.setText("");
                txtFechaNacimientoEdit.setText("");

                listarFuncionarios();
                JOptionPane.showMessageDialog(null, "Funcionario eliminado con Éxito!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al eliminar Funcionario");
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed

        int selectedRow = tblFuncionario.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un Funcionario de la Tabla");
        } else {
            int opc = JOptionPane.showConfirmDialog(null, "Desea eliminar el funcionario", "Confirmar salida",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (opc == 0) {
                Object id = tblFuncionario.getValueAt(selectedRow, 0);
                try {

                    funcionarioController.eliminar((Integer) id);
                    cbxTipoIdentificaionEdit.setSelectedIndex(0);
                    txtNumIdentificasionEdit.setText("");
                    txtNombreEdit.setText("");
                    txtApellidoEdit.setText("");
                    cbxEstadoCivilEdit.setSelectedIndex(0);
                    Group2SexoEdit.clearSelection();
                    txtDireccionEdit.setText("");
                    txtTelefonoEdit.setText("");
                    txtFechaNacimientoEdit.setText("");

                    listarFuncionarios();
                    JOptionPane.showMessageDialog(null, "Funcionario eliminado con Éxito!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar Funcionario");
                }
            }
        }

    }//GEN-LAST:event_btnDelete1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Group1Sexo;
    private javax.swing.ButtonGroup Group2SexoEdit;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnEdit;
    private javax.swing.JComboBox<EstadoCivil> cbxEstadoCivil;
    private javax.swing.JComboBox<EstadoCivil> cbxEstadoCivilEdit;
    private javax.swing.JComboBox<Funcionario> cbxFuncionarios;
    private javax.swing.JComboBox<TipoDocumento> cbxTipoIdentificaionEdit;
    private javax.swing.JComboBox<TipoDocumento> cbxTipoIdentificasion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPFun;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jPanels;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblApellidoEdit;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDreccionEdit;
    private javax.swing.JLabel lblEstadoCivil;
    private javax.swing.JLabel lblEstadoCivilEdit;
    private javax.swing.JLabel lblFechaNacimientoEdit;
    private javax.swing.JLabel lblIdEdit;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombreEdit;
    private javax.swing.JLabel lblNumIdentificasion;
    private javax.swing.JLabel lblNumIdentificasionEdit;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblSexoEdit;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTelefonoEdit;
    private javax.swing.JLabel lblTipoIdentificasion;
    private javax.swing.JLabel lblTipoIdentificasionEdit;
    private javax.swing.JTable tblFuncionario;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtApellidoEdit;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDireccionEdit;
    private javax.swing.JFormattedTextField txtFechaNacimiento;
    private javax.swing.JFormattedTextField txtFechaNacimientoEdit;
    private javax.swing.JTextField txtFuncionarioIdEdit;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreEdit;
    private javax.swing.JTextField txtNumIdentificasion;
    private javax.swing.JTextField txtNumIdentificasionEdit;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoEdit;
    // End of variables declaration//GEN-END:variables
}
