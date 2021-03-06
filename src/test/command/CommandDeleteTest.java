//@@author A0126509E

package test.command;

import static org.junit.Assert.*;

import org.junit.Test;

import command.CommandDeleteItem;
import helper.CommonHelper;

public class CommandDeleteTest extends CommandTest {
    
    /* execution test for delete command */
    
    @Test
    public void testCanDeleteEvent() throws Exception {
        String name = "eat again";
        String startDateTime = "21 Sep 2016 10:00";
        String endDateTime = "22 Sep 2016 23:00";
        addEvent(name, startDateTime, endDateTime);
        
        CommandDeleteItem cmd = new CommandDeleteItem("\"" + name + "\"");
        
        // new event exists on service
        assertNotNull(state.getServiceHandler().viewSpecificEvent(name));
        
        String feedback = cmd.execute(state);

        // successfully deleted
        assertEquals(feedback, String.format(CommonHelper.SUCCESS_ITEM_DELETED, name));
        

        // new event removed from the service
        assertNull(state.getServiceHandler().viewSpecificEvent(name));
        
    }
    
    @Test
    public void testCanDeleteTodo() throws Exception {
        String name = "eat again";
        String deadlineDateTime = "21 Sep 2016 10:00";
        addTodo(name, deadlineDateTime);
        
        CommandDeleteItem cmd = new CommandDeleteItem("\"" + name + "\"");
        
        // new todo exists on service
        assertNotNull(state.getServiceHandler().viewSpecificTask(name));
        
        String feedback = cmd.execute(state);

        // successfully deleted
        assertEquals(feedback, String.format(CommonHelper.SUCCESS_ITEM_DELETED, name));
        

        // new todo removed from the service
        assertNull(state.getServiceHandler().viewSpecificTask(name));
    }
    
    @Test
    public void testCanDeleteFloatingTodo() throws Exception {
        String name = "eat again";
        addTodo(name);
        
        CommandDeleteItem cmd = new CommandDeleteItem("\"" + name + "\"");
        
        // new todo exists on service
        assertNotNull(state.getServiceHandler().viewSpecificTask(name));
        
        String feedback = cmd.execute(state);

        // successfully deleted
        assertEquals(feedback, String.format(CommonHelper.SUCCESS_ITEM_DELETED, name));
        

        // new todo removed from the service
        assertNull(state.getServiceHandler().viewSpecificTask(name));
    }
}
