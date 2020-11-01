package Auditoria;

import Validacao.TextoLog;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadGestaoAuditoria extends Thread {
    private boolean status;

    @Override
    public void run() {
        setStatus(true);
        while (status || GerenciadorAuditoria.getInstancia().filaSize() > 0) {
            try {
                String msg = GerenciadorAuditoria.getInstancia().retiraMsgAuditoria();
                if (msg != null) {
                    gravaMensagemAuditoria(msg);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadGestaoAuditoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setStatus(boolean value) {
        status = value;
    }

    private void gravaMensagemAuditoria(String msg) throws InterruptedException {
        TextoLog registra = new TextoLog();
        registra.salvar(msg);
    }
}
