package world.pet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data@AllArgsConstructor@NoArgsConstructor
@ToString
@Entity@Table(name = "usuarios")
public class Usuario {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate idade;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @ManyToMany(mappedBy = "usuarioList")
    private List<Pet> petList;


}
