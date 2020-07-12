package world.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import world.pet.model.Adocao;
import world.pet.model.Pet;

@Repository
public interface AdocaoRepository extends JpaRepository<Adocao, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE pet_usuario SET pet_usuario.usuario_id = :usuario_id WHERE pet_usuario.pet_id = :pet_id", nativeQuery = true)
    int updateStatus(@Param("pet_id") Long pet_id, @Param("usuario_id") Long usuario_id);

}
