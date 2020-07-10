package world.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.pet.model.Adocao;
import world.pet.model.Pet;

@Repository
public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
}
