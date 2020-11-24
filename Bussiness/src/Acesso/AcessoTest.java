package Acesso;

import org.testng.annotations.Test;
import vos.Usuario;

import static org.junit.jupiter.api.Assertions.*;

public class AcessoTest {
    @Test
    public void validaUsuario() {
        Usuario user = new Usuario();
        user.setEmail("lucas@russo");
        user.setSenha("JvUv60871mYxvI");

        Acesso acess = new Acesso();
        assertTrue(acess.validaUsuario(user));
    }
}