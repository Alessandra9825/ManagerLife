package Basis;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DAO <E extends Entidade> {
    protected Class<E> entityClass;

    public DAO(Class<E> entityClass){
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.entityClass = entityClass;
    }

    public abstract E seleciona(int id);
    public abstract E localiza(String codigo) throws SQLException;
    public abstract ArrayList<E> lista() throws SQLException;

    protected E getInstanceOfE(){
        try
        {
            return entityClass.newInstance();
        }
        catch (IllegalAccessException | InstantiationException e){
            throw new RuntimeException(e);
        }
    }

    public abstract boolean salvar(Entidade entidade) throws SQLException;
}
