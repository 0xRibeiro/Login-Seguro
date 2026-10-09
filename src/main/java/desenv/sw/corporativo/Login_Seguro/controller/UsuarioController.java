package desenv.sw.corporativo.Login_Seguro.controller;

import desenv.sw.corporativo.Login_Seguro.model.Usuario;
import desenv.sw.corporativo.Login_Seguro.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

// Controla o cadastro dos usuarios.
@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // traz a pagina de cadastro
    @GetMapping("/cadastro")
    public String mostrarCadastro(Usuario usuario) {
        return "cadastro";
    }

    // tenta realizar um cadastro
    @PostMapping("/cadastro")
    public String cadastrarUsuario(@Valid @ModelAttribute Usuario usuario,
                                   BindingResult resultado) {

        if (resultado.hasErrors()) {
            return "cadastro";
        }

        try {
            usuarioService.cadastrarUsuario(
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getSenha()
            );

            // manda o cadastro bem sucedido para a pagina de login
            return "redirect:/login";

        } catch (IllegalArgumentException erro) {
            // trata o erro do email invalido
            resultado.rejectValue("email", "email.duplicado",
                    erro.getMessage());
            return "cadastro";
        }
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

}
