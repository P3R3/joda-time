package org.joda.time.tz;

import org.joda.time.DateTimeZone;

import java.util.Set;

/**
 * Created by Pere on 27/05/2016.
 */
public abstract class ProviderSupplier {

    private ProviderSupplier nextLoader;

    public void nextApproach(ProviderSupplier loader) {
        nextLoader = loader;
    }

    protected abstract Provider supply();

    public Provider getProvider() {
        Provider provider = supply();

        if (null==provider) {
            provider = nextLoader.getProvider();
        }

        return validateProvider(provider);
    }

    private static Provider validateProvider(Provider provider) {
        Set<String> ids = provider.getAvailableIDs();
        if (ids == null || ids.size() == 0) {
            throw new IllegalArgumentException("The provider doesn't have any available ids");
        }
        if (!ids.contains("UTC")) {
            throw new IllegalArgumentException("The provider doesn't support UTC");
        }
        if (!DateTimeZone.UTC.equals(provider.getZone("UTC"))) {
            throw new IllegalArgumentException("Invalid UTC zone provided");
        }
        return provider;
    }


}

