package world.pet.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "adocoes")
@Entity
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long petId;
    private Long usuarioId;
    private Long antigoUsuarioId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAdocao;
    private String informacoes;



}
