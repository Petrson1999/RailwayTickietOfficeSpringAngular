package com.railvayticketiffice.services.implementation;

import com.railvayticketiffice.data.Mail;
import com.railvayticketiffice.entity.Ticket;
import com.railvayticketiffice.entity.User;
import com.railvayticketiffice.services.interfaces.EmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource("classpath:mail.properties")
public class EmailServiceImpl implements EmailService {

    private static final Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);

    private JavaMailSender emailSender;

    private final Configuration freemarkerConfig;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, @Qualifier("getFreeMarkerConfiguration") Configuration freemarkerConfig) {
        this.emailSender = emailSender;
        this.freemarkerConfig = freemarkerConfig;
    }

    @Override
    public void sendEmail(User user, Ticket ticket, String to) {
        sendMessage(createMail(to, "Ticket order",
                "Flight \"" + ticket.getFlight().getName() + "\" "
                        + "departure station \" " + ticket.getFlight().getDepartureStation().getName() + "\" "
                        + "arrival station \" " + ticket.getFlight().getArrivalStation().getName() + "\" "
                        + "\nBest regards, ", user.getName() + " " + user.getSurname()), "ticket.pdf");
    }

    private void sendMessage(Mail mail, String path) {
        LOG.debug("method was invoked");
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            mimeMessageHelper.addAttachment("userTicket.pdf", new ClassPathResource(path));

            ClassPathResource classPathResource =  new ClassPathResource(path);


            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
            Template template ;
            String html = null;
            try {
                template = freemarkerConfig.getTemplate("email-template.ftl");
                html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());
            } catch (IOException e) {
                LOG.error("Email template not found");
            } catch (TemplateException e) {
                LOG.error("Can't process template into string");
            }

            mimeMessageHelper.setTo(mail.getTo());
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setFrom(mail.getFrom());

            emailSender.send(message);
            LOG.debug("Email was sent to email address {}", mail.getTo());
        } catch (MessagingException e) {
            LOG.error(String.format("Email was not sent. %s", e));
        }
    }

    private Mail createMail(String to, String subject, String recipientText, String userName) {
        Mail mail = new Mail();
        mail.setFrom("1999petrson@gmail.com");
        mail.setTo(to);
        mail.setSubject(subject);
        Map<String, Object> model = new HashMap<>();
        model.put("name", userName);
        model.put("text", recipientText);
        mail.setModel(model);
        return mail;
    }
}
