package fr.huiitre.tools.tools_health.weight_log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import fr.huiitre.tools.tools_health.weight_log.model.WeightLog;

@Repository
public interface WeightLogRepository extends JpaRepository<WeightLog, Long> {
    List<WeightLog> findByIduserOrderByLoggedAtDesc(Long iduser);
    Optional<WeightLog> findByIdAndIduser(Long id, Long iduser);
}