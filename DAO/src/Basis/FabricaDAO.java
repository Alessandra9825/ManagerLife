package Basis;

import Enums.enumEntidade;
import MSSQL.EventoMSSQL;
import MSSQL.PostMSSQL;
import MSSQL.SenhasAntigasMSSQL;
import MSSQL.UsuarioMSSQL;

public class FabricaDAO {

    public static DAO fabrica(enumEntidade entidade) throws Exception {
        DAO retorno;

        try
        {
            switch (entidade){
                case USUARIO:
                    retorno = new UsuarioMSSQL();
                    break;
                case SENHA_ANTIGA:
                    retorno = new SenhasAntigasMSSQL();
                    break;
                case POSTIT:
                    retorno = new PostMSSQL();
                    break;
                case Evento:
                    retorno =  new EventoMSSQL();
                    break;
                default:
                    retorno = null;
            }
        }
        catch (Exception err)
        {
            throw new Exception(err.getMessage());
        }

        return retorno;
    }
}
