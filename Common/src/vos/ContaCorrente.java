package vos;

import Annotations.CampoNoBanco;
import Basis.Entidade;

public class ContaCorrente extends Entidade{

    @CampoNoBanco(nome="valorAtual",chave=false)
    private double valor;

    @CampoNoBanco(nome="usuario_id",chave=false)
    private int usuarioId;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int financasId) {
        this.usuarioId = financasId;
    }
}
