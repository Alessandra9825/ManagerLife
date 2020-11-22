package Validacao;

import Auditoria.GerenciadorAuditoria;
import Basis.Entidade;
import vos.Gastos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ValidaGasto <E extends Entidade> extends Validacao {
    private final String meth = "ValidaGasto - ";

    public ValidaGasto() {super(Gastos.class);}

    @Override
    public ArrayList<String> ValidaDados(Entidade entidade) {
        ArrayList<String> erros = new ArrayList<>();
        Gastos gasto = (Gastos)entidade;
        try{
            GerenciadorAuditoria.getInstancia().ativar();

            if(gasto.getValor() <= 0){
                erros.add("Insira um valor positivo! ");
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Valor zerado ou negativo. \n");

            }

            if(isNullorEmpty(gasto.getData().toString())) {
                erros.add("Data inválida!");
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Data de gasto vazia. \n");
            }

            if(isNullorEmpty(gasto.getDescricao())){
                erros.add("Insira uma descrição válida!");
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Descrição vazia. \n");
            }
        }
        catch (Exception e) {
            GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + " - " + e.getMessage()+ "\n" + Arrays.toString(e.getStackTrace()));
        }
        return erros;
    }
}
