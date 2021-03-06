//@@author A0126509E

package command;

import java.util.regex.Matcher;

import helper.CommonHelper;
import helper.Parser;
import object.State;
import project.Projects;

public class CommandDeleteProgress implements CommandDelete {

    private int index;
    private String projectName;
    
    public CommandDeleteProgress(String args) {
        
        Matcher matcher = Parser.matchRegex(args, Parser.PATTERN_DELETE_PROGRESS);
        index = Integer.parseInt(matcher.group(Parser.TAG_INDEX)) - 1;
    }
    
    public CommandDeleteProgress(int index, String projectName) {
        this.index = index;
        this.projectName = projectName;
    }

    @Override
    public String execute(State state) throws Exception {
        Displayable currentDisplay = state.getCurrentDisplay();
        Projects projectHandler = state.getProjectHandler();
        
        if (projectName == null) {
            if (currentDisplay instanceof CommandViewProjectName) {
                projectName = ((CommandViewProjectName) currentDisplay).getProjectName();
            } else {
                throw new Exception(CommonHelper.ERROR_INDEX_OUT_OF_BOUND);
            }
        }
        
        if (projectHandler.deleteProgressMessage(index, projectName)) {
            return CommonHelper.SUCCESS_PROGRESS_DELETED;
        } else {
            throw new Exception(CommonHelper.ERROR_FAIL_DEL_PROGRESS);
        }
    }

    @Override
    public String revert(State state)
            throws Exception {
        Command revertDeleteProgressCommand = new CommandAddProgress(index, projectName, "");
        return revertDeleteProgressCommand.execute(state);
 
    }

    @Override
    public Displayable getDisplayable() {
        return null;
    }

}
