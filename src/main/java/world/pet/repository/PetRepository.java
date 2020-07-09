package world.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.pet.model.Pet;
import world.pet.model.Usuario;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
}
