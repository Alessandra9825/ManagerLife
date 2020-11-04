package vos;

import Annotations.CampoNoBanco;
import Basis.Entidade;
import java.util.Date;

public class Gastos extends Entidade {
    @CampoNoBanco(nome = "tipo", chave = false)
    private String tipo;

    @CampoNoBanco(nome = "Valor", chave = false)
    private double valor;

    @CampoNoBanco(nome = "data", chave = false)
    private Date data;

    @CampoNoBanco(nome = "descricao", chave = false)
    private String descricao;

    @CampoNoBanco(nome = "frequencia", chave = false)
    private String frequencia;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public java.sql.Date getData() {
        return (java.sql.Date) data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }
}
