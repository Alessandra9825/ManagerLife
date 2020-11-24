package Acesso;

import Auditoria.GerenciadorAuditoria;
import Enums.enumEntidade;
import Repositorio.MSSQL.RepositorioMSSQL;
import Repositorio.Repositorio;
import Validacao.ValidaLogin;
import singleUsuario.usuarioSingleton;
import vos.Usuario;

public class Acesso {
    private final String meth = "Acesso - ";

    private boolean validaSenha(Usuario userRepo, Usuario user){
        return userRepo.getSenha().equals(user.getSenha());
    }

    public boolean validaUsuario(Usuario user) throws Exception {
        Repositorio repositorio = new RepositorioMSSQL();
        Usuario usuario = null;

        try {
            usuario = (Usuario)repositorio.localiza(user.getEmail(), enumEntidade.USUARIO);
        } catch (Exception e) {
            GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Error - Probelmas de Conexao");
        }

        if(usuario != null && validaSenha(usuario, user))
            usuarioSingleton.Instancia();
            usuarioSingleton.idUsuario = usuario.getId();
            return true;
        else
            GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Usuário não cadastrado");

        return false;
    }
}
