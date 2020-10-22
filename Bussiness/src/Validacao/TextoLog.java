package Validacao;

import Validacao.ValidaLogin;
import vos.Usuario;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextoLog {
    public final static String diretorio = System.getProperty("user.dir")+"\\log.txt";

    private FileWriter validaTxt () throws IOException {
        FileWriter arquivo;
        if (Files.exists(Paths.get(diretorio)))
        {
            arquivo = new FileWriter("log.txt",true);
        }
        else
        {
            arquivo = new FileWriter("log.txt");
        }
        return arquivo;
    }

    public boolean salvar(String log) {
        try
        {
            validaTxt();
            BufferedWriter bw = new BufferedWriter(validaTxt());
            bw.write(log);

            bw.close();
            validaTxt().close();
            return true;
        }
        catch (IOException err)
        {
            Logger.getLogger(ValidaLogin.class.getName()).log(Level.SEVERE, null, err);
            return false;
        }
    }
}
