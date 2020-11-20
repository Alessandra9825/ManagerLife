package MSSQL;

import Basis.Entidade;
import Basis.MSSQLDAO;
import vos.ContaCorrente;
import vos.SaldoCC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContaCorrenteMSSQL <E extends Entidade> extends MSSQLDAO {

    public ContaCorrenteMSSQL(){
        super(ContaCorrente.class);
        setTabela("ContaCorrente");
    }

    @Override
    protected ContaCorrente preencheEntidade(ResultSet rs) {
        ContaCorrente conta = new ContaCorrente();
        try{
            conta.setId(rs.getInt("id"));
            conta.setValor(rs.getDouble("valorAtual"));
            conta.setFinancasId(rs.getInt("financas_id"));
        } catch (SQLException ex) {
            Logger.getLogger(SaldoCCMSSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conta;
    }

    public ContaCorrente localiza(int codigo) throws SQLException {
        ContaCorrente conta = null;
        try(Connection conexao = getConnection()){
            String SQL = getLocalizaCommand();
            try(PreparedStatement statement = getStatement(SQL, conexao)){
                statement.setInt(1, codigo);
                try(ResultSet rs = statement.executeQuery()){
                    if(rs.next())
                    {
                        conta = preencheEntidade(rs);
                    }
                }
            }
        }
        return conta;
    }

    @Override
    protected String getLocalizaCommand()
    {
        return "select * from ContaCorrente where id = ?";
    }
}
