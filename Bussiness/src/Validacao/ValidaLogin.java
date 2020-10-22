package Validacao;

import Auditoria.GerenciadorAuditoria;
import Basis.Entidade;
import vos.Usuario;

import java.util.ArrayList;

public class ValidaLogin <E extends Entidade> extends Validacao {
    private final String meth = "ValidaLogin - ";

    public ValidaLogin() {
        super(Usuario.class);
    }

    @Override
    public ArrayList ValidaDados(Entidade entidade) {
        ArrayList<String> erros = new ArrayList<>();
        Usuario user = (Usuario)entidade;
        try{
            GerenciadorAuditoria.getInstancia().ativar();

            if(isNullorEmpty(user.getEmail())){
                erros.add("Informe um e-mail!");
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Email vazio.\n");
            }

            if(isNullorEmpty(user.getSenha())){
                erros.add("Informe uma senha!");
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Senha vazio.\n");
            }
        }
        catch (Exception e){
            GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + " - " + e.getMessage() + "\n" + e.getStackTrace());
        }

        return erros;
    }
}
