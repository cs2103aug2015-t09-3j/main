package test.command;

import static org.junit.Assert.*;

import org.junit.Test;

import command.CommandEditItem;
import helper.CommonHelper;
import object.Event;
import object.Todo;

public class CommandEditTest extends CommandTest {

    
    /* execution test for edit command */
    
    @Test
    public void testCanEditEvent() throws Exception {
        String name = "eat again";
        String startDateTime = "21 Sep 2016 10:00";
        String endDateTime = "22 Sep 2016 23:00";
        addEvent(name, startDateTime, endDateTime);
        Event event = null;
        
        // test can edit start date
        new CommandEditItem(name, CommonHelper.FIELD_START, "19 Jan 2016").execute(service, project, getMostRecentRevertible(), null);
        event = service.viewSpecificEvent(name);
        assertEquals(event.getStartDateTimeString(), "19 Jan 2016 10:00");
        
        // test can edit start time
        new CommandEditItem(name, CommonHelper.FIELD_START, "13:00").execute(service, project, getMostRecentRevertible(), null);
        event = service.viewSpecificEvent(name);
        assertEquals(event.getStartDateTimeString(), "19 Jan 2016 13:00");
        
        // test can edit start date and time
        new CommandEditItem(name, CommonHelper.FIELD_START, "20 Sep 2016 13:00").execute(service, project, getMostRecentRevertible(), null);
        event = service.viewSpecificEvent(name);
        assertEquals(event.getStartDateTimeString(), "20 Sep 2016 13:00");
        
        // test can edit end date
        new CommandEditItem(name, CommonHelper.FIELD_END, "19 Dec 2016").execute(service, project, getMostRecentRevertible(), null);
        event = service.viewSpecificEvent(name);
        assertEquals(event.getEndDateTimeString(), "19 Dec 2016 23:00");
        
        // test can edit end time
        new CommandEditItem(name, CommonHelper.FIELD_END, "13:00").execute(service, project, getMostRecentRevertible(), null);
        event = service.viewSpecificEvent(name);
        assertEquals(event.getEndDateTimeString(), "19 Dec 2016 13:00");
        
        // test can edit end date and time
        new CommandEditItem(name, CommonHelper.FIELD_END, "20 Nov 2016 15:00").execute(service, project, getMostRecentRevertible(), null);
        event = service.viewSpecificEvent(name);
        assertEquals(event.getEndDateTimeString(), "20 Nov 2016 15:00");
        
        // test can edit name
        new CommandEditItem(name, CommonHelper.FIELD_NAME, "eat meh").execute(service, project, getMostRecentRevertible(), null);
        assertNull(service.viewSpecificEvent(name));
        assertNotNull(service.viewSpecificEvent("eat meh"));
        
    }
    
    @Test
    public void testCanEditTodo() throws Exception {
        String name = "eat again lho";
        String deadlineDateTime = "21 Sep 2016 10:00";
        addTodo(name, deadlineDateTime);
        Todo todo = null;
        
        // test can edit due date
        new CommandEditItem(name, CommonHelper.FIELD_DUE, "19 Dec 2016").execute(service, project, getMostRecentRevertible(), null);
        todo = service.viewSpecificTask(name);
        assertEquals(todo.getDeadlineDateTimeString(), "19 Dec 2016 10:00");
        
        // test can edit due time
        new CommandEditItem(name, CommonHelper.FIELD_DUE, "13:00").execute(service, project, getMostRecentRevertible(), null);
        todo = service.viewSpecificTask(name);
        assertEquals(todo.getDeadlineDateTimeString(), "19 Dec 2016 13:00");
        
        // test can edit due date and time
        new CommandEditItem(name, CommonHelper.FIELD_DUE, "20 Nov 2016 13:00").execute(service, project, getMostRecentRevertible(), null);
        todo = service.viewSpecificTask(name);
        assertEquals(todo.getDeadlineDateTimeString(), "20 Nov 2016 13:00");
        
        // test can edit name
        new CommandEditItem(name, CommonHelper.FIELD_NAME, "eat meh meh").execute(service, project, getMostRecentRevertible(), null);
        assertNull(service.viewSpecificTask(name));
        assertNotNull(service.viewSpecificTask("eat meh meh"));
    }
    
    @Test
    public void testCanEditFloatingTodo() throws Exception {
        String name = "eat again lho";
        addTodo(name);
        
        new CommandEditItem(name, CommonHelper.FIELD_NAME, "eat meh meh").execute(service, project, getMostRecentRevertible(), null);
        assertNull(service.viewSpecificTask(name));
        assertNotNull(service.viewSpecificTask("eat meh meh"));
    }
    
   

}
