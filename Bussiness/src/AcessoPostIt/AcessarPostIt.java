package AcessoPostIt;

import Auditoria.GerenciadorAuditoria;
import Enums.enumEntidade;
import Repositorio.MSSQL.RepositorioMSSQL;
import Repositorio.Repositorio;
import vos.PostIt;

import java.util.ArrayList;

public class AcessarPostIt {
    private final String meth = "PostIt - ";

    //Salva um postIt no banco depois de todas as validações necessarias
    public boolean salvarPostIt(PostIt data) throws Exception {
        Repositorio repositorio = new RepositorioMSSQL();
        Boolean valida  = repositorio.salvar(data,enumEntidade.POSTIT);
        if(!valida)
        {
            GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "PostIt não cadastrado");
            return false;
        }
        else
        {
            return true;
        }
    }

    //Conta a quantidade de postIt DO cadastrados no banco
    //para poder limitar a criação de acordo com o espaço da tela
    public int contaPostIt(PostIt data) throws Exception {
        Repositorio repositorio = new RepositorioMSSQL();
        if (repositorio.busca(enumEntidade.POSTIT,data) == 3)
        {
            return 3;
        }
        else
        {
            return repositorio.busca(enumEntidade.POSTIT,data);
        }

    }

    //Carrega os dados dos postIt existentes no banco
    static public ArrayList listarPostit() throws  Exception{
        Repositorio repositorio = new RepositorioMSSQL();
        return repositorio.listar();
    }

    // Altera um postIt especifico pelo nome
    public boolean alterarPostIt (PostIt data) throws Exception {
        Repositorio repositorio =  new RepositorioMSSQL();
        Boolean valida  = repositorio.alterar(data,enumEntidade.POSTIT);
        if(!valida)
        {
            GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "PostIt não alterado");
            return false;
        }
        else
        {
            return true;
        }
    }

    //Deleta um postIt especifico no banco
    public boolean deletarPosIt(Integer id, PostIt data) throws  Exception{return false;}
}
