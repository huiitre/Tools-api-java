package fr.huiitre.tools.tools_core.feedback.repository;

import fr.huiitre.tools.tools_core.feedback.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    
}