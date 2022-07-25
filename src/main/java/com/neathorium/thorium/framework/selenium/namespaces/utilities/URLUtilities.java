package com.neathorium.thorium.framework.selenium.namespaces.utilities;

import com.neathorium.thorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.thorium.java.extensions.namespaces.utilities.StringUtilities;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface URLUtilities {
    static String handle(String url, String query) {
        final var queryFragment = isNotBlank(query) ? StringUtilities.startsWithCaseInsensitive(query, "?") ? query : "?" + query : CoreFormatterConstants.EMPTY;
        var path = StringUtilities.startsWithCaseInsensitive(url, "http") && StringUtilities.contains(url, "://") ? url : "http://" + url;
        if (!StringUtilities.endsWithCaseInsensitive(path, "/")) {
            path += "/";
        }

        if (isNotBlank(queryFragment)) {
            path += queryFragment;
        }

        return path;
    }
}
