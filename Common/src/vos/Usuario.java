package vos;

import Annotations.CampoNoBanco;
import Basis.Entidade;
import javafx.scene.image.Image;

import java.util.Date;

public class Usuario extends Entidade {

    @CampoNoBanco(nome = "nome", chave = false)
    private String nome;

    @CampoNoBanco(nome = "cel", chave = false)
    private String cel;

    @CampoNoBanco(nome = "email", chave = true)
    private String email;

    @CampoNoBanco(nome = "senha", chave = false)
    private String senha;

    @CampoNoBanco(nome = "dataNascimento", chave = false)
    private Date dataNascimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
