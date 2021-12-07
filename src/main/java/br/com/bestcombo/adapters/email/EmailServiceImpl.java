package br.com.bestcombo.adapters.email;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.adapters.email.anotacao.Layout;
import br.com.bestcombo.adapters.email.dto.EmailTemplate;
import br.com.bestcombo.ports.service.EmailService;
import br.com.bestcombo.util.AnotacaoUtil;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    final Mailer mailer;

    @PostConstruct
    public void postConstruct() {
        Properties properties = new Properties();

        properties.put(RuntimeConstants.RESOURCE_LOADER, "classpath");
        properties.put("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        Velocity.init(properties);
    }

    @Override
    public void sendMail(EmailTemplate emailTemplate) {
            new FutureTask<>((Callable<Void>) () -> {
                try {
                    String body = getBody(emailTemplate);

                    mailer.send(
                            Mail.withHtml(emailTemplate.getTo(), emailTemplate.getSubject(), body)
                    );
                } catch (Exception e) {
                    log.error("Ocorreu um erro ao enviar e-mail", e);
                }
                return null;
            }).run();
    }

    private String getBody(EmailTemplate emailTemplate) {
        VelocityContext velocityContext = new VelocityContext();

        velocityContext.put("data", emailTemplate);

        Template template = Velocity.getTemplate("templates/email/" + AnotacaoUtil.getAnotacao(emailTemplate, Layout.class).value() + ".vm");

        StringWriter writer = new StringWriter();

        template.merge(velocityContext, writer);

        return writer.toString();
    }

}
