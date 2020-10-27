package Repositorio;

import Basis.Entidade;
import Enums.enumEntidade;

public abstract class Repositorio {
    public abstract Entidade seleciona(int id, enumEntidade entidade);
    public abstract Entidade localiza(String codigo, enumEntidade tipoEntidade);
    public abstract boolean salvar(Entidade entidade, enumEntidade tipoEntidade);
}
