package co.edu.univalle.examenfinalfpoe.repository;

import co.edu.univalle.examenfinalfpoe.model.Paciente;
import java.util.ArrayList;
import java.util.Map;

public interface PacienteDAOInterface {
    
    public Map<Integer, Paciente> getPacientes();
    
    public Paciente getPaciente(Integer llave);
    
    public boolean addPaciente(Paciente paciente);
    
    public boolean updatePaciente(Integer llave, Paciente paciente);
    
    public boolean updateAlergias(Integer llave, ArrayList<String> alergias);
}
