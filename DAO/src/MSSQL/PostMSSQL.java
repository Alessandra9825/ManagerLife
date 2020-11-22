package MSSQL;
import Annotations.CampoNoBanco;
import Basis.Entidade;
import Basis.MSSQLDAO;
import singleUsuario.usuarioSingleton;
import vos.PostIt;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostMSSQL <E extends Entidade> extends MSSQLDAO {
    public PostMSSQL() {
        super(PostIt.class);
        setTabela("Post_it");
    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        PostIt entidade = new PostIt();
        try{
            entidade.setId(rs.getInt("id"));
            entidade.setNome(rs.getString("nome"));
            entidade.setSituacao(rs.getInt("situacaoPostit_id"));
            entidade.setDescricao(rs.getString("descricao"));
            entidade.setTempo(rs.getInt("tempoEstimado"));

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioMSSQL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (E)entidade;
    }
    @Override
    protected String getInsertCommand(Entidade entidade) {
        return "INSERT INTO Post_it (usuario_id,nome,descricao,situacaoPostit_id,tempoEstimado) VALUES(?,?,?,?,?) ";
    }

    @Override
    protected PreparedStatement getInsertStatement(Entidade entidade, PreparedStatement stmt) throws SQLException
    {
        PostIt post = (PostIt) entidade;
        stmt.setInt(1,usuarioSingleton.getIdUsuario());
        stmt.setString(2, post.getNome());
        stmt.setString(3, post.getDescricao());
        stmt.setInt(4,post.getSituacao());
        stmt.setInt(5,post.getTempo());
        return stmt;
    }

    @Override
    protected String getUpdateCommand(Entidade entidade) {
        PostIt post = (PostIt) entidade;
        return "UPDATE tabela SET nome = ?,descricao = ?,situacaoPostit_id = ?,tempoEstimado = ?" +
                " FROM Post_it WHERE id =" + post.getId();
    }
    @Override
    protected PreparedStatement getUpdateStatement(Entidade entidade, PreparedStatement stmt) throws SQLException
    {
        PostIt post = (PostIt) entidade;
        stmt.setString(1, post.getNome());
        stmt.setString(2, post.getDescricao());
        stmt.setInt(3,post.getSituacao());
        stmt.setInt(4,post.getTempo());
        return stmt;
    }


}

