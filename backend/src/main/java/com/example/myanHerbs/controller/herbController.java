package com.example.myanHerbs.controller;


import com.example.myanHerbs.dto.herbDto;
import com.example.myanHerbs.service.herbService;
import com.example.myanHerbs.util.response_template.ApiResponse;
import com.example.myanHerbs.util.response_template.PageNumberResponse;
import com.example.myanHerbs.util.response_template.ResponseUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/herb")
public class herbController {

    private final Logger logger = LoggerFactory.getLogger(herbController.class);

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @Autowired
    private final herbService herbService;

    public herbController(herbService herbService) {
        this.herbService = herbService;
    }

    @PostMapping("/")
   // @Operation(summary = "Create a new herb", tags = { "Herb Creator" })
    public ResponseEntity<ApiResponse<String>> createHerbs(@Valid @RequestBody herbDto herbDto) {
        try {
            boolean created = herbService.createHerbs(herbDto);
            if (created) {
                return ResponseUtil.createSuccessResponse(HttpStatus.OK, "herbs created successfully", "created");
            } else {
                return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Failed to create herb",
                        "Creation failed due to unknown reasons");
            }
        } catch (DataIntegrityViolationException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Failed to create herb", e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create herb",
                    e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<PageNumberResponse<List<herbDto>>>> readHerbs(
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit
    ) {
        return ResponseUtil.getApiResponseResponseEntity(pageNo, limit,
                paginationParams -> {
                    try {
                        return herbService.readHerbByPagination(paginationParams.pageNo(), paginationParams.limit());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                },
                logger,
                "No herbs found",
                "herbs retrieved successfully");
    }



   @GetMapping("/name")
   public ResponseEntity<?> getHerbByName(@RequestParam(value = "search") String name) {
       try {
            List<herbDto> herbs = herbService.getHerbsByName(name);
            if (herbs.isEmpty()) {
                return ResponseUtil.createSuccessResponse(HttpStatus.OK, "No herbs found containing name: " + name,
                        new ArrayList<>());
            } else {
                return ResponseEntity.ok().body(herbs);
            }
        } catch (Exception e) {
            logger.error("Failed to retrieve herbs by name", e);
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve herbs",
                    e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateHerbs(
            @PathVariable Long herbId, @Valid @RequestBody herbDto herbDTO) {
        try {
            boolean updated = herbService.updateHerbs(herbDTO, herbId);
            if (updated) {
                return ResponseUtil.createSuccessResponse(HttpStatus.OK, "herbs updated successfully", "updated");
            } else {
                return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "herbs not found with ID: " + herbId,
                        null);
            }
        } catch (EntityNotFoundException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "herbs not found with ID: " + herbId,
                    e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update herbs",
                    e.getMessage());
        }
    }


    @DeleteMapping("/{herbId}")
    public ResponseEntity<ApiResponse<String>> deleteHerbs(@PathVariable Long herbId) {
        try {
            boolean deleted = herbService.deleteHerbs(herbId);
            if (deleted) {
                return ResponseUtil.createSuccessResponse(HttpStatus.OK, "herb deleted successfully", "deleted");
            } else {
                return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "herb not found with ID: " + herbId,
                        null);
            }
        } catch (Exception e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete herb",
                    e.getMessage());
        }
    }


}
