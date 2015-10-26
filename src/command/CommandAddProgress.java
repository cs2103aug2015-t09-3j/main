package command;

import java.util.regex.Matcher;

import helper.Parser;
import project.Projects;
import service.ServiceHandler;

public class CommandAddProgress implements CommandAdd {

    int index;
    String progress;
    String projectName;
    
    public CommandAddProgress(String args) {
        
        Matcher matcher = Parser.matchRegex(args, Parser.PATTERN_ADD_PROGRESS);
        
        progress = matcher.group(Parser.TAG_PROGRESS);
        index = Integer.parseInt(matcher.group(Parser.TAG_INDEX));
        projectName = matcher.group(Parser.TAG_NAME);
    }

    @Override
    public String execute(ServiceHandler serviceHandler, Projects projectHandler, Revertible mostRecent, Displayable currentDisplay)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String revert(ServiceHandler serviceHandler, Projects projectHandler, Displayable currentDisplay)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Displayable getDisplayable() {
        // TODO Auto-generated method stub
        return null;
    }

}
