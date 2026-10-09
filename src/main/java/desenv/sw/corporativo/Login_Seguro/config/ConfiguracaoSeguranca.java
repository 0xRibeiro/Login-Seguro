package desenv.sw.corporativo.Login_Seguro.config;

import desenv.sw.corporativo.Login_Seguro.model.Usuario;
import desenv.sw.corporativo.Login_Seguro.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// regras de acesso e busca de usuarios para o login
@Configuration
public class ConfiguracaoSeguranca implements UserDetailsService {

    private final UsuarioRepository repositorioUsuarios;

    public ConfiguracaoSeguranca(UsuarioRepository repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
    }

    // quais paginas da para acessar pra cada usuario
    @Bean
    public SecurityFilterChain configurarAcessos(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(regras -> regras
                        .requestMatchers("/cadastro").permitAll()
                        .requestMatchers("/admin/**").hasRole("Admin")
                        .requestMatchers("/vip/**").hasAnyRole("UsuarioVIP", "Admin")
                        .requestMatchers("/", "/inicio").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .defaultSuccessUrl("/inicio", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    // busca a conta pelo email informado no login
    @Override
    public UserDetails loadUserByUsername(String email) {
        Usuario usuario = repositorioUsuarios
                .findByEmail(email.trim().toLowerCase())
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario nao encontrado"));

        return User.withUsername(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(usuario.getPapel())
                .disabled(!usuario.taAtivo())
                .build();
    }
}