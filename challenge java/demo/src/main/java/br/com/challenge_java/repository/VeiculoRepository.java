package br.com.challenge_java.repository;

import br.com.challenge_java.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    Optional<Veiculo> findByPlacaNova(String placaNova);
    List<Veiculo> findByUsuarioId(Long usuarioId);

    @Query("SELECT v FROM Veiculo v WHERE v.usuario.id = :usuarioId AND v.placaNova = :placaNova")
    Optional<Veiculo> findByUsuarioIdAndPlacaNova(Long usuarioId, String placaNova);
}