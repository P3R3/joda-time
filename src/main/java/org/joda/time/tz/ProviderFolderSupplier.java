package org.joda.time.tz;

import java.io.File;

/**
 * Created by Pere on 27/05/2016.
 */
public class ProviderFolderSupplier extends ProviderSupplier {
    @Override
    protected Provider supply() {
        try {
            return loadFolderProvider(System.getProperty("org.joda.time.DateTimeZone.Folder"));
        } catch (SecurityException ex) {
            return null;
        }
    }

    private Provider loadFolderProvider(String dataFolder) {
        if (dataFolder == null) return null;

        try {
            return new ZoneInfoProvider(new File(dataFolder));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
