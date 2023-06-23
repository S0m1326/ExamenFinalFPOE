package co.edu.univalle.examenfinalfpoe.repository;

import co.edu.univalle.examenfinalfpoe.model.Paciente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PacienteDAO implements PacienteDAOInterface{
    
    Map <Integer, Paciente> mapaPacientes = new HashMap();

    @Override
    public Map<Integer, Paciente> getPacientes() {
        return mapaPacientes;
    }

    @Override
    public Paciente getPaciente(Integer llave) {
        return mapaPacientes.get(llave);
    }

    @Override
    public boolean addPaciente(Paciente paciente) {
        mapaPacientes.put(paciente.getId(),paciente);
        System.out.println(mapaPacientes.get(paciente.getId()));
        return true;
    }

    @Override
    public boolean updatePaciente(Integer id, Paciente paciente) {
        mapaPacientes.put(id, paciente);
        return true;
    }

    @Override
    public boolean updateAlergias(Integer id, ArrayList<String> alergias) {
        mapaPacientes.get(id).setAlergias(alergias);
        return true;
    }

}
