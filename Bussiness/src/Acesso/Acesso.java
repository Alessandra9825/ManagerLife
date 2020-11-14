package Acesso;

import Auditoria.GerenciadorAuditoria;
import Enums.enumEntidade;
import Repositorio.MSSQL.RepositorioMSSQL;
import Repositorio.Repositorio;
import Validacao.ValidaLogin;
import vos.Usuario;

public class Acesso {
    private final String meth = "Acesso - ";

    private boolean validaSenha(Usuario userRepo, Usuario user){
        return userRepo.getSenha().equals(user.getSenha());
    }

    public boolean validaUsuario(Usuario user) throws Exception {
        Repositorio repositorio = new RepositorioMSSQL();
        Usuario usuario = (Usuario)repositorio.localiza(user.getEmail(), enumEntidade.USUARIO);
        if(usuario != null)
            return validaSenha(usuario, user);
        else
            GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Usuário não cadastrado");

        return false;
    }
}
