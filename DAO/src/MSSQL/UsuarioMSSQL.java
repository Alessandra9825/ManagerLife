package MSSQL;

import Basis.Entidade;
import Basis.MSSQLDAO;
import Enums.enumEntidade;
import Repositorio.MSSQL.RepositorioMSSQL;
import Repositorio.Repositorio;
import javafx.scene.image.Image;
import vos.SenhasAntigas;
import vos.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioMSSQL <E extends Entidade> extends MSSQLDAO {
    public UsuarioMSSQL() {
        super(Usuario.class);
        setTabela("Usuario");
    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        Usuario entidade = new Usuario();
        try{
            entidade.setId(rs.getInt("id"));
            entidade.setNome(rs.getString("nome"));
            entidade.setCel(rs.getString("cel"));
            entidade.setEmail(rs.getString("email"));
            entidade.setSenha(rs.getString("senha"));
            entidade.setFoto(rs.getBinaryStream("imagem") == null ? null : new Image(rs.getBinaryStream("imagem")));
            entidade.setDataNascimento(rs.getDate("dataNascimento"));
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioMSSQL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (E)entidade;
    }

    public Entidade buscaEmail(String email) throws SQLException {
        Entidade entidade = null;
        try(Connection conexao = getConnection()){
            String SQL = "select * from " + tabela + " where email = ?";
            try(PreparedStatement statement = getStatement(SQL, conexao)){
                statement.setString(1, email);
                try(ResultSet rs = statement.executeQuery()){
                    if(rs.first())
                    {
                        entidade = preencheEntidade(rs);
                    }
                }
            }
        }
        return entidade;
    }

    public String setNewPassword(Entidade entidade, String newPass) throws SQLException {
        Usuario user = (Usuario)entidade;
        SenhasAntigasMSSQL dao = new SenhasAntigasMSSQL();
        SenhasAntigas oldPass = (SenhasAntigas)dao.buscaOldPass(user.getId(), newPass);
        String erro = "";

        if(oldPass == null && user.getSenha() != newPass){
            try(Connection conexao = getConnection()){
                String saveOldPass_SQL = "INSERT INTO SenhasAntigas(usuario_id, senha) Values(?, ?)";
                String saveNewPass_SQL = "UPDATE " + tabela + " SET " +
                        "senha = ?, " +
                        "recuperarSenha = ? " +
                        "WHERE id = ?";

                try(PreparedStatement oldPassStatement = getStatement(saveOldPass_SQL, conexao);
                    PreparedStatement newPassStatement = getStatement(saveNewPass_SQL, conexao)) {
                    conexao.setAutoCommit(false);

                    //salva a antiga senha
                    oldPassStatement.setInt(1, user.getId());
                    oldPassStatement.setString(2, user.getSenha());
                    oldPassStatement.executeUpdate();

                    //atualiza o usuario
                    newPassStatement.setString(1, newPass);
                    newPassStatement.setBoolean(2, true);
                    newPassStatement.setInt(3, user.getId());
                    newPassStatement.executeUpdate();

                    conexao.commit();
                }
            }
        }
        else {
            erro = "Favor cadastrar uma senha diferente das anteriores!";
        }

        return erro;
    }
}
