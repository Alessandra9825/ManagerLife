package Validacao;

import Basis.Entidade;
import vos.Usuario;
import java.util.*;

public class ValidaUsuario <E extends Entidade> extends Validacao {

    public ValidaUsuario() {
        super(Usuario.class);
    }

    private boolean isNullorEmpty(String param){
        return param == null || param.isEmpty();
    }

    @Override
    public void ValidaDados(Entidade entidade) {
        ArrayList<String> erros = new ArrayList<String>();
        Usuario user = (Usuario)entidade;

        if(isNullorEmpty(user.getNome())){
            erros.add("Informe o nome do usuário!");
        }

        if(isNullorEmpty(user.getEmail())){
            erros.add("Informe um e-mail!");
        }

        if(isNullorEmpty(user.getSenha())){
            erros.add("Informe uma senha!");
        }

        if(user.getDataNascimento().compareTo(new Date(System.currentTimeMillis())) > 0){
            erros.add("Data de Nascimento inválida!");
        }
    }
}
