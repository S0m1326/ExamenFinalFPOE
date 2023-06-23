package co.edu.univalle.examenfinalfpoe.model;

import java.io.Serializable;

public class Alergia implements Serializable{
    private Integer id;
    private String alergia;

    public Alergia(Integer id, String alergia) {
        this.id = id;
        this.alergia = alergia;
    }

    public Integer getId() {
        return id;
    }

    public String getAlergia() {
        return alergia;
    }

    @Override
    public String toString() {
        return "" + alergia;
    }
    
}
