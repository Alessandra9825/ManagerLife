package Utilitarios;

import Auditoria.GerenciadorAuditoria;

public class Utilitarios {

    private static Utilitarios utilInstance;

    public Utilitarios(){
    }

    public static synchronized Utilitarios getInstancia() {
        if(utilInstance == null)
            utilInstance = new Utilitarios();

        return utilInstance;
    }

    public int idUser = 0;
    public int idConta = 0;

    public boolean isNullorEmpty(String str){
        return str.trim().length() == 0 || str.isEmpty();
    }
}
