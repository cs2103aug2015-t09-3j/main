import java.util.Calendar;

/**
 * Assumption is that the time and date format goes as follow:
 * 1) date: dd mm yyyy
 * 2) time: hhmm
 * 
 */

public class Event extends Item {
  
  private Calendar start;
  private Calendar end;
  
  /*********************************** Constructor ******************************************/
  
  public Event(String name, String date, String startTime, String endTime, String additionalInfo){
    this(name, date, date, startTime, endTime, additionalInfo);
  }
  
  public Event(String name, String startDate, String endDate, String startTime, String endTime, String additionalInfo ){
    super(name, additionalInfo);
    
    start = Calendar.getInstance();
    end = Calendar.getInstance();
    
    Helper.updateDate(start, startDate);
    Helper.updateTime(start, startTime);
    Helper.updateDate(end, endDate);
    Helper.updateTime(end, endTime);
    
  }
  
  /*********************************** Accessors ********************************************/
  
  public Calendar getStartCalendar(){
      return start;
  }
  
  public Calendar getEndCalendar(){
        return end;
    }
  
  public String getStartDateString() {
      return Helper.getDateString(start);
  }
  
  public String getEndDateString() {
      return Helper.getDateString(end);
  }
  
  public String getStartTimeString() {
      return Helper.getTimeString(start);
  }
    
  public String getEndTimeString() {
	  return Helper.getTimeString(end);
  }
  
/**************************************  Mutators ********************************************/
  
  public boolean updateStartDate(String dateString) {
        return Helper.updateDate(start, dateString);
    }
    
    public boolean updateStartTime(String timeString) {
        return Helper.updateTime(start, timeString);
    }
    
    public boolean updateEndDate(String dateString) {
        return Helper.updateDate(end, dateString);
    }
    
    public boolean updateEndTime(String timeString) {
        return Helper.updateTime(end, timeString);
    }

/*********************************** Overriding Methods ***********************************/
  
  public String toString(){
    return  getName() + "\n" 
        + getAdditionalInfo() + "\n" 
        + getStartDateString() + "\n" 
        + getEndDateString() + "\n" 
        + getStartTimeString() + "\n" 
        + getEndTimeString();
  }


}