
package desenv.sw.corporativo.Login_Seguro.service;

import desenv.sw.corporativo.Login_Seguro.model.Usuario;
import desenv.sw.corporativo.Login_Seguro.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// logica dos cadastros dos usuarios.
@Service
public class UsuarioService {

    private final UsuarioRepository repositorioUsuarios;
    private final PasswordEncoder codificadorSenha;

    // O Spring fornece os objetos que precisamos usar.
    public UsuarioService(UsuarioRepository repositorioUsuarios,
                          PasswordEncoder codificadorSenha) {
        this.repositorioUsuarios = repositorioUsuarios;
        this.codificadorSenha = codificadorSenha;
    }

    public Usuario cadastrarUsuario(String nome, String email, String senha) {

        // padroniza os emails em minusculo
        email = email.trim().toLowerCase();

        // usa o repository para evitar criar conta com o mesmo email
        if (repositorioUsuarios.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Este email ja esta cadastrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);

        // salva o hash da senha original
        usuario.setSenha(codificadorSenha.encode(senha));

        // o tipo de usuario salvo inicialmente sempre é o comum
        usuario.setPapel("UsuarioComum");
        usuario.setAtivo(true);

        return repositorioUsuarios.save(usuario);
    }
}