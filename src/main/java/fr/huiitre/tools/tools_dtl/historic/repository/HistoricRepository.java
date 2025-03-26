package fr.huiitre.tools.tools_dtl.historic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.huiitre.tools.tools_dtl.historic.model.Historic;

@Repository
public interface HistoricRepository extends JpaRepository<Historic, Long> {
    Historic findByCode(String code);

}