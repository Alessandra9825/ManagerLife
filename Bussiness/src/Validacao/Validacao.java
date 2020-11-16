package Validacao;

import Basis.Entidade;

import java.util.ArrayList;

public abstract class Validacao<E extends Entidade> {
    protected Class<E> entityClass;

    public Validacao(Class<E> entityClass){
        this.entityClass = entityClass;
    }



    public boolean isNullorEmpty(String param){
        return param == null || param.isEmpty();
    }

    public abstract ArrayList<String> ValidaDados(E entidade);
}
