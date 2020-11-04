package MSSQL;

import Basis.Entidade;
import Basis.MSSQLDAO;
import com.sun.org.apache.xerces.internal.impl.dv.dtd.ENTITYDatatypeValidator;
import vos.Gastos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GastosMSSQL <E extends Entidade> extends MSSQLDAO {
    public GastosMSSQL() {
        super(Gastos.class);
        setTabela("Gastos");
    }

    @Override
    protected Entidade preencheEntidade(ResultSet rs) {
        Gastos entidade = new Gastos();
        try{
            entidade.setId(rs.getInt("id"));
            entidade.setTipo(rs.getString("tipo"));
            entidade.setValor(rs.getDouble("Valor"));
            entidade.setData(rs.getDate("data"));
            entidade.setDescricao(rs.getString("descricao"));
            entidade.setFrequencia(rs.getString("frequencia"));
        }
        catch (SQLException ex){
            Logger.getLogger(GastosMSSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)entidade;
    }

    public Entidade inserirGastos(Entidade entidade) throws SQLException {
        Gastos gastos = (Gastos)entidade;
        try(Connection conexao = getConnection()){
            String insertSQL = "insert into Gasto(tipo , Valor, data, descricao, frequencia) " +
                               "values (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = getStatement(insertSQL, conexao)){
                statement.setString(1, gastos.getTipo());
                statement.setDouble(2,gastos.getValor());
                statement.setDate(3, gastos.getData());
                statement.setString(4, gastos.getDescricao());
                statement.setString(5, gastos.getFrequencia());

                statement.executeUpdate();
            }
        }
        return gastos;
    }
}
