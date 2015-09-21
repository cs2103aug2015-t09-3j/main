
public class TestProject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Rmb to del file in main proj b4 running
		
		ProjectHandler test = new ProjectHandler();
		Event pseudoEvent = new Event("eatpieday", "20 November 2015", "08:00", "18:00", "additional info la");
		Event pseudoEvent2 = new Event("eatpieday2", "21 November 2015", "08:00", "18:00", "additional info la");
		
		System.out.println(test.createProject("projectdiva3"));
		System.out.println(test.deleteProject("projectdiva3"));
		System.out.println(test.createProject("projectdiva2"));
		//System.out.println(test.createProject("projectdiva2"));
		System.out.println(test.addProjectEvent(pseudoEvent, "projectdiva2"));
		System.out.println(test.addProjectEvent(pseudoEvent2, "projectdiva2"));
		//System.out.println(test.addProjectEvent(pseudoEvent, "projectdiva3"));
		System.out.println(test.viewProjectTimeline("projectdiva2"));
		System.out.println();
		System.out.println(test.viewProjectTimeline(2));
		System.out.println(test.viewProjectTimeline(1));
		
		// Open file to check edited, lazy to view again. Too much scrolling involved.
		System.out.println(test.editProjectEvent(1, 1, "myProjectDiva","projectdiva2"));
		test.editProjectEvent(1, 2, "21 October 2015", "projectdiva2");
		test.editProjectEvent(1, 3, "22 November 2015", "projectdiva2");
		test.editProjectEvent(1, 4, "09:00", "projectdiva2");
		test.editProjectEvent(1, 5, "20:00", "projectdiva2");
		test.editProjectEvent(1, 6, "no more info", "projectdiva2");
		
	}

}