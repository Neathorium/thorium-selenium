package com.neathorium.thorium.framework.selenium.records.scripter;

import com.neathorium.thorium.framework.selenium.abstracts.ExecuteBaseData;
import com.neathorium.thorium.framework.selenium.namespaces.extensions.boilers.ScriptFunction;
import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.thorium.core.records.ExecuteCommonData;
import org.openqa.selenium.JavascriptExecutor;

import java.security.InvalidParameterException;
import java.util.function.Function;

public class ExecuteRegularData<T> extends ExecuteBaseData<T, Function<String, Object>> implements ScriptFunction<Function<String, Object>> {
    public final ScriptFunction<Function<String, Object>> handler;

    public ExecuteRegularData(
        ExecuteCommonData<T> commonData,
        ScriptFunction<Function<String, Object>> handler
    ) {
        super(commonData);
        this.handler = handler;
    }

    @Override
    public Function<String, Object> apply(JavascriptExecutor executor) {
        if (NullableFunctions.isNull(executor)) {
            throw new InvalidParameterException("Executor parameter" + CoreFormatterConstants.WAS_NULL);
        }

        return handler.apply(executor);
    }
}
