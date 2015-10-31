package command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;

import helper.CommonHelper;
import helper.Parser;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import object.Event;
import project.Projects;
import service.ServiceHandler;
import ui.GUI;

public class CommandViewProjectName implements CommandView {

    public CommandViewProjectName(String args) {
        Matcher matcher = Parser.matchRegex(args, Parser.PATTERN_NAME);
        projectName = matcher.group(Parser.TAG_NAME);
    }
    
    String projectName = null;
    double projectProgress;
    ArrayList<Event> projectEvents;

    public String getProjectName() {
        return projectName;
    }
    
    @Override
    public String execute(ServiceHandler serviceHandler, Projects projectHandler, Revertible mostRecent, Displayable currentDisplay)
            throws Exception {
        projectEvents = projectHandler.viewEventProgressTimeline(projectName);
        if (projectEvents == null) {
            throw new Exception(String.format(CommonHelper.ERROR_PROJECT_NOT_FOUND, projectName));
        }
        projectProgress = projectHandler.progressBar(projectName);
        return "Got it!";
    }

    @Override
    public void display(GridPane displayBox) {
        displayBox.getChildren().clear();
        
        Collections.sort(projectEvents);
        
        String previousDate = null;
        
        int rowIndex = 0;
        int listNumber = 0;
        HBox title = new HBox(GUI.getText(projectName + " (" + String.valueOf(projectProgress)+"%)", Color.ORANGE, 20));
        title.setAlignment(Pos.CENTER);
        
        displayBox.add(title, 0, rowIndex++, 5, 1);
        
        for (Event item : projectEvents) {
            String date = item.getStartDateString();
            
            if (!date.equals(previousDate)) {
                Separator separator = new Separator();
                rowIndex++;
                rowIndex++;
                
                Text dateString = GUI.getText(date, Color.BLACK, 18);
                
                displayBox.add(dateString, 1, rowIndex++, 5, 1);
                displayBox.add(separator, 0, rowIndex++, 5, 1);
            }
            listNumber++;
            
            Text numberingText = GUI.getText(String.valueOf(listNumber), Color.BLACK, 16);
            Text markText = GUI.getText("\u2714", Color.GREEN, 18);
            
            if (!item.getDone()) {
                markText = GUI.getText("\u2610", Color.GREY, 18);
            }
            
            Text nameText;
            
            if (!item.getAdditionalInfo().isEmpty()) {
                nameText = GUI.getText(item.getName() + " (" + item.getAdditionalInfo() + ")", Color.GREY, 16);
            } else {
                nameText = GUI.getText(item.getName(), Color.GREY, 16);
            }
            
            String timeString = "";
            timeString += item.getStartTimeString();
            timeString += " to ";
            
            if (item.getEndDateString().equals(date)) {
                timeString += ((Event)item).getEndTimeString();
            }
            else {
                timeString += ((Event)item).getEndDateTimeString();
            }
            

            Text timeText = GUI.getText(timeString, Color.GREY, 16);

            displayBox.add(numberingText, 0, rowIndex);
            displayBox.add(markText, 1, rowIndex);
            displayBox.add(nameText, 2, rowIndex);
            displayBox.add(timeText, 3, rowIndex);
            
            rowIndex++;
            previousDate = date;
        }
        
    }
    
    @Override
    public Displayable getDisplayable() {
        return this;
    }

}
