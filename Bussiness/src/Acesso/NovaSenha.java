package Acesso;

import Auditoria.GerenciadorAuditoria;
import MSSQL.UsuarioMSSQL;
import Utilitarios.Utilitarios;
import singleUsuario.usuarioSingleton;
import vos.Usuario;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NovaSenha {

    public String newPassword(String email, String senha){
        String erro = "";
        String meth = "newPassword - ";

        boolean emailEnviado = false;
        try{
            UsuarioMSSQL dao = new UsuarioMSSQL();
            Usuario user = (Usuario)dao.buscaEmail(email);
            if(user != null) {
                if(Utilitarios.getInstancia().isNullorEmpty(senha))
                    senha = generateRandomPassword();

                erro = dao.setNewPassword(user, senha);

                if(Utilitarios.getInstancia().isNullorEmpty(erro))
                    emailEnviado = sendEmail(email, senha);

                if(emailEnviado){
                    usuarioSingleton.usuario = (Usuario)dao.buscaEmail(email);
                    GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Senha cadastrada com sucesso!");
                }
                else {
                    erro = "Problemas ao enviar e-mail, tente novamente!";
                    GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Senha cadastrada com sucesso!");
                }
            }
            else{
                erro = "Usuário não encontrado!";
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Usuário não encontrado.");
            }
        } catch (SQLException e){
            erro = "Falha na conexão, tente novamente!";
            GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + e.getMessage() + "\t" + Arrays.toString(e.getStackTrace()));
        } catch(Exception e){
            erro = "Erro inesperado, tente novamente!";
            GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + e.getMessage() + "\t" + Arrays.toString(e.getStackTrace()));
        }

        return erro;
    }

    private String generateRandomPassword(){
        Stream<Character> pwdStream = Stream.concat(getRandomNumbers(5),
                Stream.concat(getRandomAlphabets(5, true), getRandomAlphabets(5, false)));
        List<Character> charList = pwdStream.collect(Collectors.toList());
        Collections.shuffle(charList);
        String password = charList.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return password;
    }

    private Stream<Character> getRandomNumbers(int count) {
        Random random = new Random();
        IntStream specialChars = random.ints(count, 48, 57);
        return specialChars.mapToObj(data -> (char) data);
    }

    private Stream<Character> getRandomAlphabets(int count, boolean UpperCase) {
        Random random = new Random();
        IntStream specialChars;
        if(UpperCase){
            specialChars = random.ints(count, 65, 90);
        }
        else{
            specialChars = random.ints(count, 97, 122);
        }
        return specialChars.mapToObj(data -> (char) data);
    }

    private boolean sendEmail(String email, String newPass){
        String meth = "sendEmail - ";
        final String user = "suporte.ManagerLife@gmail.com"; //change accordingly
        final String password = "managerLife1234"; //change accordingly
        boolean sendMsg = false;

        Properties props = new Properties();
        // Parâmetros de conexão com servidor Gmail
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(user, password);
                    }
                });

        //session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("suporte.ManagerLife@gmail.com"));

            //Remetente
            Address[] toUsers = InternetAddress.parse(email);

            message.setRecipients(Message.RecipientType.TO, toUsers);
            message.setSubject("Manager Life - Recuperação de senha!"); //Assunto
            message.setText(
                    "Olá.\n\n" +
                    "A sua solicitação de recuperação da conta no cadastro da Manager Life foi recebida.\n\n" +
                    "Nós da Manager Life geramos uma nova senha para acesso de sua conta.\n\n" +
                    "\t\t Nova Senha: " + newPass + "\n\n" +
                    "Tenha em mente que a nova senha expira em três horas.\n\n" +
                    "Caso contrário, terá que submeter novamente o pedido para recuperação da conta.\n\n" +
                    "Atenciosamente.\n" +
                    "Equipe Manager Life.\n" +
                    "Organizando sua vida nos menores detalhes!\n\n\n\n" +
                    "Atenção: Por favor, não responda a esse e-mail. " +
                    "Essa conta de e-mail é utilizada para o fim exclusivo de enviar mensagens e notificações para os nossos clientes. " +
                    "Os e-mails enviados para essa conta não serão verificados pela nossa equipe e, portanto, não serão respondidos."
            );

            Transport.send(message);

            sendMsg = true;

            GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Sucesso!");

        } catch (MessagingException e) {
            GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Error!" + e.getMessage() + "\t" + Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }

        return sendMsg;
    }
}
