package Repositorio.MSSQL;

import Basis.DAO;
import Basis.Entidade;
import Basis.FabricaDAO;
import Enums.enumEntidade;
import Repositorio.Repositorio;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepositorioMSSQL extends Repositorio {
    @Override
    public Entidade seleciona(int id, enumEntidade tipoEntidade) throws Exception {
        DAO dao = FabricaDAO.fabrica(tipoEntidade);
        Entidade entidade = dao.seleciona(id);
        return entidade;
    }

    @Override
    public Entidade localiza(String codigo, enumEntidade tipoEntidade) throws Exception {
        DAO dao = FabricaDAO.fabrica(tipoEntidade);
        Entidade entidade = null;
        try{
            entidade = dao.localiza(codigo);
        } catch (SQLException e){
            Logger.getLogger(RepositorioMSSQL.class.getName()).log(Level.SEVERE, null, e);
        }

        return entidade;
    }

    @Override
    public boolean salvar(Entidade entidade, enumEntidade tipoEntidade) throws Exception {
        DAO dao = FabricaDAO.fabrica(tipoEntidade);
        boolean salvado = false;

        try {
            salvado = dao.salvar(entidade);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return salvado;
    }

    @Override
    public int busca(enumEntidade tipoEntidade,Entidade entidadde) throws Exception {
        DAO dao = FabricaDAO.fabrica(tipoEntidade);
        Integer dados = dao.busca();
        return dados;
    }

    @Override
    public ArrayList listar() throws Exception {
        DAO dao = FabricaDAO.fabrica(enumEntidade.POSTIT);
        ArrayList<Entidade> listPost = dao.listarExpecifico();
        return listPost;
    }

    @Override
    public boolean alterar(Entidade entidade, enumEntidade tipoEntidade) throws Exception {
        DAO dao = FabricaDAO.fabrica(tipoEntidade);
        boolean salvado = false;

        try {
            salvado = dao.alterar(entidade);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return salvado;
    }
}
