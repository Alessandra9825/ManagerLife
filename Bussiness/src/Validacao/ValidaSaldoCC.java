package Validacao;

import Auditoria.GerenciadorAuditoria;
import Basis.Entidade;
import vos.SaldoCC;
import vos.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ValidaSaldoCC <E extends Entidade> extends Validacao{
    private final String meth = "ValidaSaldoCC - ";

    public ValidaSaldoCC() {
        super(SaldoCC.class);
    }

    @Override
    public ArrayList ValidaDados(Entidade entidade){
        ArrayList<String> erros = new ArrayList<>();
        SaldoCC saldo = (SaldoCC)entidade;
        try{
            GerenciadorAuditoria.getInstancia().ativar();

            if(isNullorEmpty(saldo.getDescricao())){
                erros.add("Informe o descrição do saldo!");
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Descrição vazia.\n");
            }

            if(saldo.getValor() <= 0){
                erros.add("Informe um valor positivo!");
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Valor invalido.\n");
            }
            Date agora = new Date(System.currentTimeMillis());
            if(saldo.getData().before(agora)){
                erros.add("Informe uma data presente ou passada!");
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Data invalida.\n");
            }
        }
        catch (Exception e){
            GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + " - " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }

        return erros;
    }
}