package org.joda.time.tz;

/**
 * Created by Pere on 27/05/2016.
 */
public class ProviderClasspathSupplier extends ProviderSupplier {
    @Override
    protected Provider supply() {
        try {
            return new ZoneInfoProvider("org/joda/time/tz/data");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
