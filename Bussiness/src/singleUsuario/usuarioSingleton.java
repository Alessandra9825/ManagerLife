package singleUsuario;

public class usuarioSingleton {

    public static usuarioSingleton _instance;

    public static usuarioSingleton Instancia() {
        if (_instance == null) {
            _instance = new usuarioSingleton();
        }
        return _instance;
    }

    static public int idUsuario;
    static public int getIdUsuario(){
        return  idUsuario;
    }
}
