package hello.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Bill {

    private String orderNumber;
    private String projectDescription;
    private String timeSummary;
    private List<Position> singlePositions = new ArrayList<>();
    private TreeMap<String,SummerizedPosition> summerizedPositions = new TreeMap<>();

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getTimeSummary() {
        return timeSummary;
    }

    public void setTimeSummary(String timeSummary) {
        this.timeSummary = timeSummary;
    }

    public List<Position> getSinglePositions() {
        return singlePositions;
    }

    public void setSinglePositions(List<Position> singlePositions) {
        this.singlePositions = singlePositions;
    }

    public TreeMap<String, SummerizedPosition> getSummerizedPositions() {
        return summerizedPositions;
    }

    public void setSummerizedPositions(TreeMap<String, SummerizedPosition> summerizedPositions) {
        this.summerizedPositions = summerizedPositions;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("summerizedPositions", summerizedPositions)
                .append("orderNumber", orderNumber)
                .append("projectDescription", projectDescription)
                .append("singlePositions", singlePositions)
                .append("timeSummary", timeSummary)
                .toString();
    }
}
