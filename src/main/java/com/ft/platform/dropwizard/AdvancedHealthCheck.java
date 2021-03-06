package com.ft.platform.dropwizard;

import com.codahale.metrics.health.HealthCheck;
import com.google.common.base.Objects;

public abstract class AdvancedHealthCheck extends HealthCheck implements Comparable<AdvancedHealthCheck> {

    private final String name;

    protected AdvancedHealthCheck(final String name) {
        this.name = name;
    }

    @Override
    protected final Result check() throws Exception {
        return checkAdvanced().asResult();
    }

    public final AdvancedResult executeAdvanced() {
        try {
            return checkAdvanced();
        } catch (final Error e) {
            throw e;
        } catch (final Throwable e) {
            return AdvancedResult.error(this, e);
        }
    }

    protected String id(){
        return name;
    }

    protected abstract AdvancedResult checkAdvanced() throws Exception;

    protected abstract int severity();

    protected abstract String businessImpact();

    protected abstract String technicalSummary();

    protected abstract String panicGuideUrl();

    public String getName() {
        return name;
    }
    @Override
    public int compareTo(final AdvancedHealthCheck other) {
        return getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this.getClass())
                .add("id", id())
                .add("name", name)
                .add("severity", severity())
                .add("businessImpact", businessImpact())
                .add("technicalSummary", technicalSummary())
                .add("panicGuideUrl", panicGuideUrl())
                .toString();
    }

}
