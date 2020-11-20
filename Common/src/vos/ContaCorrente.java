package vos;

import Annotations.CampoNoBanco;
import Basis.Entidade;

public class ContaCorrente extends Entidade{

    @CampoNoBanco(nome="valorAtual",chave=false)
    private double valor;

    @CampoNoBanco(nome="financas_id",chave=false)
    private int financasId;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getFinancasId() {
        return financasId;
    }

    public void setFinancasId(int financasId) {
        this.financasId = financasId;
    }
}
