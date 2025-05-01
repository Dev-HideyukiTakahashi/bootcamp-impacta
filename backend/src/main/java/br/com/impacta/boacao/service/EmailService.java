package br.com.impacta.boacao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${email.password-recover.uri}")
    private String recoverUri;

    private final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendPasswordResetTokenEmail(String email, String token) {
        String subject = "Recuperação de senha.";
        String body = """
                Olá,

                Recebemos uma solicitação para redefinir sua senha. Para continuar com o processo de recuperação, clique no link abaixo:

                %s

                Este link é válido por 30 minutos. Após esse período, será necessário solicitar uma nova recuperação.

                Se você não solicitou essa recuperação, pode ignorar este e-mail com segurança.

                Atenciosamente,
                Equipe BoAção
                """
                .formatted(recoverUri + token);

        emailSender.send(buildEmailMessage(email, subject, body));
        logger.info("Email de recuperação de senha enviado ao usuario: {}", email);
    }

    private SimpleMailMessage buildEmailMessage(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        return message;
    }
}