package planner.server;

import planner.commons.*;
import planner.commons.helper.DateConversion;
import planner.database.controller.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class RunServerCommands {
    public static void main(String[] args){
        InventoryItem inventoryItem = new InventoryItem(1, "cup", 1, "Fridge");
        InventoryDBController.addItem(inventoryItem);
    }
}
