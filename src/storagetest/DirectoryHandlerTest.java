package storagetest;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import storage.DirectoryHandler;

public class DirectoryHandlerTest {

	DirectoryHandler dirHand = new DirectoryHandler();
	private static String newBaseDirectory;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Exiting, cleaning up folders");
		if(PreparationCleanUp.cleanUp(newBaseDirectory)){
			PreparationCleanUp.manualCleanUp();
			System.out.println("Clean up completed. bye");
		}
	}

	@Test
	public void testDirectoryHandler() {
		String baseDirectory = System.getProperty("user.dir").toString();
		File file = new File(baseDirectory + "/Event");
		assertEquals("Test with valid new directory",
				true, file.exists());
		
		file = new File(baseDirectory + "/Todo");
		assertEquals("Test with valid new directory",
				true, file.exists());
		
		file = new File(baseDirectory + "/Project");
		assertEquals("Test with valid new directory",
				true, file.exists());
	}

	@Test
	public void testSetNewBaseDirectory() {
		newBaseDirectory = null;
		assertEquals("Test change to null directory",
				false, dirHand.setNewBaseDirectory(newBaseDirectory));
		
		newBaseDirectory = "";
		assertEquals("Test change to empty directory",
				false, dirHand.setNewBaseDirectory(newBaseDirectory));
		
		newBaseDirectory = "non-existing";
		assertEquals("Test change to non-existing directory",
				false, dirHand.setNewBaseDirectory(newBaseDirectory));
		
		newBaseDirectory = System.getProperty("user.dir").toString() + "/alternateTestFiles";
		PreparationCleanUp.makeNewDirectory(newBaseDirectory);
		assertEquals("Test with valid new directory",
				true, dirHand.setNewBaseDirectory(newBaseDirectory));
		
		String baseDirectory = System.getProperty("user.dir").toString() + "/alternateTestFiles";
		File file = new File(baseDirectory + "/Event");
		assertEquals("Test with valid new directory",
				true, file.exists());
		
		file = new File(baseDirectory + "/Todo");
		assertEquals("Test with valid new directory",
				true, file.exists());
		
		file = new File(baseDirectory + "/Project");
		assertEquals("Test with valid new directory",
				true, file.exists());
		
	}
}
