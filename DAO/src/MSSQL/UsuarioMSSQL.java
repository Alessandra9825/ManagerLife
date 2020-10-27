package MSSQL;

import Basis.Entidade;
import Basis.MSSQLDAO;
import javafx.scene.image.Image;
import vos.Usuario;

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
            //entidade.setId(rs.getInt("id"));
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
}
