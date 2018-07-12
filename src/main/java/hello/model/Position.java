package hello.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Position
{
protected String description;
private String timeStr;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("description", description)
                .append("timeStr", timeStr)
                .toString();
    }
}
