package vos;

import Annotations.CampoNoBanco;
import Basis.Entidade;

import java.util.Date;

public class PostIt extends Entidade {
    @CampoNoBanco(nome = "nome", chave = false)
    private String nome;

    @CampoNoBanco(nome = "tempoEstimado", chave = false)
    private Integer tempo;

    @CampoNoBanco(nome = "descricao", chave = false)
    private String descricao;

    @CampoNoBanco(nome = "situacaoPostit_id", chave = false)
    private Integer situacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }
}
