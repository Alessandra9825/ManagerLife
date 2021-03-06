package Basis;

import Annotations.CampoNoBanco;
import singleUsuario.usuarioSingleton;
import vos.PostIt;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public abstract class MSSQLDAO <E extends Entidade> extends DAO {
    private final String stringConexao = "jdbc:sqlserver://SQL5097.site4now.net;databaseName= DB_A69CF7_ManagerLife;";
    private final String usuario = "DB_A69CF7_ManagerLife_admin";
    private final String senha = "managerLife1234";
    protected String tabela;

    public MSSQLDAO(Class entityClass) {
        super(entityClass);
    }

    protected void setTabela(String tabela){
        this.tabela = tabela;
    }

    protected E preencheEntidade(ResultSet rs) {
        //To change body of generated methods, choose Tools | Templates.
        throw new UnsupportedOperationException("Implementar na classe filha.");
    }

    protected Connection getConnection(){
        Connection conexao = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexao = DriverManager.getConnection(stringConexao, usuario, senha);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return conexao;
    }

    protected PreparedStatement getStatement(String SQL, Connection conexao){
        PreparedStatement stmt = null;
        try{
            stmt = conexao.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return stmt;
    }

    @Override
    public Entidade seleciona(int id) {
        return null;
    }

    @Override
    public Entidade localiza(String codigo) throws SQLException {
        E entidade = null;
        try(Connection conexao = getConnection()){
            String SQL = getLocalizaCommand();
            try(PreparedStatement statement = getStatement(SQL, conexao)){
                statement.setString(1, codigo);
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
        return "select id," + campos + " from " + tabela + " where " + chave + " = ?";
        //return "select * from " + tabela;
    }

    @Override
    public ArrayList lista() throws SQLException {
        ArrayList<E> entidades = new ArrayList();
        try (Connection conexao = getConnection()) {
            String SQL = getListaCommand();
            try (PreparedStatement stmt = getStatement(SQL, conexao)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()){
                        E entidade = preencheEntidade(rs);
                        entidades.add(entidade);
                    }
                }
            }
        }
        return entidades;
    }
    @Override
    public  int busca () throws  SQLException {
        ArrayList<E> entidades = new ArrayList();
        try(Connection conexao = getConnection())
        {
            String SQL = "Select * from Post_It where situacaoPostit_id = 0 and usuario_id = " + usuarioSingleton.idUsuario;
            try(PreparedStatement stmt = getStatement(SQL, conexao))
            {
                try (ResultSet rs = stmt.executeQuery())
                {
                    while (rs.next())
                    {
                        E entidade = preencheEntidade(rs);
                        entidades.add(entidade);
                    }
                }
            }
        }
        return entidades.size();
    }

    protected String getListaCommand() {
        return "select * from " + tabela;
    }

    @Override
    public boolean salvar(Entidade entidade) throws SQLException {
        try(Connection conexao = getConnection()){
            String SQL = getInsertCommand(entidade);
            try(PreparedStatement stmt = getInsertStatement(entidade, conexao.prepareStatement(SQL))){
                return stmt.executeUpdate() >= 1;
            }
        }
    }

    protected String getInsertCommand(Entidade entidade) {
        //To change body of generated methods, choose Tools | Templates.
        throw new UnsupportedOperationException("Implementar na classe filha.");
    }

    protected String getUpdateCommand(Entidade entidade) {
        //To change body of generated methods, choose Tools | Templates.
        throw new UnsupportedOperationException("Implementar na classe filha.");
    }

    protected PreparedStatement getInsertStatement(Entidade entidade, PreparedStatement stmt) throws SQLException {
        //To change body of generated methods, choose Tools | Templates.
        throw new UnsupportedOperationException("Implementar na classe filha.");
    }

    public ArrayList listarExpecifico() throws SQLException {
        ArrayList<E> entidades = new ArrayList();
        try (Connection conexao = getConnection()) {
            String SQL = "select * from "+tabela+" where usuario_id = "+ usuarioSingleton.idUsuario;
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()){
                        E entidade = preencheEntidade(rs);
                        entidades.add(entidade);
                    }
                }
            }
        }
        return entidades;
    }

    @Override
    public  boolean alterar(Entidade entidade) throws SQLException{
        try(Connection conexao = getConnection()){
            String SQL = getUpdateCommand(entidade);
            try(PreparedStatement stmt = getUpdateStatement(entidade, conexao.prepareStatement(SQL))){

                if (stmt.executeUpdate() == 1)
                {
                    return true;
                }
                else
                {
                    return false;

                }
            }
        }
    }

    protected  PreparedStatement getUpdateStatement(Entidade entidade, PreparedStatement prepareStatement) throws SQLException{
        throw new UnsupportedOperationException("Implementar na classe filha.");
    };


}
