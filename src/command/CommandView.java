package command;

import java.util.ArrayList;
import java.util.Stack;

import helper.CalendarHelper;
import helper.CommonHelper;
import object.Event;
import object.Todo;
import project.ProjectHandler;
import service.ServiceHandler;

public class CommandView extends Command {

    public static final String KEYWORD = "view";
    
    String dateString;
    
    public CommandView(String args) throws Exception {
        this.setRequireConfirmation(false);
        this.setRevertible(false);
        
        dateString = args.trim();
        
        if (CalendarHelper.getCalendarStringType(dateString) != CalendarHelper.TYPE_DATE) {
            throw new Exception(CommonHelper.ERROR_INVALID_DATE_TIME);
        }
    }
    
    @Override
    public String execute(ServiceHandler serviceHandler, ProjectHandler projectHandler, Stack<Command> historyList)
            throws Exception {
        ArrayList<Event> eventList = serviceHandler.viewEventByDate(dateString);
        ArrayList<Todo> todoList = serviceHandler.viewTaskByDate(dateString);
        ArrayList<Todo> floatingTodoList = serviceHandler.viewTaskNoDate();
        
        StringBuilder feedback = new StringBuilder();
        feedback.append("NowGotTime on " + dateString + "\n");
        feedback.append("----------------------------------------\n");
        feedback.append("--Event\n");
        feedback.append(CommonHelper.getFormattedEventList(eventList, dateString));
        feedback.append("----------------------------------------\n");
        feedback.append("--Todo\n");
        feedback.append(CommonHelper.getFormattedTodoList(todoList, dateString));
        feedback.append("----------------------------------------\n");
        feedback.append("--Floating Todo\n");
        feedback.append(CommonHelper.getFormattedTodoList(floatingTodoList, dateString));
        feedback.append("----------------------------------------\n");
        
        return feedback.toString();
    }

    @Override
    public String revert(ServiceHandler serviceHandler, ProjectHandler projectHandler, Stack<Command> historyList)
            throws Exception {
        // view command cannot be reverted
        return null;
    }

}
