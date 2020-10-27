package Basis;

import Enums.enumEntidade;
import MSSQL.UsuarioMSSQL;

public class FabricaDAO {

    public static DAO fabrica(enumEntidade entidade){
        DAO retorno;
        switch (entidade){
            case USUARIO:
                retorno = new UsuarioMSSQL();
                break;
            default:
                retorno = null;
        }

        return retorno;
    }
}
