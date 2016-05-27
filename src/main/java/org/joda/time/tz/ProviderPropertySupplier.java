package org.joda.time.tz;

/**
 * Created by Pere on 27/05/2016.
 */
public class ProviderPropertySupplier extends ProviderSupplier {
    @Override
    protected Provider supply() {
        try {
            return loadPropertyProvider(System.getProperty("org.joda.time.DateTimeZone.Provider"));
        } catch (SecurityException ex) {
            return null;
        }
    }

    private Provider loadPropertyProvider(String providerClass ) {
        if (providerClass == null) return null;

        try {
            return (Provider) Class.forName(providerClass).newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


}
