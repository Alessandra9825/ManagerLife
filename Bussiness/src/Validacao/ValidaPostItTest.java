package Validacao;

import org.junit.jupiter.api.Test;
import vos.PostIt;

import static org.junit.jupiter.api.Assertions.*;

class ValidaPostItTest {

    @Test
    void validaDados() {
        PostIt postit = new PostIt();
        postit.setDescricao("Um test");
        postit.setNome("Test");
        postit.setTempo("10");
        postit.setSituacao(1);

        ValidaPostIt validaPostIt = new ValidaPostIt();
        assertTrue(validaPostIt.ValidaDados(postit).isEmpty());
    }
}