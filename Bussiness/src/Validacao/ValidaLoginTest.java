package Validacao;

import Basis.Entidade;
import org.junit.jupiter.api.Test;
import vos.Usuario;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ValidaLoginTest {

    @Test
    void validaDados() {
        Usuario user = new Usuario();
        user.setEmail("lucas@russo");
        user.setSenha("JvUv60871mYxvI");

        ValidaLogin valida = new ValidaLogin();
        assertTrue(valida.ValidaDados((user)).isEmpty());
    }
}