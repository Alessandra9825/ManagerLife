package singleUsuario;

import vos.Usuario;

public class usuarioSingleton {

    public static usuarioSingleton _instance;

    public static usuarioSingleton Instancia() {
        if (_instance == null) {
            _instance = new usuarioSingleton();
        }
        return _instance;
    }

    static public Usuario usuario;
    static public int idUsuario;
    static public int getIdUsuario(){
        return  idUsuario;
    }

}
