//@@author A0130445R
package test.project;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import object.Event;
import object.Item;
import project.ProjectHandler;
import project.Projects;
import storage.FileHandler;

public class TestProjectHandler {

	protected static FileHandler clear;
	private ProjectHandler pH;
	private Projects project;
	
	// Make sure dates are in DD MMM YYY format 
	private Event event1;
	private Event event2;
	private Event event3;
	
	@Before
	public void setUpBeforeTesting() throws Exception {
		clear = new FileHandler();
		clear.clearAll();
		System.out.println("Files Cleared for ProjectHandler Class Testing");
		
		Item.setCounter(0);
	    event1 = new Event("EventName1", "25 Oct 2015", "26 Oct 2015", "10:00", "21:00", "");
	    event2 = new Event("EventName2", "21 Oct 2015", "31 Oct 2015", "19:00", "23:00", "");
	    event3 = new Event("EventName3", "21 Oct 2015", "30 Oct 2015", "17:00", "02:00", "");
	    pH = new ProjectHandler();
	    project = new Projects();
	    
	    clear.saveNewEventHandler(event1);
	    clear.saveNewEventHandler(event2);
	    clear.saveNewEventHandler(event3);
	}
	
	@After
	public void tearDownAfterTesting() throws Exception {
		clear = new FileHandler();
		clear.clearAll();
		System.out.println("Test Files used in ProjectHandler Testing Cleared");
	}
	
	@Test
	public void testAddViewEditDeleteProjectHandlerTimeline() throws AssertionError {
		try {
			
			project.createProject("helloAll");
			
			//Add
			assertTrue("Failed to add event to projecthandler by name", pH.addProjectEvent(event1, "helloAll"));
			
			//View
			Integer[] intArray = new Integer[]{0}; 
			assertArrayEquals("Failed to view projecthandler timeline by name", intArray, pH.viewProjectTimeline("helloAll").toArray());
			
			Event[] eventArray = new Event[]{event1}; 
			assertArrayEquals("Failed to view projecthandler event timeline by name", eventArray, pH.viewProjectTimelineInEvents("helloAll").toArray());
			
			//Pass to-be-edited Event to Parser to call editing in Event Class.
			assertEquals("Failed to edit Event projecthandler", event1, pH.editEvent(0, "helloAll"));
			
			//Delete
			assertTrue("Failed to delete Event by event, projectname in project handler", pH.deleteProjectEvent(event1, "helloAll"));
			assertFalse("Successfully deleted non-existent Event by event, projectname in project handler", pH.deleteProjectEvent(event3, "helloAll"));
			
			project.addProjectEvent(event3, "helloAll");
			assertTrue("Failed to delete Event by eventindex, projectname in project handler", pH.deleteProjectEvent(0, "helloAll"));
			assertFalse("Successfully deleted non-existent Event by eventindex, projectname in project handler", pH.deleteProjectEvent(1, "helloAll"));		
		
			project.createProject("new name");
			assertTrue("Failed to rename project", pH.editProjectName("new name", "helloall"));

		} catch (AssertionError AE) {
			System.out.println(AE.getMessage());
			throw AE;
		}
	}
	
}

