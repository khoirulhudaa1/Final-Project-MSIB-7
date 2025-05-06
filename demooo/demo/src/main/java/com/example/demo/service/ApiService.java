package com.example.demo.service;

import com.example.demo.entity.ApiParameter;
import com.example.demo.repository.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@Service
public class ApiService {

    @Autowired
    private ApiRepository repository; // Corrected
    // Save parameter

    public ApiParameter saveParameter(ApiParameter parameter) {
        return repository.save(parameter);
    }

    // Get all parameters
    public List<ApiParameter> getAllParameters() {
        return repository.findAll();
    }

    // Get parameter by ID
    public ApiParameter getParameterById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Update parameter
    public ApiParameter updateParameter(Long id, ApiParameter newParameter) {
        Optional<ApiParameter> optionalParameter = repository.findById(id);
        if (optionalParameter.isPresent()) {
            ApiParameter existingParameter = optionalParameter.get();
            existingParameter.setNasdaqTraded(newParameter.getNasdaqTraded());
            existingParameter.setMarketCategory(newParameter.getMarketCategory());
            existingParameter.setRoundLotSize(newParameter.getRoundLotSize());
            return repository.save(existingParameter);
        }
        return null;
    }

    // Delete parameter by ID
    public boolean deleteParameter(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // Run Python script
    public String runPythonScript(ApiParameter parameter) {
        try {
            String[] command = {
                    "python",
                    "C:/Users/ASUS/MSIB2024/Tugas_Akhir/point_3.py",
                    "--Nasdaq Traded", parameter.getNasdaqTraded(),
                    "--Market Category", parameter.getMarketCategory(),
                    "--Round Lot Size", parameter.getRoundLotSize()
            };

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            output.append("Exited with code: ").append(exitCode);

            return output.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error running script: " + e.getMessage();
        }
    }
}