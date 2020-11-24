package Acesso;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NovaSenhaTest {


    @Test
    public void newPassword() {
        String email = "lucas@";
        //quando informado é recadastro, se nao esqueceu a senha
        String senha = "testeUnitario";

        NovaSenha novaSenha = new NovaSenha();

        //Retorna uma String com todos os erros
        assertEquals("", novaSenha.newPassword(email, senha));
    }
}