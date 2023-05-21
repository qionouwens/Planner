package planner.client.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import planner.client.MainUICtrl;
import planner.client.serverUtils.UpdateServerUtils;
import planner.commons.UpdateDay;
import planner.commons.helper.DateConversion;

import java.util.*;

public class UpdateQuestionsCtrl {
    @FXML
    private Label dayLabel;
    @FXML
    private AnchorPane anchorPane;
    private List<CheckBox> checkBoxes;
    private static UpdateQuestionsCtrl INSTANCE;
    private MainUICtrl mainUICtrl;
    private GregorianCalendar calendar;

    public UpdateQuestionsCtrl() {
        INSTANCE = this;
    }

    public void initialise(MainUICtrl mainUICtrl, GregorianCalendar calendar) {
        this.mainUICtrl = mainUICtrl;
        this.calendar = calendar;
        setUpCheckboxes();
        setDayLabel();
    }

    public static UpdateQuestionsCtrl getInstance() {
        return INSTANCE;
    }

    public void setDayLabel() {
        String date = DateConversion.getStringFromDate(calendar);
        String dayString = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH);
        dayLabel.setText(dayString + " " + date);
    }

    public void setUpCheckboxes() {
        anchorPane.getChildren().removeIf(node ->
                node.getLayoutX() == 55 ||
                node.getLayoutX() == 300);
        UpdateServerUtils updateServerUtils = new UpdateServerUtils();
        List<String> categories = updateServerUtils.getCategories();
        int[] dates = DateConversion.getDateArray(calendar);
        UpdateDay thisDay = updateServerUtils.getUpdateDay(dates[0], dates[1], dates[2]);
        List<String> selected = thisDay.getCategoryMap();
        checkBoxes = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            CheckBox checkBox = new CheckBox(categories.get(i));
            if (selected.contains(categories.get(i))) {
                checkBox.setSelected(true);
            }
            int x = i % 2 == 0 ? 55 : 300;
            int y = ((i + 4) / 2) * 30;
            checkBox.setLayoutX(x); checkBox.setLayoutY(y);
            anchorPane.getChildren().add(checkBox);
            checkBoxes.add(checkBox);
        }
    }

    public void save() {
        List<String> selected = new ArrayList<>();
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                selected.add(checkBox.getText());
            }
        }
        UpdateServerUtils updateServerUtils = new UpdateServerUtils();
        UpdateDay updateDay = new UpdateDay(calendar, selected);
        updateServerUtils.sendUpdateDay(updateDay);
    }

    public void prevDay() {
        save();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        initialise(mainUICtrl, calendar);
    }

    public void nextDay() {
        save();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        initialise(mainUICtrl, calendar);
    }

    public void close() {
        save();
        mainUICtrl.showHome();
    }
}
