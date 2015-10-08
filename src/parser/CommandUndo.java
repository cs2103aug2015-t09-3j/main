package parser;

import java.util.Stack;

import projectlogic.ProjectHandler;
import crudLogic.ServiceHandler;

public class CommandUndo extends Command {

    public static final String KEYWORD = "undo";
    
    public CommandUndo(String args) throws Exception {
        this.setRequireConfirmation(false);
        this.setRevertible(false);
        
        if (args.trim().isEmpty());
        else {
            // undo command accept no arguments
            throw new Exception(String.format(Helper.ERROR_INVALID_ARGUMENTS, KEYWORD));
        }
        
    }
    
    @Override
    public String execute(ServiceHandler serviceHandler, ProjectHandler projectHandler, Stack<Command> historyList) throws Exception {
        if (historyList.empty()) {
            return Helper.ERROR_EMPTY_HISTORY;
        }
        Command lastCommand = historyList.pop();
        return lastCommand.revert(serviceHandler, projectHandler, historyList);
    }

    @Override
    public String revert(ServiceHandler serviceHandler, ProjectHandler projectHandler, Stack<Command> historyList) throws Exception {
        // not revertible
        return null;
    }

}