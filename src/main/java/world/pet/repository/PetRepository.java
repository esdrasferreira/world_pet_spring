package world.pet.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import world.pet.model.Pet;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

//  @Transactional
//  @Modifying
//  @Query( value = "select * from pet_world_jpa.pets\n"
//          + "left join pet_usuario on (pets.pet_id = pet_usuario.pet_id)\n"
//          + "left join usuarios on (pet_usuario.usuario_id = usuarios.usuario_id)\n"
//          + "where usuarios.usuario_id like :id")
//  Iterable<Pet> findAllPetsAndUsersById(@Param("id") Long petId);



}
