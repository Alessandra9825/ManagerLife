package Enums;

public enum enumCalendarsType {

    ANIVERSARIO("Aniversário"),
    REUNIAO("Reunião"),
    FOLGA("Folga"),
    LAZER("Lazer"),
    GOOGLE("Google"),
    ERROR("Error - Calendar not Found");


    private String descricao;

    private enumCalendarsType(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}