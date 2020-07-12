package world.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import world.pet.model.Pet;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

  @Transactional
  @Modifying
  @Query(
      value =
          "SELECT * FROM pet_world_jpa.pets\n"
              + "left join pet_usuario on (pets.pet_id = pet_usuario.pet_id)\n"
              + "left join usuarios on (pet_usuario.usuario_id = usuarios.usuario_id)\n"
              + "where usuarios.usuario_id = :usuario_id",
      nativeQuery = true)
  List<Pet> findAllPetsById(@Param("usuario_id") Long usuarioId);
}
