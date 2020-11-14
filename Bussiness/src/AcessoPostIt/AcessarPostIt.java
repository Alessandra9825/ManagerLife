package AcessoPostIt;

import Auditoria.GerenciadorAuditoria;
import Enums.enumEntidade;
import Repositorio.MSSQL.RepositorioMSSQL;
import Repositorio.Repositorio;
import vos.PostIt;

public class AcessarPostIt {
    private final String meth = "PostIt - ";

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

}
