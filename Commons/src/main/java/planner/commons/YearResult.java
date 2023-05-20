package planner.commons;

public class YearResult {
    private int[] resultArray;
    private String category;
    public YearResult(String category, int[] resultArray) {
        this.category = category;
        this.resultArray = resultArray;
    }

    public int getMonth(int i) {
        return resultArray[i];
    }

    public String getCategory() {
        return category;
    }
}
