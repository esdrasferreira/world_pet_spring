package world.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import world.pet.model.Pet;
import world.pet.model.Usuario;
import world.pet.repository.PetRepository;
import world.pet.repository.UsuarioRepository;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping
    public ModelAndView home(ModelAndView mv){

        Iterable<Pet> petIterable = petRepository.findAll();

        mv.addObject("pets",petIterable);
        mv.setViewName("pets/home");
        return mv;
    }

    @GetMapping("/listar")
    public ModelAndView listar(ModelAndView mv, HttpSession session){


        if(session.getAttribute("usuario") == null){

            return new ModelAndView("redirect:/pets");
    } else {

      Iterable<Pet> petIterable = petRepository.findAll();

      mv.addObject("pets", petIterable);
      mv.setViewName("pets/all");
        }
        return mv;
    }

    @GetMapping("/meus_pets")
    public ModelAndView meusPets(ModelAndView mv, HttpSession session){

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if(session.getAttribute("usuario") == null){

            return new ModelAndView("redirect:/pets");
    } else {



      Iterable<Pet> petIterable = petRepository.findAllPetsById(usuario.getUsuarioId());
      mv.addObject("pets", petIterable);
      mv.setViewName("pets/meus");
      return mv;
        }
    }


    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(ModelAndView mv, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if(session.getAttribute("usuario") == null){

            return new ModelAndView("redirect:/pets");
    } else {

      mv.addObject("usuario", usuario);

      mv.addObject("pet", new Pet());
      mv.setViewName("pets/form");
        }
        return mv;
    }

    @GetMapping("editar/{id}")
    public ModelAndView editar(ModelAndView mv, @PathVariable Long id){
        Optional<Pet> pet = petRepository.findById(id);


        mv.addObject("pet",pet.get());

        Usuario usuarioId = usuarioRepository.findUserByPetId(id);
        mv.addObject("usuarioId", usuarioId.getUsuarioId());

        Iterable<Usuario> usuarioIterable = usuarioRepository.findAll();
        mv.addObject("usuarios", usuarioIterable);

        mv.setViewName("pets/form");
        return mv;
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@RequestParam Long usuario_id, Pet pet){

        Usuario usuario = usuarioRepository.getOne(usuario_id);
        pet.setUsuarioId(usuario_id);
        pet.setUsuarioNome(usuario.getUsuarioNome());

        pet.setUsuarioList(Arrays.asList(usuario));

        petRepository.save(pet);

        return new ModelAndView("redirect:/pets/listar");
    }

    @GetMapping("excluir/{id}")
    public ModelAndView excluir(ModelAndView mv, @PathVariable Long id){
        Pet pet = new Pet();
        pet.setPetId(id);
        petRepository.delete(pet);

        return new ModelAndView("redirect:/pets/listar");
    }

}
