package command;

import helper.CommonHelper;
import helper.Parser;

public interface CommandDelete extends Command, Revertible {

    public static final String KEYWORD = "delete";
    
    public static CommandDelete parseCommandDelete(String text) throws Exception {
        
        CommandDelete commandDelete = null;
        
        if (text.matches(Parser.PATTERN_NAME) || text.matches(Parser.PATTERN_INTEGER)) {
            commandDelete = new CommandDeleteItem(text);
            
        } else if (text.matches(Parser.PATTERN_PROJECT)) {
            commandDelete = new CommandDeleteProject(text);
            
        } else if (text.matches(Parser.PATTERN_DELETE_INDEX_FROM_PROJECT)) {
            commandDelete = new CommandDeleteFromProject(text);
            
        } else if (text.matches(Parser.PATTERN_DELETE_PROGRESS)) {
            commandDelete = new CommandDeleteProgress(text);
            
        }
        else {
            throw new Exception(String.format(CommonHelper.ERROR_INVALID_ARGUMENTS, CommandDelete.KEYWORD));
            
        }
        
        
        return commandDelete;

    }
    
}
