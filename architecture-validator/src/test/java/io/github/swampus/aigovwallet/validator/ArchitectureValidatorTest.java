package io.github.swampus.aigovwallet.validator;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ArchitectureValidatorTest {

    @Test
    void shouldValidateProjectArchitectureManifest() {
        var validator = new ArchitectureValidator();
        var result = validator.validate(Path.of("../architecture/system.yaml"));

        assertTrue(result.valid(), () -> "Expected valid architecture, but got: " + result.errors());
    }
}
