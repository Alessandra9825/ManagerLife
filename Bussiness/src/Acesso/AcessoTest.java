package Acesso;

import org.junit.jupiter.api.Test;
import vos.Usuario;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcessoTest {
    @Test
    public void validaUsuario() {
        Usuario user = new Usuario();
        user.setEmail("lucas@russo");
        user.setSenha("JvUv60871mYxvI");

        Acesso acess = new Acesso();
        try {
            assertTrue(acess.validaUsuario(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}