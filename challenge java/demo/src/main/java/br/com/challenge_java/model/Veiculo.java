package br.com.challenge_java.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "TB_MTT_VEICULO")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TB_MTT_USUARIO_id", nullable = false)
    private Usuario usuario;

    @Column(name = "placa_antiga", length = 7)
    private String placaAntiga;

    @Column(name = "placa_nova", length = 10, nullable = false, unique = true)
    private String placaNova;

    @Column(name = "tipo_veiculo", length = 75, nullable = false)
    private String tipoVeiculo;

    // @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Iot> dispositivosIot; // Deixaremos Iot para depois
}