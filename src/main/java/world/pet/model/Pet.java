package world.pet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "pets")
public class Pet {
    @Id
    private Long id;

    private String nome;
    private String tipo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate idade;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Enumerated(EnumType.STRING)
    private StatusAdocao status;
}
