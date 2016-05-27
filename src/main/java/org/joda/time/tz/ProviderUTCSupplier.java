package org.joda.time.tz;

/**
 * Created by Pere on 27/05/2016.
 */
public class ProviderUTCSupplier extends ProviderSupplier {
    @Override
    protected Provider supply() {
        return new UTCProvider();
    }
}
