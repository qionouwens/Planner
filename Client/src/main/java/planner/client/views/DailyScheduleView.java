package planner.client.views;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import planner.client.MainUICtrl;
import planner.commons.CalendarItem;

import java.util.List;

public class DailyScheduleView extends AnchorPane {
    private final List<CalendarItem> calendarItems;
    private final MainUICtrl mainUICtrl;

    public DailyScheduleView(List<CalendarItem> calendarItems, MainUICtrl mainUICtrl) {
        this.calendarItems = calendarItems;
        this.mainUICtrl = mainUICtrl;
        createView();
    }

    public void createView() {
        this.setPrefSize(358, 70*calendarItems.size());
        for (int i = 0; i < calendarItems.size(); i++) {
            CalendarItem calendarItem = calendarItems.get(i);
            if (calendarItem == null) {return;}
            AnchorPane newPane = new AnchorPane();
            newPane.setPrefSize(358, 70);
            newPane.setLayoutX(0);
            newPane.setLayoutY(70*i);
            Label timeLabel = new Label(calendarItem.getStartTime() + "-" + calendarItem.getEndTime());
            timeLabel.setPrefSize(105, 70);
            timeLabel.setAlignment(Pos.CENTER);
            timeLabel.setLayoutX(0);
            timeLabel.setLayoutY(0);
            Label titleLabel = new Label(calendarItem.getTitle());
            titleLabel.setPrefSize(253, 70);
            titleLabel.setAlignment(Pos.CENTER);
            titleLabel.setWrapText(true);
            titleLabel.setLayoutX(105);
            timeLabel.setLayoutY(0);
            newPane.getChildren().add(timeLabel);
            newPane.getChildren().add(titleLabel);
            newPane.setStyle("-fx-border-color: black");
            newPane.setOnMouseClicked(event -> mainUICtrl.showCalendar(calendarItem));
            this.getChildren().add(newPane);
        }
    }
}
