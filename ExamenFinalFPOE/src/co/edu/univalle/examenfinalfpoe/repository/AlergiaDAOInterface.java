package co.edu.univalle.examenfinalfpoe.repository;

import co.edu.univalle.examenfinalfpoe.model.Alergia;
import java.util.Map;

public interface AlergiaDAOInterface {
    public Map<Integer, Alergia> getAlergias();
    
    public Alergia getAlergia(Integer llave);
    
    public boolean addAlergia(Alergia alergia);

}
