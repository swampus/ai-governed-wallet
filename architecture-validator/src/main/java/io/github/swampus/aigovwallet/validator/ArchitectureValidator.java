package io.github.swampus.aigovwallet.validator;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Minimal validator for the machine-readable architecture manifest.
 * This is intentionally simple: it validates that declared dependencies point to known modules.
 */
public class ArchitectureValidator {

    @SuppressWarnings("unchecked")
    public ValidationResult validate(Path systemYamlPath) {
        try (InputStream inputStream = Files.newInputStream(systemYamlPath)) {
            var yaml = new Yaml();
            var payload = yaml.load(inputStream);

            if (!(payload instanceof Map<?, ?> rawMap)) {
                return ValidationResult.invalid(List.of("Architecture manifest must be a YAML map."));
            }

            var modules = (List<String>) rawMap.getOrDefault("modules", List.of());
            var dependencies = (List<String>) rawMap.getOrDefault("dependencies", List.of());

            var moduleSet = new HashSet<>(modules);
            var errors = new ArrayList<String>();

            for (var dependency : dependencies) {
                var parts = dependency.split("->");
                if (parts.length != 2) {
                    errors.add("Invalid dependency syntax: " + dependency);
                    continue;
                }

                var left = parts[0].trim();
                var right = parts[1].trim();

                if (!moduleSet.contains(left)) {
                    errors.add("Unknown module in dependency source: " + left);
                }
                if (!moduleSet.contains(right)) {
                    errors.add("Unknown module in dependency target: " + right);
                }
            }

            return errors.isEmpty() ? ValidationResult.valid() : ValidationResult.invalid(errors);
        } catch (Exception exception) {
            return ValidationResult.invalid(List.of("Failed to validate architecture: " + exception.getMessage()));
        }
    }
}
