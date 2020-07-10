package world.pet.controller;

import liquibase.pro.packaged.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import world.pet.model.Pet;
import world.pet.model.Usuario;
import world.pet.repository.PetRepository;
import world.pet.repository.UsuarioRepository;

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
    public ModelAndView listar(ModelAndView mv){

        Iterable<Pet> petIterable = petRepository.findAll();

        System.out.println("imprimindo pets :::: "+ petIterable.toString());

        mv.addObject("pets",petIterable);
        mv.setViewName("pets/all");
        return mv;
    }

    @GetMapping("/meu_pets")
    public ModelAndView meusPets(ModelAndView mv){
        Iterable<Usuario> usuarios = usuarioRepository.findAll();
        mv.addObject("usuarios", usuarios);

        return mv;
    }

    @PostMapping("/meus_pets2")
    public ModelAndView meusPets2(ModelAndView mv, @RequestParam(required = false, defaultValue = "1")Long usuario_id){
        Iterable<Usuario> usuarios = usuarioRepository.findAll();
        mv.addObject("usuarios", usuarios);


        mv.addObject("usuarioID", usuario_id);


        Iterable<Pet> petIterable = petRepository.findAllPetsAndUsersById(usuario_id);
        mv.addObject("pets",petIterable);
        mv.setViewName("pets/list");
        return mv;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(ModelAndView mv){
        Iterable<Usuario> usuarios = usuarioRepository.findAll();
        mv.addObject("usuarios", usuarios);
        mv.addObject("pet", new Pet());
        mv.setViewName("pets/form");
        return mv;
    }

    @GetMapping("editar/{id}")
    public ModelAndView editar(ModelAndView mv, @PathVariable Long id){
        Optional<Pet> pet = petRepository.findById(id);


        mv.addObject("pet",pet.get());

        Usuario usuarioId = usuarioRepository.findUserByPetId(id);
        mv.addObject("usuarioId", usuarioId.getId());

        Iterable<Usuario> usuarioIterable = usuarioRepository.findAll();
        mv.addObject("usuarios", usuarioIterable);

        mv.setViewName("pets/form");
        return mv;
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@RequestParam Long usuario_id, Pet pet){



        Usuario usuario = new Usuario();
        usuario.setId(usuario_id);

        pet.setUsuarioList(Arrays.asList(usuario));

        petRepository.save(pet);

        return new ModelAndView("redirect:/pets/meus_pets");
    }

    @GetMapping("excluir/{id}")
    public ModelAndView excluir(ModelAndView mv, @PathVariable Long id){
        Pet pet = new Pet();
        pet.setId(id);
        petRepository.delete(pet);

        return new ModelAndView("redirect:/pets");
    }

}
