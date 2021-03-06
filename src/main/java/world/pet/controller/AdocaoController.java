package world.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import world.pet.model.Adocao;
import world.pet.model.Pet;
import world.pet.model.StatusAdocao;
import world.pet.model.Usuario;
import world.pet.repository.AdocaoRepository;
import world.pet.repository.PetRepository;
import world.pet.repository.UsuarioRepository;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
@RequestMapping("/adocao")
public class AdocaoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AdocaoRepository adocaoRepository;

    @GetMapping("/adotar/{id}")
    public ModelAndView cadastrar(ModelAndView mv, @PathVariable Long id, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Pet pet = petRepository.getOne(id);

        if(session.getAttribute("usuario") == null){

            return new ModelAndView("redirect:/pets");
    } else if(pet.getStatus() == StatusAdocao.DISPONIVEL && !pet.getUsuarioNome().equals(usuario.getUsuarioNome())){

      mv.addObject("usuario", usuario);

      mv.addObject("pet", pet);

      mv.setViewName("adocao/form");
      return mv;
        }else {
            return new ModelAndView("redirect:/pets/listar");
        }
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(ModelAndView mv,@RequestParam Long usuario_id, Pet pet, Long antigo_dono_id){

        Usuario usuario = usuarioRepository.getOne(usuario_id);

        Adocao adocao = new Adocao();
        adocao.setPetId(pet.getPetId());
        adocao.setUsuarioId(usuario_id);
        adocao.setAntigoUsuarioId(antigo_dono_id);
        adocao.setDataAdocao(LocalDate.now());
        adocaoRepository.save(adocao);

        adocaoRepository.updateStatus(pet.getPetId(),usuario_id);


        Pet pet1 = petRepository.getOne(pet.getPetId());
        pet1.setUsuarioNome(usuario.getUsuarioNome());
        pet1.setUsuarioId(usuario_id);
        petRepository.save(pet1);

        return new ModelAndView("redirect:/pets/listar");
    }



}
