package crudLogic;
import objects.Event;
import objects.Todo;
import storage.FileHandler;

public class TestServiceHandler {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ServiceHandler test = new ServiceHandler();
        //Event testEvent = new Event("study", "12 mar 2020", "08:00 ", "18:00", "");
        Event testEvent1 = new Event("study 2020", "12 mar 2020", "18:00 ", "19:00", "");
        
        //System.out.println(test.createEvent(testEvent)); 
        System.out.println(test.createEvent(testEvent1));
        System.out.println(test.viewEventByDate("12 mar 2020"));
        //System.out.println(test.deleteEvent("study"));
        System.out.println(test.editEventName("study 2020", "play"));
        System.out.println(test.editEventStartDate("play", "13 mar 2020"));
        System.out.println(test.editEventStartTime("play", "09:00"));
        System.out.println(test.editEventEndTime("play", "09:01"));
        System.out.println(test.viewEventByDate("12 mar 2020"));
        System.out.println(test.viewEventByDate("13 mar 2020"));
        
        
        /******************* Test Delete Event ********************************/
        // this is to clear all the directories and txt files.
        FileHandler clear = new FileHandler();
        clear.clearAll();
        new FileHandler();
        
        //create events
		Event event1 = new Event("sleep", "31 aug 2016", "31 aug 2016", "03:00", "10:00", "lean on the left");
		Event event2 = new Event("Project", "24 aug 2016", "31 aug 2016", "20:00", "02:00", "chiong ah");
		
		//save events
		test.createEvent(event1);
		test.createEvent(event2);
		System.out.println ("Events added");
		
		// check if all the events are saved
		System.out.println(test.viewEventByDate("31 aug 2016"));
		System.out.println();
		
		//delete one event: "Project"
		System.out.println("Delete Project");
		test.deleteEvent("Project");
		
		// check: "Project" should be gone
		System.out.println(test.viewEventByDate("31 aug 2016"));
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		/******************* Test Delete Event End ********************************/
		
		//create task
		Todo todo1 = new Todo("What is shit", "It is what I eat", "20 oct 2016");
		Todo todo2 = new Todo("Watch Pony On Red Nails", "Best one", "20 oct 2016");
		
		//save task
		test.createTask(todo1);
		test.createTask(todo2);
		System.out.println ("Tasks added");
		
		// Check if tasks are added
		System.out.println(test.viewTaskByDate("20 oct 2016"));
		System.out.println();
		
		//delete one task: What is shit
		System.out.println("Delete What is shit");
		test.deleteEvent("Dinner with Tim");
		test.deleteTask("What is shit");
		
		// check: "What is shit" should be gone
		System.out.println(test.viewTaskByDate("20 oct 2016"));
		System.out.println();
		
		
		/******************* Test Delete Todo End ********************************/

       
		
		
		
		
		
		
        
/*      System.out.println(test.createTask(testTask1));
        System.out.println(test.viewTaskNoDate("12 March 2020"));
        System.out.println(test.deleteTaskWithoutDeadline("study"));
        */
    }

}