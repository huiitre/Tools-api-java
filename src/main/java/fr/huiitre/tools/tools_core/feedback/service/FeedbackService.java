package fr.huiitre.tools.tools_core.feedback.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.huiitre.tools.tools_core.feedback.dto.AddFeedbackRequest;
import fr.huiitre.tools.tools_core.feedback.model.Feedback;
import fr.huiitre.tools.tools_core.feedback.repository.FeedbackRepository;
import fr.huiitre.tools.tools_core.user.model.User;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public Feedback addFeedback(User user, AddFeedbackRequest feedbackDTO) throws Exception {
        if (feedbackDTO == null || feedbackDTO.getMessage() == null || feedbackDTO.getMessage().trim().isEmpty()) {
            throw new Exception("Le message est obligatoire");
        }
        Feedback feedback = new Feedback();
        feedback.setMessage(feedbackDTO.getMessage());
        feedback.setCreatedAt(LocalDateTime.now());
        feedback.setIduser(user.getId());
        feedback.setIsRead(false);

        return feedbackRepository.save(feedback);
    }
}