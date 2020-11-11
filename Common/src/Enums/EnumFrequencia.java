package Enums;

public enum EnumFrequencia {
    GASTO_UNICO("Gasto único"),
    MENSAL("Mensal");

    private String descricao;

    EnumFrequencia(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
