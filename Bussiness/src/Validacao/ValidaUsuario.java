package Validacao;

import Auditoria.GerenciadorAuditoria;
import Basis.Entidade;
import vos.Usuario;
import java.util.*;

public class ValidaUsuario <E extends Entidade> extends Validacao {
    private final String meth = "ValidaUsuario - ";

    public ValidaUsuario() {
        super(Usuario.class);
    }

    @Override
    public ArrayList ValidaDados(Entidade entidade) {
        ArrayList<String> erros = new ArrayList<>();
        Usuario user = (Usuario)entidade;
        try{
            GerenciadorAuditoria.getInstancia().ativar();

            if(isNullorEmpty(user.getNome())){
                erros.add("Informe o nome do usuário!");
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Nome vazio.\n");
            }

            if(isNullorEmpty(user.getEmail())){
                erros.add("Informe um e-mail!");
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Email vazio.\n");
            }

            if(isNullorEmpty(user.getSenha())){
                erros.add("Informe uma senha!");
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Senha vazia.\n");
            }

            if(user.getDataNascimento() == null || user.getDataNascimento().compareTo(new Date(System.currentTimeMillis())) > 0){
                erros.add("Data de Nascimento inválida!");
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Data de nascimento inválida.\n");
            }
        }
        catch (Exception e){
            GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + " - " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }

        return erros;
    }
}
