package MSSQL;
import Basis.Entidade;
import Basis.MSSQLDAO;
import vos.PostIt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            entidade.setSituacao(rs.getInt("situacao"));
            entidade.setDescricao(rs.getString("descricao"));
            entidade.setTempo(rs.getString("tempo"));

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioMSSQL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (E)entidade;
    }
    @Override
    protected String getInsertCommand(Entidade entidade) {
        return "INSERT INTO Post_it (usuario_id,nome,descricao,situacaoPostit_id,tempoEstimado) VALUES('1',?,?,?,?) ";
    }

    @Override
    protected PreparedStatement getInsertStatement(Entidade entidade, PreparedStatement stmt) throws SQLException
    {
        PostIt post = (PostIt) entidade;
        stmt.setString(1, post.getNome());
        stmt.setString(2, post.getDescricao());
        stmt.setInt(3,post.getSituacao());
        stmt.setString(4,post.getTempo());
        return stmt;
    }
    /*public Entidade buscaPost(String nome) throws SQLException
    {
        Entidade entidade = null;
        try(Connection conexao = getConnection()){
            String SQL = "select * from " + tabela + " where nome = ?";
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
    }*/

}

