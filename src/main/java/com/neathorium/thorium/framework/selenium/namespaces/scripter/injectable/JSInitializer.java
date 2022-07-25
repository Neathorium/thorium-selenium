package com.neathorium.thorium.framework.selenium.namespaces.scripter.injectable;

import com.neathorium.thorium.core.data.constants.CoreDataConstants;
import com.neathorium.thorium.core.data.namespaces.factories.DataFactoryFunctions;
import com.neathorium.thorium.core.data.namespaces.predicates.DataPredicates;
import com.neathorium.thorium.core.data.records.Data;
import com.neathorium.thorium.framework.selenium.constants.scripts.injectable.JSInitializerConstants;
import com.neathorium.thorium.framework.selenium.namespaces.driver.DevtoolsDriverFunctions;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.thorium.framework.selenium.namespaces.ExecutionCore;
import org.openqa.selenium.WebDriver;

public interface JSInitializer {
    static DriverFunction<Boolean> isJSDependenciesExistData() {
        return ExecutionCore.ifDriver("isJSDependenciesExistData", DevtoolsDriverFunctions.doBooleanCommand(JSInitializerConstants.DEPENDENCY_EXISTENCE_CHECKER), CoreDataConstants.NULL_BOOLEAN);
    }

    private static Data<Boolean> setJSDependenciesCore(WebDriver driver) {
        final var nameof = "setJSDependenciesCore";
        final var isAlreadySet = isJSDependenciesExistData().apply(driver);
        if (DataPredicates.isInvalid(isAlreadySet)) {
            return DataFactoryFunctions.getBoolean(false, nameof, isAlreadySet.MESSAGE().MESSAGE(), isAlreadySet.EXCEPTION(), isAlreadySet.EXCEPTION_MESSAGE());
        }

        if (DataPredicates.isValidNonFalse(isAlreadySet)) {
            return DataFactoryFunctions.getBoolean(true, nameof, isAlreadySet.MESSAGE().MESSAGE(), isAlreadySet.EXCEPTION(), isAlreadySet.EXCEPTION_MESSAGE());
        }

        return DataFactoryFunctions.replaceName(DevtoolsDriverFunctions.doBooleanCommand(JSInitializerScripts.getDefaultJSInitializer()).apply(driver), nameof);
    }

    private static DriverFunction<Boolean> setJSDependenciesCore() {
        return JSInitializer::setJSDependenciesCore;
    }

    static DriverFunction<Boolean> setJSDependencies() {
        return ExecutionCore.ifDriver("setJSDependencies", setJSDependenciesCore(), CoreDataConstants.NULL_BOOLEAN);
    }
}
