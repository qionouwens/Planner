package planner.commons;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UpdateDay {
    private GregorianCalendar calendar;
    private List<String> categoryMap;

    public UpdateDay() {}

    public UpdateDay(GregorianCalendar calendar, List<String> categoryMap) {
        this.calendar = calendar;
        this.categoryMap = categoryMap;
    }

    public GregorianCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(GregorianCalendar calendar) {
        this.calendar = calendar;
    }

    public List<String> getCategoryMap() {
        return categoryMap;
    }

    public void setCategoryMap(List<String> categoryMap) {
        this.categoryMap = categoryMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateDay updateDay = (UpdateDay) o;
        return Objects.equals(calendar, updateDay.calendar) && Objects.equals(categoryMap, updateDay.categoryMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calendar, categoryMap);
    }

    @Override
    public String toString() {
        return "UpdateDay{" +
                "calendar=" + calendar +
                ", categoryMap=" + categoryMap +
                '}';
    }
}
