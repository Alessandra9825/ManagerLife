package Validacao;

import Basis.Entidade;

public abstract class Validacao<E extends Entidade> {
    protected Class<E> entityClass;

    public Validacao(Class<E> entityClass){
        this.entityClass = entityClass;
    }

    public abstract void ValidaDados(E entidade);
}
