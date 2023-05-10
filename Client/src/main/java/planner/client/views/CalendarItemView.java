package planner.client.views;

import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import planner.client.helper.CalendarOverview;
import planner.commons.CalendarItem;

public class CalendarItemView extends Pane {
    private Label title;
    private CalendarItem calendarItem;
    public CalendarItemView(CalendarItem calendarItem) {
        super();
        this.calendarItem = calendarItem;
        createView();
    }

    public void createView() {
        double height = CalendarOverview.height(calendarItem.getEndTime(), calendarItem.getStartTime());

        this.setPrefSize(206, height);
        this.setLayoutY(CalendarOverview.getY(calendarItem.getStartTime()));
        this.setLayoutX(CalendarOverview.getX(calendarItem.getDate()));
        this.setStyle("-fx-background-color: " + calendarItem.getColour());

        title = new Label();
        title.setText(calendarItem.getTitle());
        title.setPrefSize(206, height);
        title.setTextAlignment(TextAlignment.CENTER);
        title.setContentDisplay(ContentDisplay.CENTER);
        title.setAlignment(Pos.CENTER);
        title.setFont(new Font(24));
        title.setWrapText(true);
        this.getChildren().add(title);
    }
}
