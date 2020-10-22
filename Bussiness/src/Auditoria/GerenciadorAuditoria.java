package Auditoria;

import java.time.Instant;
import java.time.ZoneId;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GerenciadorAuditoria {

    ConcurrentLinkedQueue<String> filaMensagens;
    private static GerenciadorAuditoria uniqueInstance;

    private GerenciadorAuditoria() {
        filaMensagens = new ConcurrentLinkedQueue<>();
    }

    public static synchronized GerenciadorAuditoria getInstancia() {
        if(uniqueInstance == null)
            uniqueInstance = new GerenciadorAuditoria();

        return uniqueInstance;
    }

    ThreadGestaoAuditoria thread;

    public void adicionaMsgAuditoria(String msgAuditoria) {
        filaMensagens.add(Instant.now().atZone(ZoneId.systemDefault()).toString() + " - " + msgAuditoria);
    }

    public String retiraMsgAuditoria() {
        String msg = filaMensagens.poll();
        return msg;
    }

    public void ativar(){
        if (thread == null){
            thread = new ThreadGestaoAuditoria();
            thread.start();
        }
    }

    public void desativar(){
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

    public int filaSize(){
        return filaMensagens.size();
    }
}
