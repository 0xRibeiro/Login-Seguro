package desenv.sw.corporativo.Login_Seguro.config;

import org.mongodb.spring.session.config.annotation.web.http.EnableMongoHttpSession;
import org.springframework.context.annotation.Configuration;

// Configura o armazenamento das sessoes no MongoDB.
@Configuration
@EnableMongoHttpSession
public class ConfiguracaoSessao {
}