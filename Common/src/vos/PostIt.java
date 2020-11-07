package vos;

import Annotations.CampoNoBanco;
import Basis.Entidade;

import java.util.Date;

public class PostIt extends Entidade {
    @CampoNoBanco(nome = "nome", chave = false)
    private String nome;

    @CampoNoBanco(nome = "tempo", chave = false)
    private String tempo;

    @CampoNoBanco(nome = "descricao", chave = false)
    private String descricao;

    @CampoNoBanco(nome = "situacao", chave = false)
    private String situacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
