package world.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import world.pet.model.Pet;
import world.pet.model.Usuario;
import world.pet.repository.UsuarioRepository;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ModelAndView login(ModelAndView mv){




        mv.setViewName("login/login");
        return mv;
    }


    @PostMapping("/logar")
    public ModelAndView login(ModelAndView mv, @RequestParam String email , @RequestParam String senha, HttpSession session){

    System.out.println("Email e senha:" + email+ senha);

        if (email != null && email != "" && senha != null && senha != "") {

      Usuario usuario = usuarioRepository.findUser(email, senha);

      if (usuario == null) {
        mv.setViewName("login/login");

      } else {
        session.setAttribute("usuario", usuario);
        return new ModelAndView("redirect:/pets/listar");
      }
     }
        return mv;
    }


    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView mv, HttpSession session){

        session.invalidate();

        return new ModelAndView("redirect:/pets");

    }






//    @GetMapping("/cadastrar")
//    public ModelAndView cadastrar(ModelAndView mv){
//        mv.addObject("usuario", new Usuario());
////        mv.addObject("endereco", new Endereco());
//        mv.setViewName("usuarios/form");
//        return mv;
//    }
//
//
//    @GetMapping("editar/{id}")
//    public ModelAndView editar(ModelAndView mv, @PathVariable Long id){
//        Optional<Usuario> usuario = usuarioRepository.findById(id);
//
//        mv.addObject("usuario",usuario.get());
//
//        mv.setViewName("usuarios/form_editar");
//        return mv;
//    }
//
//    @PostMapping("/salvar")
//    public ModelAndView salvar(@ModelAttribute Usuario usuario){
//
//
//        usuarioRepository.save(usuario);
//        return new ModelAndView("redirect:/usuarios");
//    }
//
//    @GetMapping("excluir/{id}")
//    public ModelAndView excluir(ModelAndView mv, @PathVariable Long id){
//        Usuario usuario = new Usuario();
//        usuario.setUsuarioId(id);
//        usuarioRepository.delete(usuario);
//
//        return new ModelAndView("redirect:/usuarios");
//    }
}
