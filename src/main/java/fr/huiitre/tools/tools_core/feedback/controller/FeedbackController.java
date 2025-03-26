package fr.huiitre.tools.tools_core.feedback.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.huiitre.tools.annotations.RequireToken;
import fr.huiitre.tools.common.controller.BaseController;
import fr.huiitre.tools.tools_core.feedback.dto.AddFeedbackRequest;
import fr.huiitre.tools.tools_core.feedback.model.Feedback;
import fr.huiitre.tools.tools_core.feedback.service.FeedbackService;
import fr.huiitre.tools.tools_core.user.model.User;

@RestController
@RequestMapping("/core/feedback")
public class FeedbackController extends BaseController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/add")
    @RequireToken(true)
    public ResponseEntity<?> submitFeedback(@RequestAttribute("user") User user, @RequestBody AddFeedbackRequest feedbackDTO) {
        try {

            Feedback feedback = feedbackService.addFeedback(user, feedbackDTO);

            return ResponseEntity.ok(Map.of( "msg", "Feedback ajouté avec succès", "feedbackId", feedback.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of( "msg", e.getMessage()));
        }
    }
}