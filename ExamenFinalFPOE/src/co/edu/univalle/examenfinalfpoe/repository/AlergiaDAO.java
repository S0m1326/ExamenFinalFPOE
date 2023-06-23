package co.edu.univalle.examenfinalfpoe.repository;

import co.edu.univalle.examenfinalfpoe.model.Alergia;
import java.util.HashMap;
import java.util.TreeMap;

public class AlergiaDAO implements AlergiaDAOInterface {
    
    TreeMap <Integer, Alergia> mapaAlergias = new TreeMap();

    @Override
    public TreeMap<Integer, Alergia> getAlergias() {
        return mapaAlergias;
    }

    @Override
    public Alergia getAlergia(Integer llave) {
        return mapaAlergias.get(llave);
    }

    @Override
    public boolean addAlergia(Alergia alergia) {
        mapaAlergias.put(alergia.getId(), alergia);
        return true;
    }
    
}
