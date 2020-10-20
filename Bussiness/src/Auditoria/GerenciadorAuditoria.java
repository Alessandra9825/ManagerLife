package Auditoria;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GerenciadorAuditoria {

    private static GerenciadorAuditoria instancia;

    private GerenciadorAuditoria() {
    }

    public static GerenciadorAuditoria getInstancia() {
        if(instancia == null)
            return new GerenciadorAuditoria();
        return instancia;
    }

    ThreadGestaoAuditoria thread;

    void ativar(){
        if (thread == null){
            thread = new ThreadGestaoAuditoria();
            thread.start();
        }
    }

    void desativar(){
        if (thread != null) {
            thread.setStatus(false);
            try {
                thread.join(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GerenciadorAuditoria.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (thread.isAlive())
                thread.interrupt();
        }
    }
}
