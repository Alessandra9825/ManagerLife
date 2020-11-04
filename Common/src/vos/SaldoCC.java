package vos;

import Annotations.CampoNoBanco;
import Basis.Entidade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

public class SaldoCC extends Entidade {

    @CampoNoBanco(nome = "data",chave =  false)
    private Date data;

    @CampoNoBanco(nome="valor",chave = false)
    private double valor;

    @CampoNoBanco(nome="descricao",chave=false)
    private String descricao;

    @CampoNoBanco(nome = "contaCorrente_id", chave = false)
    private int CCId;

    @CampoNoBanco(nome="mensal", chave=false)
    private boolean mensal;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        BigDecimal bd = new BigDecimal(valor).setScale(2);
        this.valor = bd.doubleValue();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCCId() {
        return CCId;
    }

    public void setCCId(int CCId) {
        this.CCId = CCId;
    }

    public boolean isMensal() {
        return mensal;
    }

    public void setMensal(boolean mensal) {
        this.mensal = mensal;
    }
}
