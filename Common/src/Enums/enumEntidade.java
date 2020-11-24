package Enums;

import vos.SenhasAntigas;

public enum enumEntidade {

    USUARIO("usuario"),
    SENHA_ANTIGA("senhaAntiga"),
    Evento("evento"),
    POSTIT("postIt");

    private String descricao;

    enumEntidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
