package org.joda.time.tz;

/**
 * Created by Pere on 27/05/2016.
 */
public class ProviderReferenceSupplier extends ProviderSupplier {

    Provider provider;

    public ProviderReferenceSupplier(Provider provider) {
        this.provider = provider;
    }

    @Override
    protected Provider supply() {
        return provider;
    }
}
