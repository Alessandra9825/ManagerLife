package Validacao;

import Auditoria.GerenciadorAuditoria;
import Basis.Entidade;
import vos.PostIt;
import java.util.ArrayList;
import java.util.Arrays;


public class ValidaPostIt <E extends Entidade> extends Validacao  {
    private final String meth = "ValidaPostIt - ";

    public ValidaPostIt() {
        super(PostIt.class);
    }

    @Override
    public ArrayList<String> ValidaDados(Entidade entidade) {
        ArrayList<String> erros = new ArrayList<>();
        PostIt post = (PostIt)entidade;
        try{
            GerenciadorAuditoria.getInstancia().ativar();

            if(isNullorEmpty(post.getNome())){
                erros.add("Informe o nome do Post-It!");
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Nome vazio.\n");
            }

            if(isNullorEmpty(post.getDescricao())){
                erros.add("Informe pelo menos uma descrição minima!");
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Descrição vazio.\n");
            }

            if(isNullorEmpty(post.getSituacao())){
                erros.add("Informe uma situação inicial!");
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Situação vazia.\n");
            }

            if(isNullorEmpty(post.getTempo())){
                erros.add("Informe um tempo valido!");
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Tempo inválida.\n");
            }
        }
        catch (Exception e){
            GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + " - " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }

        return erros;
    }
}
