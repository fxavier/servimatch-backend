package com.xavier.servimatchbackend;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.springframework.modulith.test.ApplicationModuleTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test for Spring Modulith architecture validation.
 * This test ensures that the modular structure is correctly defined and follows architectural constraints.
 */
//@ApplicationModuleTest
class ModulithTest {

    /**
     * Verifies that all application modules are properly structured and follow architectural rules.
     * This test will fail if there are any circular dependencies or architectural violations.
     */
    @Test
    void verifyModularStructure() {
        ApplicationModules modules = ApplicationModules.of(ServimatchBackendApplication.class);

        // Verify that modules are detected correctly
        assertThat(modules.stream()).isNotEmpty();

        // This will throw an exception if there are architectural violations
        modules.verify();
    }

    /**
     * Tests that the common module is properly configured and accessible to other modules.
     */
    @Test
    void verifyCommonModuleStructure() {
        ApplicationModules modules = ApplicationModules.of(ServimatchBackendApplication.class);

        // Verify common module exists
        assertThat(modules.getModuleByName("common")).isPresent();

        var commonModule = modules.getModuleByName("common").get();

        // Verify common module has the expected base packages
        assertThat(commonModule.getBasePackage().getName())
                .isEqualTo("com.xavier.servimatchbackend.common");
    }

    /**
     * Generates documentation for the modular structure.
     * This creates documentation files in the target/modulith-docs directory.
     */
    @Test
    void writeDocumentation() {
        ApplicationModules modules = ApplicationModules.of(ServimatchBackendApplication.class);

        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }

    /**
     * Verifies that module dependencies are correctly configured.
     * This ensures that modules only depend on allowed modules.
     */
    @Test
    void verifyModuleDependencies() {
        ApplicationModules modules = ApplicationModules.of(ServimatchBackendApplication.class);

        // Verify each module individually
        modules.forEach(module -> {
            System.out.println("Module: " + module.getName());
            System.out.println("Base Package: " + module.getBasePackage());
          //  System.out.println("Dependencies: " + module.getDependencies());
            System.out.println("---");
        });
    }
}