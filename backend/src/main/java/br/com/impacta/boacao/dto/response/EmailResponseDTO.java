package br.com.impacta.boacao.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailResponseDTO {

    @NotBlank
    @Email
    private String to;

    @NotBlank
    private String subject;

    @NotBlank
    private String body;

    public EmailResponseDTO() {
    }

    public String getTo() {
        return to;
    }

    public EmailResponseDTO(@NotBlank @Email String to, @NotBlank String subject, @NotBlank String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
