package MSSQL;

import Basis.Entidade;
import Basis.MSSQLDAO;
import javafx.scene.image.Image;
import vos.SenhasAntigas;
import vos.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SenhasAntigasMSSQL <E extends Entidade> extends MSSQLDAO {
    public SenhasAntigasMSSQL() {
        super(SenhasAntigas.class);
        setTabela("SenhasAntigas");
    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        SenhasAntigas entidade = new SenhasAntigas();
        try{
            entidade.setId(rs.getInt("id"));
            entidade.setUsuario_id(rs.getInt("usuario_id"));
            entidade.setSenha(rs.getString("senha"));
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioMSSQL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (E)entidade;
    }

    protected Entidade buscaOldPass(int usuario_id, String oldPass) throws SQLException {
        Entidade entidade = null;
        try(Connection conexao = getConnection()){
            String SQL = "SELECT * FROM " + tabela + " WHERE usuario_id = ? and senha = ?";
            try(PreparedStatement statement = getStatement(SQL, conexao)){
                statement.setInt(1, usuario_id);
                statement.setString(2, oldPass);
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

    @Override
    public boolean alterar(Entidade entidade) throws SQLException {
        return false;
    }
}
