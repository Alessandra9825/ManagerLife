package Validacao;

import org.junit.jupiter.api.Test;
import vos.Usuario;

import java.sql.Date;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class ValidaUsuarioTest {

    @Test
    void validaDados() {
        Usuario user = new Usuario();
        user.setEmail("a");
        user.setSenha("a");
        user.setCel("a");
        user.setNome("a");
        user.setDataNascimento(Date.from(Instant.now()));

        ValidaUsuario valida = new ValidaUsuario();
        assertTrue(valida.ValidaDados(user).isEmpty());

    }
}