package com.pulse.controller;
import com.pulse.dto.VolunteerResponseDTO;
import com.pulse.service.VolunteerResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blood-requests")
public class VolunteerResponseController {
    private final VolunteerResponseService volunteerResponseService;

    public VolunteerResponseController(VolunteerResponseService volunteerResponseService) {
        this.volunteerResponseService = volunteerResponseService;
    }

    // Endpoint: POST /api/blood-requests/{requestId}/accept
    @PostMapping("/{requestId}/accept")
    public ResponseEntity<VolunteerResponseDTO> acceptBloodRequest(
            @PathVariable Integer requestId,
            @RequestBody VolunteerResponseDTO volunteerResponseDTO) {

        VolunteerResponseDTO acceptedResponse = volunteerResponseService.acceptBloodRequest(requestId, volunteerResponseDTO);
        return new ResponseEntity<>(acceptedResponse, HttpStatus.CREATED);
    }

    // Endpoint: GET /api/blood-requests/{requestId}/responses
    @GetMapping("/{requestId}/responses")
    public ResponseEntity<List<VolunteerResponseDTO>> getResponsesForBloodRequest(@PathVariable Integer requestId) {
        List<VolunteerResponseDTO> responses = volunteerResponseService.getResponsesForBloodRequest(requestId);
        return responses.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
