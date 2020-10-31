package vos;

import Annotations.CampoNoBanco;
import Basis.Entidade;

public class SenhasAntigas extends Entidade {

    @CampoNoBanco(nome = "usuario_id", chave = false)
    private int usuario_id;

    @CampoNoBanco(nome = "senha", chave = true)
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }
}
