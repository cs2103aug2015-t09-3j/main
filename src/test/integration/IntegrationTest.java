//@@author A0122432X

package test.integration;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import helper.CommonHelper;
import object.Item;
import storage.FileHandler;
import ui.GUI;

public class IntegrationTest {
	 protected static GUI gui;
	 
	 private static final String SEARCHKEYWORDONE = "search \"1\"";
	 private static final String SEARCHKEYWORDTASK = "search \"task\"";
	 
	 private static final String INVALIDONE = "hello";
	 private static final String INVALIDTWO = "20 Jan 2016";
	 private static final String FIRSTWORDINVALIDLINE = "20";
	 private static final String INVALIDTHREE = "add \"hahaha\" from 20 Sep 2016 23:00 to 11 Sep 2015 07:00"; // start date before end date
	 
	 private static final String EVENTONE = "add \"event1\" from 10 Sep 2016 23:00 to 11 Sep 2016 07:00"; // event with duration going into the next day
	 private static final String EVENTTWO = "add \"event2\" from 22 Sep 2016 10:00 to 11:30";// event on the day itself
	 private static final String EVENTTHREE = "add \"event3 with jack and jill\" from 20 Sep 2016 10:00 to 11:30";// event with spaces in name
	 
	 private static final String TASKONE = "add \"task1\" by 22 Sep 2016 18:00";// normal task
	 private static final String TASKTWO = "add \"task2 with jack and jill\" by 22 Sep 2016 23:00";// task with spaces in name
	 
	 private static final String FLOATINGONE = "add \"floating1Task\"";// normal floating task
	 private static final String FLOATINGTWO = "add \"floating2Task Singapore alone\"";// floating task with spaces in name
	 
	 private static final String DELETEINDEXNEGATIVETHREE = "delete -3"; //negative number
	 private static final String DELETEINDEXZERO = "delete 0"; //zero
	 private static final String DELETEINDEXONE = "delete 1"; // one
	 private static final String DELETEINDEXTWO = "delete 2"; //two
	 
	 private static final String DELETEKEYWORDONE = "delete \"1\"";
	 private static final String DELETEKEYWORDTASK = "delete \"task\"";
	 
	 private static final String CHECKONE = "check \"1\"";
	 private static final String CHECKNEGATIVEONE = "check \"-1\"";
	 
	 private static final String UNCHECKONE = "uncheck \"1\"";
	 
	 private static final String VIEWDATE = "view 22 sep 2016";
	 
	 private static final String UNDO = "undo";
	 
	 private static final String REDO = "redo";
	 
	 private static final String VIEWPROJECT = "view project";
	 private static final String PROJECT = "view \"123456\" ";
	 private static final String ADDPROJECT = "add project \"123456\"";
	 
    @Before
    public void setUp() throws Exception {
        FileHandler clear = new FileHandler();
        clear.clearAll();
        Item.setCounter(0);
        gui = new GUI();
        gui.initiateState(true);
    }

    @Test
    public void testAddItemsInvalid() {
        assertEquals("Invalid1 success" ,String.format(CommonHelper.ERROR_INVALID_COMMAND, INVALIDONE),gui.executeResponse(INVALIDONE));
        assertEquals("Invalid2 success" ,String.format(CommonHelper.ERROR_INVALID_COMMAND, FIRSTWORDINVALIDLINE),gui.executeResponse(INVALIDTWO));
        assertEquals("Invalid3 success" ,String.format(CommonHelper.ERROR_START_AFTER_END),gui.executeResponse(INVALIDTHREE));
    }
    
    @Test
    public void testAddItemsValid() {
        assertEquals("Add event1 success", String.format(CommonHelper.SUCCESS_ITEM_CREATED, "event1"),gui.executeResponse(EVENTONE));
        assertEquals("Add event2 success", String.format(CommonHelper.SUCCESS_ITEM_CREATED, "event2"),gui.executeResponse(EVENTTWO));
        assertEquals("Add event3 success", String.format(CommonHelper.SUCCESS_ITEM_CREATED, "event3 with jack and jill"),gui.executeResponse(EVENTTHREE));
        assertEquals("Add task1 success", String.format(CommonHelper.SUCCESS_ITEM_CREATED, "task1"),gui.executeResponse(TASKONE));
        assertEquals("Add task2 success", String.format(CommonHelper.SUCCESS_ITEM_CREATED, "task2 with jack and jill"),gui.executeResponse(TASKTWO));
        assertEquals("Add floating1 success", String.format(CommonHelper.SUCCESS_ITEM_CREATED, "floating1Task"),gui.executeResponse(FLOATINGONE));
        assertEquals("Add floating2 success", String.format(CommonHelper.SUCCESS_ITEM_CREATED, "floating2Task Singapore alone"),gui.executeResponse(FLOATINGTWO));
    }

    @Test
    public void testDeleteIndexInvalid() {
    	//check if want ERROR_INVALID_ARGUMENTS or ERROR_INDEX_OUT_OF_BOUND for negative numbers
    	assertEquals("Delete -3 invalid success", String.format(CommonHelper.ERROR_INVALID_ARGUMENTS,"delete"),gui.executeResponse(DELETEINDEXNEGATIVETHREE)); 
    	
    	assertEquals("Delete 0 invalid success", String.format(CommonHelper.ERROR_INDEX_OUT_OF_BOUND),gui.executeResponse(DELETEINDEXZERO));
    	assertEquals("Delete 2 invalid success", String.format(CommonHelper.ERROR_INDEX_OUT_OF_BOUND),gui.executeResponse(DELETEINDEXTWO)); // index does not exist
    }
    
    @Test
    public void testKeywordInvalid() {
    	assertEquals("Delete keyword \"1\" invalid success", String.format(CommonHelper.ERROR_ITEM_NOT_FOUND, "1"),gui.executeResponse(DELETEKEYWORDONE)); //keyword does not exist   	
    	assertEquals("Delete keyword \"task\" invalid success", String.format(CommonHelper.ERROR_ITEM_NOT_FOUND, "task"),gui.executeResponse(DELETEKEYWORDTASK)); // keyword does not exist
    }
    
    @Test
    public void testDeleteKeywordValid() {
    	gui.executeResponse(EVENTONE);
    	gui.executeResponse(EVENTTHREE);
    	assertEquals("Delete '1' event valid success", String.format(CommonHelper.SUCCESS_ITEM_DELETED,"event1"),gui.executeResponse(DELETEKEYWORDONE)); //keyword '1' for events
    	gui.executeResponse(TASKONE);
    	assertEquals("Delete '1' task valid success", String.format(CommonHelper.SUCCESS_ITEM_DELETED,"task1"),gui.executeResponse(DELETEKEYWORDONE)); //keyword '1' for task
    	gui.executeResponse(TASKTWO);
    	assertEquals("Delete 'task' task valid success", String.format(CommonHelper.SUCCESS_ITEM_DELETED,"task2 with jack and jill"),gui.executeResponse(DELETEKEYWORDTASK)); //keyword 'task' for task
    	gui.executeResponse(FLOATINGTWO);
    	assertEquals("Delete 'task' floating task valid success", String.format(CommonHelper.SUCCESS_ITEM_DELETED,"floating2Task Singapore alone"),gui.executeResponse(DELETEKEYWORDTASK)); //keyword 'task' for floating task
    }
    
    @Test
    public void testDeleteIndexValid() {
    	gui.executeResponse(EVENTTHREE);
    	gui.executeResponse(FLOATINGTWO);
    	gui.executeResponse(TASKTWO);
    	gui.executeResponse(EVENTONE);
    	gui.executeResponse(TASKONE);
    	gui.executeResponse(FLOATINGONE);
    	
    	gui.executeResponse(SEARCHKEYWORDONE);
    	assertEquals("Delete 2 \"task1\" valid success", String.format(CommonHelper.SUCCESS_ITEM_DELETED,"task1"),gui.executeResponse(DELETEINDEXTWO));//delete task
    	assertEquals("Delete 2 \"event1\" valid success", String.format(CommonHelper.SUCCESS_ITEM_DELETED,"event1"),gui.executeResponse(DELETEINDEXONE));//delete event
    	
    	gui.executeResponse(SEARCHKEYWORDTASK);
    	assertEquals("Delete 2 \"floating2Task Singapore alone\" valid success", String.format(CommonHelper.SUCCESS_ITEM_DELETED,"floating2Task Singapore alone"),gui.executeResponse(DELETEINDEXTWO));//delete floating task
    }
    
    @Test
    public void testSearchInvaild() {
    	
    	assertEquals("Search keyword \"task\" invalid success", "", gui.executeResponse(SEARCHKEYWORDTASK));// no event or task added for search
    	gui.executeResponse(EVENTTHREE);
    	gui.executeResponse(FLOATINGTWO);
    	gui.executeResponse(TASKTWO);
    	assertEquals("Search keyword \"1\" invalid success", "", gui.executeResponse(SEARCHKEYWORDONE));// no event or task with "1" added for search
    }
    
    @Test
    public void testSearchVaild() {
    	
    	gui.executeResponse(EVENTTHREE);
    	gui.executeResponse(FLOATINGTWO);
    	gui.executeResponse(TASKTWO);
    	gui.executeResponse(EVENTONE);
    	gui.executeResponse(TASKONE);
    	gui.executeResponse(FLOATINGONE);
    	
    	assertEquals("Search keyword \"1\" valid success", "event1" + "\n" + "task1" + "\n" + "floating1Task" + "\n", gui.executeResponse(SEARCHKEYWORDONE));
    	assertEquals("Search keyword \"task\" valid success", "task1" + "\n" + "task2 with jack and jill" + "\n" + "floating2Task Singapore alone" + "\n" + "floating1Task" + "\n", gui.executeResponse(SEARCHKEYWORDTASK));
    }
    
    @Test
    public void testCheckInvaild() {
    	assertEquals("check \"1\" invalid success", String.format(CommonHelper.ERROR_ITEM_NOT_FOUND, "1"), gui.executeResponse(CHECKONE)); // no items added to be checked
    	assertEquals("check \"-1\" invalid success", String.format(CommonHelper.ERROR_ITEM_NOT_FOUND, "-1"), gui.executeResponse(CHECKNEGATIVEONE));//negative number
    	
    	gui.executeResponse(FLOATINGONE);
    	gui.executeResponse(CHECKONE);
    	assertEquals("check \"1\" invalid because checked item success", String.format(CommonHelper.ERROR_ALREADY_CHECKED, "floating1Task"), gui.executeResponse(CHECKONE)); // checked item being checked    	
    }
    
    @Test
    public void testCheckVaild() {   	
    	gui.executeResponse(EVENTONE);
    	
    	assertEquals("check \"1\" valid success", String.format(CommonHelper.SUCCESS_ITEM_CHECKED, "event1"), gui.executeResponse(CHECKONE));
    }
    
    @Test
    public void testUncheckVaild() {   	
    	gui.executeResponse(TASKONE);
    	gui.executeResponse(CHECKONE);
    	
    	assertEquals("uncheck \"1\" valid success", String.format(CommonHelper.SUCCESS_ITEM_UNCHECKED, "task1"), gui.executeResponse(UNCHECKONE));
    }
    
    @Test
    public void testUncheckInvaild() {  
    	assertEquals("uncheck \"1\" valid success", String.format(CommonHelper.ERROR_ITEM_NOT_FOUND, "1"), gui.executeResponse(UNCHECKONE)); // no item to uncheck
    	
    	gui.executeResponse(TASKONE);
    	assertEquals("uncheck \"1\" valid success", String.format(CommonHelper.ERROR_ALREADY_UNCHECKED, "task1"), gui.executeResponse(UNCHECKONE)); // item already unchecked
    }
    
    @Test
    public void testViewDateVaild() {
    	gui.executeResponse(TASKONE);
    	gui.executeResponse(TASKTWO);
    	gui.executeResponse(EVENTTWO);
    	gui.executeResponse(FLOATINGTWO);
    	
    	assertEquals("view date \"22 sep 2016\" valid success", "event2" + "\n" + "task1" + "\n" + "task2 with jack and jill" + "\n" + "\n" + "floating2Task Singapore alone" + "\n", gui.executeResponse(VIEWDATE));
    }
    
    @Test
    public void testUndoInvaild() {
    	assertEquals("undo invalid", CommonHelper.ERROR_EMPTY_UNDO,gui.executeResponse(UNDO)); //nothing to undo
    }
    
    @Test
    public void testUndoVaild() {
    	gui.executeResponse(EVENTONE);
    	assertEquals("undo add event 1 valid", String.format(CommonHelper.SUCCESS_ITEM_DELETED, "event1"),gui.executeResponse(UNDO)); //undo add event, deleting the event
    	
    	gui.executeResponse(FLOATINGTWO);
    	gui.executeResponse(DELETEKEYWORDTASK);
    	assertEquals("undo delete floating2Task Singapore alone valid", String.format(CommonHelper.SUCCESS_ITEM_CREATED, "floating2Task Singapore alone"),gui.executeResponse(UNDO)); //undo delete floating, adding the floating
    }
    
    @Test
    public void testRedoInvaild() {
    	assertEquals("redo invalid", CommonHelper.ERROR_EMPTY_REDO,gui.executeResponse(REDO)); //nothing to redo
    }
    
    @Test
    public void testRedoVaild() {
    	gui.executeResponse(EVENTONE);
    	gui.executeResponse(UNDO);
    	assertEquals("redo add event 1 valid", String.format(CommonHelper.SUCCESS_ITEM_CREATED, "event1"),gui.executeResponse(REDO)); //redo add event, adding a event
    	
    	gui.executeResponse(FLOATINGTWO);
    	gui.executeResponse(DELETEKEYWORDTASK);
    	gui.executeResponse(UNDO);
    	assertEquals("redo delete floating2Task Singapore alone valid", String.format(CommonHelper.SUCCESS_ITEM_DELETED, "floating2Task Singapore alone"),gui.executeResponse(REDO)); //redo delete floating, deleting a floating task
    }
    
    @Test
    public void testViewProjectNameInvaild() {
    	assertEquals("view project 123456 invalid", String.format(CommonHelper.ERROR_PROJECT_NOT_FOUND, "123456"),gui.executeResponse(PROJECT)); // no project created yet
    }
    
    @Test
    public void testViewProjectNameVaild() {
    	gui.executeResponse(ADDPROJECT);
    	assertEquals("view project 123456 Valid", "Got it!",gui.executeResponse(PROJECT)); 
    }
    
    @Test
    public void testViewProjectVaild() {
    	gui.executeResponse(ADDPROJECT);
    	assertEquals("view all projects Valid", "Got it!",gui.executeResponse(VIEWPROJECT)); 
    }
}