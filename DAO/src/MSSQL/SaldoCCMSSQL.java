package MSSQL;

import Basis.Entidade;
import Basis.MSSQLDAO;
import javafx.scene.image.Image;
import vos.SaldoCC;
import vos.Usuario;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaldoCCMSSQL <E extends Entidade> extends MSSQLDAO {

    public SaldoCCMSSQL(){
        super(SaldoCC.class);
        setTabela("HistoricoCorrente");
    }

    @Override
    public Entidade preencheEntidade(ResultSet rs) {
        SaldoCC entidade = new SaldoCC();
        try{
            entidade.setId(rs.getInt("id"));
            entidade.setValor(rs.getDouble("valor"));
            entidade.setData(rs.getDate("data"));
            entidade.setDescricao(rs.getString("descricao"));
            entidade.setCCId(rs.getInt("contaCorrente_id"));
            entidade.setMensal(rs.getBoolean("mensal"));
        } catch (SQLException ex) {
            Logger.getLogger(SaldoCCMSSQL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (E)entidade;
    }

    @Override
    protected String getInsertCommand(Entidade entidade) {
        return "insert into HistoricoCorrente values (?,?,?,?,?,?)";
    }

    @Override
    protected PreparedStatement getInsertStatement(Entidade entidade, PreparedStatement stmt) throws SQLException {
        SaldoCC saldo = (SaldoCC) entidade;
        stmt.setInt(1,saldo.getId());
        stmt.setDouble(2,saldo.getValor());
        stmt.setDate(3,(Date) saldo.getData());
        stmt.setString(4,saldo.getDescricao());
        stmt.setInt(5,saldo.getCCId());
        stmt.setBoolean(6,saldo.isMensal());
        return stmt;
    }
}
