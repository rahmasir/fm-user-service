package org.rahmasir.fmuserservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.rahmasir.fmuserservice.dto.SkillDto;
import org.rahmasir.fmuserservice.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for skill-related operations.
 */
@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
@Tag(name = "Skills", description = "APIs for retrieving skills")
@SecurityRequirement(name = "bearerAuth")
public class SkillController {

    private final SkillService skillService;

    @Operation(summary = "Get all available skills", description = "Returns a list of all skills defined in the system.")
    @GetMapping
    public ResponseEntity<List<SkillDto>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }
}
