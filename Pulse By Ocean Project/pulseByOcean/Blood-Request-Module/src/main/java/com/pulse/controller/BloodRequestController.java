package com.pulse.controller;
import com.pulse.dto.BloodRequestDTO;
import com.pulse.service.BloodRequestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blood-requests")
public class BloodRequestController {
    private final BloodRequestService bloodRequestService;

    public BloodRequestController(BloodRequestService bloodRequestService) {
        this.bloodRequestService = bloodRequestService;
    }

    // Endpoint: POST /api/blood-requests
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createBloodRequest(@Valid @RequestBody BloodRequestDTO bloodRequestDTO) {
        BloodRequestDTO createdRequest = bloodRequestService.createBloodRequest(bloodRequestDTO);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    // Endpoint: GET /api/blood-requests
    @GetMapping
    public ResponseEntity<List<BloodRequestDTO>> getAllBloodRequests() {
        List<BloodRequestDTO> bloodRequests = bloodRequestService.getAllBloodRequests();
        return new ResponseEntity<>(bloodRequests, HttpStatus.OK);
    }

    // Endpoint: PUT /api/blood-requests/{requestId}/close
    @PutMapping("/{requestId}/close")
    public ResponseEntity<String> closeBloodRequest(@PathVariable Integer requestId) {
        return bloodRequestService.closeBloodRequest(requestId)
                .map(request -> new ResponseEntity<>("Request closed successfully", HttpStatus.OK))
                .orElse(new ResponseEntity<>("Request ID not found", HttpStatus.NOT_FOUND));
    }


}
