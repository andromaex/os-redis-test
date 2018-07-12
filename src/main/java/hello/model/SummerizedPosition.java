package hello.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class SummerizedPosition extends Position {

    private BigDecimal timeSpend;

    public BigDecimal getTimeSpend() {
        return timeSpend;
    }

    public void setTimeSpend(BigDecimal timeSpend) {
        this.timeSpend = timeSpend;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("timeSpend", timeSpend)
                .toString();
    }
}
