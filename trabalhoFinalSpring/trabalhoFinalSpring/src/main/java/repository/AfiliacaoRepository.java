package repository;

import dto.afiliacao.AfiliacaoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AfiliacaoRepository extends JpaRepository<AfiliacaoDTO, Long> {
    AfiliacaoDTO findByNome(String nome);
}
