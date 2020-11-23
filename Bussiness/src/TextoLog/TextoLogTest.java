package TextoLog;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextoLogTest {

    @Test
    public void salvar() {
        TextoLog log = new TextoLog();
        String logTest = "Um log de teste";

        assertTrue(log.salvar(logTest));
    }
}