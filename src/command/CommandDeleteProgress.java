package command;

import java.util.regex.Matcher;

import helper.CommonHelper;
import helper.Parser;
import project.Projects;
import service.ServiceHandler;

public class CommandDeleteProgress implements CommandDelete {

    String projectName;
    int index;
    
    public CommandDeleteProgress(String args) {
        
        Matcher matcher = Parser.matchRegex(args, Parser.PATTERN_DELETE_PROGRESS);
        index = Integer.parseInt(matcher.group(Parser.TAG_INDEX)) - 1;
        projectName = matcher.group(Parser.TAG_NAME);
    }
    
    public CommandDeleteProgress(int index, String projectName) {
        this.index = index;
        this.projectName = projectName;
    }

    @Override
    public String execute(ServiceHandler serviceHandler, Projects projectHandler, Revertible mostRecent, Displayable currentDisplay)
            throws Exception {
        if (projectHandler.deleteProgressMessage(index, projectName)) {
            return CommonHelper.SUCCESS_PROGRESS_DELETED;
        } else {
            // TODO: different error when project not found
            throw new Exception(CommonHelper.ERROR_FAIL_DEL_PROGRESS);
        }
    }

    @Override
    public String revert(ServiceHandler serviceHandler, Projects projectHandler, Displayable currentDisplay)
            throws Exception {
        // TODO: save old value
        Command revertDeleteProgressCommand = new CommandAddProgress(index, projectName, "");
        return revertDeleteProgressCommand.execute(serviceHandler, projectHandler, null, currentDisplay);
 
    }

    @Override
    public Displayable getDisplayable() {
        // TODO what to show
        return null;
    }

}
