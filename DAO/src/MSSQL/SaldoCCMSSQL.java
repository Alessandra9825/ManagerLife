package MSSQL;

import Annotations.CampoNoBanco;
import Basis.Entidade;
import Basis.MSSQLDAO;
import javafx.scene.image.Image;
import vos.SaldoCC;
import vos.Usuario;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaldoCCMSSQL <E extends Entidade> extends MSSQLDAO {

    public SaldoCCMSSQL(){
        super(SaldoCC.class);
        setTabela("HistoricoCorrente");
    }

    @Override
    public SaldoCC preencheEntidade(ResultSet rs) {
        SaldoCC saldo = new SaldoCC();
        try{
            saldo.setId(rs.getInt("id"));
            saldo.setValor(rs.getDouble("valor"));
            saldo.setData(rs.getDate("data"));
            saldo.setDescricao(rs.getString("descricao"));
            saldo.setCCId(rs.getInt("contaCorrente_id"));
            saldo.setMensal(rs.getBoolean("mensal"));
        } catch (SQLException ex) {
            Logger.getLogger(SaldoCCMSSQL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return saldo;
    }

    @Override
    protected String getInsertCommand(Entidade entidade) {
        return "insert into HistoricoCorrente (valor, [data], descricao, contaCorrente_id, mensal) values (?,?,?,?,?)";
    }

    @Override
    protected PreparedStatement getInsertStatement(Entidade entidade, PreparedStatement stmt) throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SaldoCC saldo = (SaldoCC) entidade;
        stmt.setDouble(1,saldo.getValor());
        stmt.setString(2,formatter.format(saldo.getData()));
        stmt.setString(3,saldo.getDescricao());
        stmt.setInt(4,saldo.getCCId());
        stmt.setBoolean(5,saldo.isMensal());
        return stmt;
    }

    public ArrayList localizaTodasTransicoes(int codCC) throws SQLException {
        ArrayList<SaldoCC> listaSaldo = new ArrayList();
        try (Connection conexao = getConnection()) {
            String SQL = getLocalizaTodasCommand();
            try (PreparedStatement stmt = getStatement(SQL, conexao)) {
                stmt.setInt(1,codCC);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()){
                        SaldoCC saldo = preencheEntidade(rs);
                        listaSaldo.add(saldo);
                    }
                }
            }
        }
        return listaSaldo;
    }

    public ArrayList localizaUltima30Transicoes(int codCC) throws SQLException{
        ArrayList<SaldoCC> listaSaldo = new ArrayList();
        try (Connection conexao = getConnection()) {
            String SQL = getLocalizaUltimas30Command();
            try (PreparedStatement stmt = getStatement(SQL, conexao)) {
                stmt.setInt(1,codCC);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()){
                        SaldoCC saldo = preencheEntidade(rs);
                        listaSaldo.add(saldo);
                    }
                }
            }
        }
        return listaSaldo;
    }

    protected String getLocalizaTodasCommand()
    {
        return "select * from HistoricoCorrente where contaCorrente_id = ? order by [data] desc";
    }

    protected String getLocalizaUltimas30Command()
    {
        return "select sum(valor) as valor, [data] from HistoricoCorrente where [data] >= getdate() - 30 and [data] <= GETDATE() group by [data] order by [data]";
    }

    @Override
    protected String getLocalizaCommand(){
        String campos = "";
        String chave = "";
        for(Field campo : entityClass.getDeclaredFields()) {
            if (campo.isAnnotationPresent(CampoNoBanco.class)) {
                CampoNoBanco anotacao = campo.getAnnotation(CampoNoBanco.class);
                if (anotacao.chave())
                    chave = anotacao.nome();
                campos += anotacao.nome() + ",";
            }
        }
        if(campos.length() > 0)
            campos = campos.substring(0, campos.length()-1);
        return "select " + campos + " from " + tabela + " where " + chave + " = ?";
        //return "select * from " + tabela;
    }
}
