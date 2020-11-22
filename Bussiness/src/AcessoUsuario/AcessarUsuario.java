package AcessoUsuario;

import Auditoria.GerenciadorAuditoria;
import Enums.enumEntidade;
import Repositorio.MSSQL.RepositorioMSSQL;
import Repositorio.Repositorio;
import vos.PostIt;
import vos.Usuario;

public class AcessarUsuario {
    private final String meth = "Usuario - ";

    public boolean salvarUsuario(Usuario data) throws Exception {
        Repositorio repositorio = new RepositorioMSSQL();
        Boolean valida  = repositorio.salvar(data, enumEntidade.USUARIO);
        if(!valida)
        {
            GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Usuario não cadastrado");
            return false;
        }
        else
        {
            return true;
        }
    }

}
