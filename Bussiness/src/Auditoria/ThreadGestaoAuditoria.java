package Auditoria;

public class ThreadGestaoAuditoria extends Thread {
    private boolean status;

    @Override
    public void run() {
        setStatus(true);


    }

    public void setStatus(boolean value){
        status = value;
    }
}
