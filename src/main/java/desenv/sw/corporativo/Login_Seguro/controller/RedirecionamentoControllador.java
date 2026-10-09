package desenv.sw.corporativo.Login_Seguro.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

// controla para qual pagina vai cada usuario
@Controller
public class RedirecionamentoControllador {

    // usa o papel do usuario como parametro para o redirect
    @GetMapping("/inicio")
    public String mostrarInicio(Authentication autenticacao) {

        if (autenticacao.getAuthorities().stream()
                .anyMatch(papel -> Objects.equals(papel.getAuthority(), "ROLE_Admin"))) {
            return "redirect:/admin/inicio";
        }

        if (autenticacao.getAuthorities().stream()
                .anyMatch(papel -> Objects.equals(papel.getAuthority(), "ROLE_UsuarioVIP"))) {
            return "redirect:/vip/inicio";
        }

        return "redirect:/comum/inicio";
    }

    // mostra a pagina inicial do usuario comum
    @GetMapping("/comum/inicio")
    public String mostrarInicioComum() {
        return "comum";
    }

    // mostra a pagina inicial do usuario vip
    @GetMapping("/vip/inicio")
    public String mostrarInicioVip() {
        return "vip";
    }

    // mostra a pagina inicial do usuario admin
    @GetMapping("/admin/inicio")
    public String mostrarInicioAdmin() {
        return "admin";
    }
}