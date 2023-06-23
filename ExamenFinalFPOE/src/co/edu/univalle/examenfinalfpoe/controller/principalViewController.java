package co.edu.univalle.examenfinalfpoe.controller;

import co.edu.univalle.examenfinalfpoe.model.Alergia;
import co.edu.univalle.examenfinalfpoe.model.Paciente;
import co.edu.univalle.examenfinalfpoe.repository.AlergiaDAO;
import co.edu.univalle.examenfinalfpoe.repository.PacienteDAO;
import co.edu.univalle.examenfinalfpoe.view.principalView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;


public class principalViewController {
    
    private principalView principalView;
    private javax.swing.JComboBox comboAlergias;
    private PacienteDAO pacienteDAO;
    private AlergiaDAO alergiaDAO;
    private Map mapaPacientes;
    private TreeMap mapaAlergias;
    private java.util.List listaParametros;
    private DefaultComboBoxModel<String> listModel;
    private int index;
    private ArrayList<String> alergiasNewPaciente;
    private ArrayList<String> alergiasPaciente;
    private DefaultListModel<String> model;
    private DefaultListModel<String> modelVacio;

    public principalViewController(principalView principalView) {
        this.principalView = principalView;
        this.comboAlergias = principalView.getComboAlergias();
        this.alergiaDAO = new AlergiaDAO();
        this.pacienteDAO = new PacienteDAO();
        
        HandlerActions listener = new HandlerActions();
        
        principalView.addBtnActualizar(listener);
        principalView.addBtnAgregar(listener);
        principalView.addBtnCancelar(listener);
        principalView.addBtnVerificar(listener);
        principalView.addComboAlergias(listener);
        
        this.listaParametros = new ArrayList();
        this.alergiasNewPaciente = new ArrayList<>();
        this.alergiasPaciente = new ArrayList<>();
        this.model = new DefaultListModel<>();
        this.modelVacio = new DefaultListModel<>();
        
        principalView.getBtnAgregar().setEnabled(false);
        
        Alergias();
        mapaAlergias = alergiaDAO.getAlergias();
        mapaPacientes = pacienteDAO.getPacientes();
        
        actualizarComboAlergias();
    }
    
    private void Alergias(){
        FileReader frAlergias = null;
        listaParametros.clear();
        BufferedReader brAlergias = null;
        try {
            File archivo = new File("alergias.txt");
            frAlergias = new FileReader(archivo);
            brAlergias = new BufferedReader(frAlergias);
            String linea;
            while((linea = brAlergias.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(linea,",");
                while(tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    listaParametros.add(token);
                }
                if(listaParametros.size() == 2) {
                    alergiaDAO.addAlergia(new Alergia(Integer.parseInt((String)listaParametros.get(0)), 
                            (String)listaParametros.get(1)));
                }
                listaParametros.clear();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(principalViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(principalViewController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
            if (brAlergias != null) {
                    brAlergias.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(principalViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void actualizarComboAlergias() {        
        Set<Map.Entry<Integer, Alergia>> entrySetMapa = mapaAlergias.entrySet();

        listModel = new DefaultComboBoxModel<>();

        for (Map.Entry<Integer, Alergia> entry : entrySetMapa){
            Alergia value = entry.getValue();
            String item = value.getId() + ". " + value;
            
            listModel.addElement(item);
            
        }
        comboAlergias.setModel(listModel);
    }
    
    public void actualizarJlist(ArrayList<String> List) {
        for (String alergia : List) {
            model.addElement(alergia);
        }
        
        System.out.println(model);
        principalView.setListAlergias(model);
    }
    
    public void limpiarCampos () {
        alergiasNewPaciente.clear();
        alergiasPaciente.clear();
        model.clear();
        
        principalView.setjTxtNombres("");
        principalView.setjTxtApellidos("");
        principalView.setjTxtTelefono("");
        principalView.setjTxtDireccion("");
        principalView.setjTxtId("");
        actualizarJlist(alergiasPaciente);
    }
    
    public void verificarPaciente(Integer id) {
        boolean verificar = false;
        
        if (mapaPacientes.containsKey(id)) {
            verificar = true;
            principalView.getBtnActualizar().setText("Actualizar");
            Paciente paciente = (Paciente) mapaPacientes.get(id);
            principalView.setjTxtNombres(paciente.getNombre());
            principalView.setjTxtApellidos(paciente.getApellido());
            principalView.setjTxtTelefono(paciente.getTelefono());
            principalView.setjTxtDireccion(paciente.getDireccion());

            alergiasNewPaciente = paciente.getAlergias();
            System.out.println(alergiasNewPaciente);
            actualizarJlist(alergiasNewPaciente);
            principalView.getBtnAgregar().setEnabled(true);
            System.out.println(verificar);
        }
        
        if (!verificar) {
            System.out.println("No existe el paciente");
            principalView.getBtnActualizar().setText("Agregar");
            principalView.getBtnAgregar().setEnabled(true);
            ListModel<String> listModel = principalView.getListAlergias().getModel();
            for (int i = 0; i < listModel.getSize(); i++) {
                alergiasNewPaciente.add(listModel.getElementAt(i));
            }
            if (principalView.getjTxtNombres().isEmpty() || principalView.getjTxtApellidos().isEmpty() || principalView.getjTxtTelefono().isEmpty() || principalView.getjTxtDireccion().isEmpty()) {
                System.out.println("Por favor diligencie todos los campos de información");
            } else if (alergiasNewPaciente.isEmpty()) {
                System.out.println("Agregue las alergias del paciente");
            } else {
                
            }
        }
    }

    class HandlerActions implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == principalView.getBtnVerificar()) {
                if(principalView.getjTxtId() == -1){
                    System.out.println("Vacio");
                } else {
                    Integer id = principalView.getjTxtId();
                    verificarPaciente(id);
                }
            }
            if (e.getSource() == principalView.getBtnAgregar()) {
                index = comboAlergias.getSelectedIndex();
                
                boolean repetido = true;
                
                for (int i = 0; i < alergiasNewPaciente.size(); i++) {
                    String elemento = alergiasNewPaciente.get(i);
                    if (elemento.equalsIgnoreCase(alergiaDAO.getAlergia(index).toString())){
                        System.out.println("Alergia ya agregada");
                        repetido = false;
                        break;
                    }
                }
                
                if (repetido) {
                    alergiasNewPaciente.add(alergiaDAO.getAlergia(index).toString());
                    model.clear();
                    actualizarJlist(alergiasNewPaciente);
                }
            }
            if (e.getSource() == principalView.getBtnActualizar()) {
                pacienteDAO.addPaciente(new Paciente(principalView.getjTxtId(),principalView.getjTxtNombres(),principalView.getjTxtApellidos(),principalView.getjTxtTelefono(), principalView.getjTxtDireccion()));
                for (int i = 0; i < alergiasNewPaciente.size(); i++) {
                    String elemento = alergiasNewPaciente.get(i);
                    pacienteDAO.getPaciente(principalView.getjTxtId()).getAlergias().add(elemento);
                }
                if (principalView.getBtnActualizar().getText().equals("Agregar")) {
                    System.out.println("Paciente Creado");
                } else if (principalView.getBtnActualizar().getText().equals("Actualizar")) {
                    System.out.println("Paciente Actualizado");
                }

                limpiarCampos();

                principalView.getBtnAgregar().setEnabled(false);
            }
            if (e.getSource() == principalView.getBtnCancelar()) {
                limpiarCampos();
                principalView.getBtnAgregar().setEnabled(false);
            }
        }
    }
}
