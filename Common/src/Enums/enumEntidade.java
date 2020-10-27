package Enums;

public enum enumEntidade {

    USUARIO("usuario");

    private String descricao;

    enumEntidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
