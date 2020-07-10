package world.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import world.pet.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  @Transactional
  @Modifying
  @Query(value = "select * from pet_world_jpa.usuarios left join pet_world_jpa.pet_usuario\n"
              + "on (pet_world_jpa.usuarios.id = pet_world_jpa.pet_usuario.usuario_id)\n"
              + "where pet_id like :pet_id", nativeQuery = true)
  Usuario findUserByPetId(@Param("pet_id") Long id);
}
