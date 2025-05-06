package com.example.demo.controller;

//import com.example.demo.entity.ApiParameter;
import com.example.demo.service.ApiService;
import com.example.demo.entity.ApiParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scripts")
public class ApiController {

    @Autowired
    private ApiService ApiService;

    // Create and execute script
    @PostMapping
    public ResponseEntity<String> executeScript(@RequestBody ApiParameter parameter) {
        ApiService.saveParameter(parameter);
        String output = ApiService.runPythonScript(parameter);
        return ResponseEntity.ok(output);
    }

    // Read all parameters
    @GetMapping
    public List<ApiParameter> getAllParameters() {
        return ApiService.getAllParameters();
    }

    // Read parameter by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiParameter> getParameterById(@PathVariable Long id) {
        ApiParameter parameter = ApiService.getParameterById(id);
        if (parameter != null) {
            return ResponseEntity.ok(parameter);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update parameter
    @PutMapping("/{id}")
    public ResponseEntity<ApiParameter> updateParameter(@PathVariable Long id, @RequestBody ApiParameter parameter) {
        ApiParameter updatedParameter = ApiService.updateParameter(id, parameter);
        if (updatedParameter != null) {
            return ResponseEntity.ok(updatedParameter);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete parameter by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParameter(@PathVariable Long id) {
        if (ApiService.deleteParameter(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
