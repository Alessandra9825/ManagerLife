package Basis;

import Enums.enumEntidade;
import MSSQL.SenhasAntigasMSSQL;
import MSSQL.UsuarioMSSQL;

public class FabricaDAO {

    public static DAO fabrica(enumEntidade entidade){
        DAO retorno;
        switch (entidade){
            case USUARIO:
                retorno = new UsuarioMSSQL();
                break;
            case SENHA_ANTIGA:
                retorno = new SenhasAntigasMSSQL();
            default:
                retorno = null;
        }

        return retorno;
    }
}
