package service;
import helper.Helper;

import java.util.ArrayList;

import object.Event;
import object.Todo;
import storage.FileHandler;


public class ServiceHandler implements ServiceManager{
    private FileHandler eventHandler;
    private FileHandler taskHandler;
    
    public ServiceHandler (){
        eventHandler = new FileHandler();
        taskHandler = new FileHandler();
    }

    @Override
    public boolean createEvent(Event newEvent) {
        if (viewSpecificEvent(newEvent.getName()) != null) return false;
        return eventHandler.saveNewEventHandler(newEvent);
    }

    @Override
    public boolean createTask(Todo newTask) {
        if (viewSpecificTask(newTask.getName()) != null) return false;
        return taskHandler.saveNewTodoHandler(newTask);
    }

    @Override
    public ArrayList<Event> viewEventByDate(String date) {
        return eventHandler.retrieveEventByDate(date);
    }

    @Override
    public ArrayList<Todo> viewTaskByDate(String date) {
        return taskHandler.retrieveTodoByDate(date);
    }

    @Override
    public ArrayList<Todo> viewTaskNoDate() {
        return taskHandler.retrieveUniversalTodo();
    }

    @Override
    public boolean deleteEvent(String eventName) {
        ArrayList<Event> completeEventBook = eventHandler.retrieveEventsToDelete();
      
        for (Event event:completeEventBook){
            
            if (event.getName().toLowerCase().equals(eventName.toLowerCase())){
                completeEventBook.remove(event);
               
                return eventHandler.saveAll();
            }
        }
        return false;
    }

    @Override
    public boolean deleteTask(String taskName) {

        ArrayList<Todo> completeTodoList = taskHandler.retrieveTodoToDelete();      
        System.out.println(completeTodoList); /////////////////////////////////////////////////////////
        for (Todo task:completeTodoList){
            if (task.getName().toLowerCase().equals(taskName.toLowerCase())){
                completeTodoList.remove(task);
                System.out.println(completeTodoList); /////////////////////////////////////////////////////////
                return taskHandler.saveAll();
            }
        }
        return false;
    }

    @Override
    public void editEvent(String eventName, String fieldName, String newInputs) throws Exception {
    	Event _event;

    	switch(fieldName) {
    	//case edit eventName
    	case (Helper.FIELD_NAME): 
    		if (!eventName.equals(newInputs) && viewSpecificEvent(newInputs) != null) {
    			throw new Exception(Helper.ERROR_EDIT_DUPLICATE);
    		}
    	_event = findEvent(eventName);

    	if (_event == null) {
    		throw new Exception (String.format(Helper.ERROR_NOT_FOUND, eventName));
    	}
    	else {
    		_event.setName(newInputs);
    		eventHandler.saveAll();
    	}

    	// case edit startDate
    	case (Helper.FIELD_START + Helper.DATE_TYPE): 
    		_event = findEvent(eventName);

    	if (_event == null) {
    		throw new Exception (String.format(Helper.ERROR_NOT_FOUND, eventName));
    	}
    	else {
    		_event.updateStartDate(newInputs);
    		eventHandler.saveAll();
    	}
    	
    	//case edit endDate
    	case (Helper.FIELD_END + Helper.DATE_TYPE): 
    		_event = findEvent(eventName);

    	if (_event == null) {
    		throw new Exception (String.format(Helper.ERROR_NOT_FOUND, eventName));
    	}
    	else {
    		_event.updateEndDate(newInputs);
    		eventHandler.saveAll();
    	}
    	
    	//case edit startTime
    	case (Helper.FIELD_START + Helper.TIME_TYPE):
    		_event = findEvent(eventName);

    	if (_event == null) {
    		throw new Exception (String.format(Helper.ERROR_NOT_FOUND, eventName));
    	}
    	else {
    		_event.updateStartTime(newInputs);
    		eventHandler.saveAll();
    	}	
    	
    	//case endDate
    	case (Helper.FIELD_END + Helper.TIME_TYPE) : 
    		_event = findEvent(eventName);

    	if (_event == null) {
    		throw new Exception (String.format(Helper.ERROR_NOT_FOUND, eventName));
    	}
    	else {
    		_event.updateEndTime(newInputs);
    		eventHandler.saveAll();
    	}
    	
    	//case startDateTime
    	case (Helper.FIELD_START + Helper.DATE_TIME_TYPE): 
    		_event = findEvent(eventName);

    	if (_event == null) {
    		throw new Exception (String.format(Helper.ERROR_NOT_FOUND, eventName));
    	}
    	else {
    		_event.updateStartDateTime(newInputs);
    		eventHandler.saveAll();
    	}	

    	//case edit eventEndDateTime
    	default : 
    		_event = findEvent(eventName);

    		if (_event == null) {
    			throw new Exception (String.format(Helper.ERROR_NOT_FOUND, eventName));
    		}
    		else {
    			_event.updateEndDateTime(newInputs);
    			eventHandler.saveAll();
    		}	
    	}
    }

    
    @Override
    public boolean editEventName(String eventName, String newEventName) {
    	if (!eventName.equals(newEventName) && viewSpecificEvent(newEventName) != null) return false;
    	int eventIndex = 0;
    	ArrayList<Event> completeEventBook = eventHandler.retrieveEventsToDelete();
    	for (Event event:completeEventBook){
    		if (event.getName().toLowerCase().equals(eventName.toLowerCase())){
    			Event _event = completeEventBook.get(eventIndex);
    			_event.setName(newEventName);
    			return eventHandler.saveAll();
    		}
    		else {
    			eventIndex++; // finding index with same name as eventName passed in
    		}
    	}
    	return false;
    }
    
    @Override
    public boolean editEventStartDate(String eventName, String newStartDate) {
        int eventIndex = 0;
        ArrayList<Event> completeEventBook = eventHandler.retrieveEventsToDelete();
        for (Event event:completeEventBook){
            if (event.getName().toLowerCase().equals(eventName.toLowerCase())){
                Event _event = completeEventBook.get(eventIndex);
                _event.updateStartDate(newStartDate);
                return eventHandler.saveAll(); 
            }
            else {
                eventIndex++; // finding index with same name as eventName passed in
            }
        }
            return false;
    }
    
    @Override
    public boolean editEventEndDate(String eventName, String newEndDate) {
        int eventIndex = 0;
        ArrayList<Event> completeEventBook = eventHandler.retrieveEventsToDelete();
        for (Event event:completeEventBook){
            if (event.getName().toLowerCase().equals(eventName.toLowerCase())){
                Event _event = completeEventBook.get(eventIndex);
                _event.updateEndDate(newEndDate);
                return eventHandler.saveAll(); 
            }
            else {
                eventIndex++; // finding index with same name as eventName passed in
            }
        }
            return false;
    }
    
    @Override
    public boolean editEventStartTime(String eventName, String newStartTime) {
        int eventIndex = 0;
        ArrayList<Event> completeEventBook = eventHandler.retrieveEventsToDelete();
        for (Event event:completeEventBook){
            if (event.getName().toLowerCase().equals(eventName.toLowerCase())){
                Event _event = completeEventBook.get(eventIndex);
                _event.updateStartTime(newStartTime);
                return eventHandler.saveAll(); 
            }
            else {
                eventIndex++; // finding index with same name as eventName passed in
            }
        }
            return false;
    }
    
    @Override
    public boolean editEventEndTime(String eventName, String newEndTime) {
        int eventIndex = 0;
        ArrayList<Event> completeEventBook = eventHandler.retrieveEventsToDelete();
        for (Event event:completeEventBook){
            if (event.getName().toLowerCase().equals(eventName.toLowerCase())){
                Event _event = completeEventBook.get(eventIndex);
                _event.updateEndTime(newEndTime);
                return eventHandler.saveAll(); 
            }
            else {
                eventIndex++; // finding index with same name as eventName passed in
            }
        }
            return false;
    }
    
    @Override
    public boolean editEventStartDateTime(String eventName, String newStartDateTime){
        int eventIndex = 0;
        ArrayList<Event> completeEventBook = eventHandler.retrieveEventsToDelete();
        for (Event event:completeEventBook){
            if (event.getName().toLowerCase().equals(eventName.toLowerCase())){
                Event _event = completeEventBook.get(eventIndex);
                _event.updateStartDateTime(newStartDateTime);
                return eventHandler.saveAll(); 
            }
            else {
                eventIndex++; // finding index with same name as eventName passed in
            }
        }
            return false;
    }
    
    @Override
    public boolean editEventEndDateTime(String eventName, String newEndDateTime){
        int eventIndex = 0;
        ArrayList<Event> completeEventBook = eventHandler.retrieveEventsToDelete();
        for (Event event:completeEventBook){
            if (event.getName().toLowerCase().equals(eventName.toLowerCase())){
                Event _event = completeEventBook.get(eventIndex);
                _event.updateEndDateTime(newEndDateTime);
                return eventHandler.saveAll(); 
            }
            else {
                eventIndex++; // finding index with same name as eventName passed in
            }
        }
            return false;
    }
    
    @Override
    public void editTask(String taskName, String fieldName, String newInputs) throws Exception{
    	Todo _task;
    	
    	switch(fieldName) {
    	//case edit taskName
    	case (Helper.FIELD_NAME): 
    		if (!taskName.equals(newInputs) && viewSpecificEvent(newInputs) != null) {
    			throw new Exception(Helper.ERROR_EDIT_DUPLICATE);
    		}
    	_task = findTask(taskName);

    	if (_task == null) {
    		throw new Exception (String.format(Helper.ERROR_NOT_FOUND, taskName));
    	}
    	else {
    		_task.setName(newInputs);
    		taskHandler.saveAll();
    	}
    	
    	//case edit deadlineDate
    	case (Helper.FIELD_END + Helper.DATE_TYPE):
    	_task = findTask(taskName);

    	if (_task == null) {
    		throw new Exception (String.format(Helper.ERROR_NOT_FOUND, taskName));
    	}
    	else {
    		_task.updateDeadlineDate(newInputs);
    		taskHandler.saveAll();
    	}
    	
    	//case edit deadlineTime
    	case (Helper.FIELD_END + Helper.TIME_TYPE):
    		_task = findTask(taskName);

    	if (_task == null) {
    		throw new Exception (String.format(Helper.ERROR_NOT_FOUND, taskName));
    	}
    	else {
    		_task.updateDeadlineTime(newInputs);
    		taskHandler.saveAll();
    	}

    	//case edit deadlineDateTime
    	default :
    		_task = findTask(taskName);

    		if (_task == null) {
    			throw new Exception (String.format(Helper.ERROR_NOT_FOUND, taskName));
    		}
    		else {
    			_task.updateDeadlineDateTime(newInputs);
    			taskHandler.saveAll();
    		}
    	}
    }
    
    @Override
    public boolean editTaskName(String taskName, String newTaskName) {
        if (!taskName.equals(newTaskName) && viewSpecificTask(newTaskName) != null) return false;
        int taskIndex = 0;
        ArrayList<Todo> completeTaskBook = taskHandler.retrieveTodoToDelete();
        for (Todo task:completeTaskBook){ //finding task with deadline
            if (task.getName().toLowerCase().equals(taskName.toLowerCase())){
                Todo _task = completeTaskBook.get(taskIndex);
                _task.setName(newTaskName);
                return taskHandler.saveAll(); 
            }
            else {
                taskIndex++; // finding index with same name as taskName passed in
            }
        }
        return false; //task not found
    }
    
    @Override
    public boolean editTaskDeadlineDate(String taskName, String newDeadlineDate) {
        int taskIndex = 0;
        ArrayList<Todo> completeTaskBook = taskHandler.retrieveTodoToDelete();
        for (Todo task:completeTaskBook){
            if (task.getName().toLowerCase().equals(taskName.toLowerCase())){
                Todo _task = completeTaskBook.get(taskIndex);
                _task.updateDeadlineDate(newDeadlineDate);
                return taskHandler.saveAll(); 
            }
            else {
                taskIndex++; // finding index with same name as taskName passed in
            }
        }
            return false;
    }
    
    @Override
    public boolean editTaskDeadlineTime(String taskName, String newDeadlineTime) {
        int taskIndex = 0;
        ArrayList<Todo> completeTaskBook = taskHandler.retrieveTodoToDelete();
        for (Todo task:completeTaskBook){
            if (task.getName().toLowerCase().equals(taskName.toLowerCase())){
                Todo _task = completeTaskBook.get(taskIndex);
                _task.updateDeadlineTime(newDeadlineTime);
                return taskHandler.saveAll(); 
            }
            else {
                taskIndex++; // finding index with same name as taskName passed in
            }
        }
            return false;
    }
    
    @Override
    public boolean editTaskDeadlineDateTime(String taskName, String newDeadlineDateTime){
        int taskIndex = 0;
        ArrayList<Todo> completeTaskBook = taskHandler.retrieveTodoToDelete();
        for (Todo task:completeTaskBook){
            if (task.getName().toLowerCase().equals(taskName.toLowerCase())){
                Todo _task = completeTaskBook.get(taskIndex);
                _task.updateDeadlineDateTime(newDeadlineDateTime);
                return taskHandler.saveAll(); 
            }
            else {
                taskIndex++; // finding index with same name as taskName passed in
            }
        }
            return false;
    }

    
    @Override
    public Event viewSpecificEvent (String eventName) {
        ArrayList<Event> completeEventBook = eventHandler.retrieveEventsToDelete();
        int eventIndex = 0;
        for (Event event:completeEventBook){
            if (event.getName().equals(eventName)){
                Event _event = completeEventBook.get(eventIndex);
                return _event;
            }
            else {
                eventIndex++;
            }
    }
        return null; //@Stef returns null if no task found with same name as eventName passed in
    }
    
    public Todo viewSpecificTask(String taskName){
        ArrayList<Todo> completeTaskBook = taskHandler.retrieveTodoToDelete();
        int taskIndex = 0;
        for (Todo task:completeTaskBook){ //checking taskbook with deadline
            if (task.getName().equals(taskName)){
                Todo _task = completeTaskBook.get(taskIndex);
                return _task;
            }
            else{
                taskIndex++;
            }
        }
        return null; //@Stef returns null if no task found with same name as taskName passed in
    }
    
    private Event findEvent(String eventName) {
    	int eventIndex = 0;
    	ArrayList<Event> completeEventBook = eventHandler.retrieveEventsToDelete();
    	
    	for (Event event:completeEventBook){
            if (event.getName().toLowerCase().equals(eventName.toLowerCase())){
                return completeEventBook.get(eventIndex);
            }
            else{
            	eventIndex++;
            }
    	}
    	return null;
    }

    
    private Todo findTask(String taskName) {
    	int taskIndex = 0;
        ArrayList<Todo> completeTaskBook = taskHandler.retrieveTodoToDelete();
        
        for (Todo task:completeTaskBook){
            if (task.getName().toLowerCase().equals(taskName.toLowerCase())){
                return completeTaskBook.get(taskIndex);
            }
            else {
                taskIndex++; // finding index with same name as taskName passed in
            }
        }
            return null;
    }
   
    
}