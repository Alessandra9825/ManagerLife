package Repositorio;

import Basis.Entidade;
import Enums.enumEntidade;

import java.util.ArrayList;

public abstract class Repositorio {
    public abstract Entidade seleciona(int id, enumEntidade entidade) throws Exception;
    public abstract Entidade localiza(String codigo, enumEntidade tipoEntidade) throws Exception;
    public abstract boolean salvar(Entidade entidade, enumEntidade tipoEntidade) throws Exception;
    public abstract int busca(enumEntidade tipoEntidade,Entidade entidade) throws Exception;
    public abstract ArrayList listar () throws Exception;
    public abstract boolean alterar (Entidade entidade, enumEntidade tipoEntidade) throws Exception;
}
