package planner.client.views;

import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import planner.client.MainUICtrl;
import planner.client.helper.CalendarOverview;
import planner.commons.CalendarItem;

public class CalendarItemView extends Pane {
    private final CalendarItem calendarItem;
    private final MainUICtrl mainUICtrl;
    private final boolean isHomeScreen;
    public CalendarItemView(CalendarItem calendarItem, MainUICtrl mainUICtrl, boolean isHomeScreen) {
        super();
        this.calendarItem = calendarItem;
        this.mainUICtrl = mainUICtrl;
        this.isHomeScreen = isHomeScreen;
        createView();
    }

    public void createView() {
        int width;
        double height;
        double layX;
        double layY;
        int fontSize;
        if (!isHomeScreen) {
            width = 206;
            height = CalendarOverview.height(calendarItem.getEndTime(), calendarItem.getStartTime());
            layX = CalendarOverview.getX(calendarItem.getDate());
            layY = CalendarOverview.getY(calendarItem.getStartTime());
            fontSize = 24;
        } else {
            width = 103;
            height = CalendarOverview.homeHeight(calendarItem.getEndTime(), calendarItem.getStartTime());
            layX = CalendarOverview.getHomeX(calendarItem.getDate());
            layY = CalendarOverview.getHomeY(calendarItem.getStartTime(), calendarItem.getEndTime());
            fontSize = 12;
        }
        if (height == 0) {
            return;
        }
        this.setPrefSize(width, height);
        this.setLayoutY(layY);
        this.setLayoutX(layX);
        this.setStyle("-fx-background-color: " + calendarItem.getColour());

        this.setOnMouseClicked(event -> mainUICtrl.showCalendar(calendarItem));

        Label title = new Label();
        title.setText(calendarItem.getTitle());
        title.setPrefSize(width, height);
        title.setTextAlignment(TextAlignment.CENTER);
        title.setContentDisplay(ContentDisplay.CENTER);
        title.setAlignment(Pos.CENTER);
        title.setFont(new Font(fontSize));
        title.setWrapText(true);
        this.getChildren().add(title);
    }
}
