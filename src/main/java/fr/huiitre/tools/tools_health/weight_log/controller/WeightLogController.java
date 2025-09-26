package fr.huiitre.tools.tools_health.weight_log.controller;

import fr.huiitre.tools.annotations.RequireToken;
import fr.huiitre.tools.common.controller.BaseController;
import fr.huiitre.tools.tools_core.user.model.User;
import fr.huiitre.tools.tools_health.weight_log.model.WeightLog;
import fr.huiitre.tools.tools_health.weight_log.repository.WeightLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/health/weight-log")
public class WeightLogController extends BaseController {

    @Autowired
    private WeightLogRepository repository;

    // CREATE
    @PostMapping
    @RequireToken(true)
    public ResponseEntity<?> create(
        @RequestAttribute("user") User user,
        @RequestBody WeightLog body
    ) {
        try {
            // on force l'user courant
            body.setIduser(user.getId().longValue());

            if (body.getCreatedAt() == null) body.setCreatedAt(OffsetDateTime.now());
            if (body.getUpdatedAt() == null) body.setUpdatedAt(OffsetDateTime.now());
            if (body.getLoggedAt()  == null) body.setLoggedAt(OffsetDateTime.now());

            WeightLog saved = repository.save(body);
            return ResponseEntity
                .created(URI.create("/dtl/weight-log/" + saved.getId()))
                .body(Map.of("data", saved));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("msg", "ta soeur"));
        }
    }

    // READ (by id)
    @GetMapping("/{id}")
    @RequireToken(true)
    public ResponseEntity<?> getById(@RequestAttribute("user") User user, @PathVariable Long id) {
        try {
            return repository.findByIdAndIduser(id, user.getId().longValue())
                .<ResponseEntity<?>>map(wl -> ResponseEntity.ok(Map.of("data", wl)))
                .orElseGet(() -> ResponseEntity.status(404).body(Map.of("msg", "Entrée introuvable")));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }

    // READ (all for current user)
    @GetMapping
    @RequireToken(true)
    public ResponseEntity<?> list(@RequestAttribute("user") User user) {
        try {
            Long uid = user.getId().longValue();
            List<WeightLog> data = repository.findByIduserOrderByLoggedAtDesc(uid);
            return ResponseEntity.ok(Map.of("data", data));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    @RequireToken(true)
    public ResponseEntity<Map<String, Object>> update(
            @RequestAttribute("user") User user,
            @PathVariable Long id,
            @RequestBody WeightLog body) {
        try {
            return repository.findByIdAndIduser(id, user.getId().longValue())
                    .map(existing -> {
                        if (body.getLoggedAt() != null) existing.setLoggedAt(body.getLoggedAt());
                        existing.setWeightKg(body.getWeightKg());
                        existing.setNotes(body.getNotes());
                        existing.setUpdatedAt(OffsetDateTime.now());

                        WeightLog saved = repository.save(existing);
                        return ResponseEntity.ok(Map.<String, Object>of("data", saved));
                    })
                    .orElseGet(() -> ResponseEntity.status(404)
                            .body(Map.<String, Object>of("msg", "weight_log introuvable")));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(Map.<String, Object>of("msg", e.getMessage()));
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    @RequireToken(true)
    public ResponseEntity<?> delete(
        @RequestAttribute("user") User user,
        @PathVariable Long id
    ) {
        try {
            return repository.findByIdAndIduser(id, user.getId().longValue())
                    .map(existing -> {
                        repository.delete(existing);
                        return ResponseEntity.ok(Map.of("msg", "weight_log supprimé"));
                    })
                    .orElseGet(() -> ResponseEntity.status(404)
                            .body(Map.of("msg", "weight_log introuvable")));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }
}