package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import beans.User;

public class DataService {

    //Delimiter used in CSV file
    private static final String DELIMITER = "\\|";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String DATA_FILE_NAME = "data.dat";
    //CSV file header
    private static final String FILE_HEADER = "firstName|lastName|email|address|phone|password";	
    
    private static final int FIRSTNAME = 0;
    private static final int LASTNAME = 1;
    private static final int EMAIL = 2;
    private static final int ADDRESS = 3;
    private static final int PHONE = 4;
    private static final int PASSWORD = 5;
    
    
	/**
	 * this saves the user on registration
	 * @param user
	 */
	public void save(User user) {
        FileWriter fileWriter = null;
        
        String fileToWrite = new File(".").getAbsolutePath() +"\\"+ DATA_FILE_NAME;
        System.out.println("fileToWrite: " + fileToWrite);
        
        
        try {
        	
            
            fileWriter = new FileWriter(fileToWrite);
 
            //Write the CSV file header
            fileWriter.append(FILE_HEADER.toString());
             
            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);
             
             
             fileWriter.append(user.getFirstName());
             fileWriter.append(DELIMITER);
             fileWriter.append(user.getLastName());
             fileWriter.append(DELIMITER);
             fileWriter.append(user.getEmail());
             fileWriter.append(DELIMITER);
             fileWriter.append(user.getAddress());
             fileWriter.append(DELIMITER);
             fileWriter.append(user.getPhone());
             fileWriter.append(DELIMITER);
             fileWriter.append(user.getPassword());
             fileWriter.append(NEW_LINE_SEPARATOR);
            
             
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
             
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
             
        }	
       
	}
	
	
	/**
	 * this validates the user when they login
	 * @param user
	 * @return
	 */
	public boolean validate(User user) {
		
		
		boolean f = false;
		
		for(User userToCheck: LoadUserList()) {
			
			System.out.println("email: " + userToCheck.getEmail() + " -- " + user.getEmail() + " password: " + userToCheck.getPassword() + " -- " + user.getPassword());
			
			if(userToCheck.getEmail().compareTo(user.getEmail()) == 0 &&
					userToCheck.getPassword().compareTo(user.getPassword()) == 0)
				f = true;
		}
		
		System.out.println("Found: " +  f);
		
		return f;
		
		
	}
	
	/**
	 * this will load the saved users
	 * @return
	 */
	public List<User> LoadUserList() {
		
		List<User> users = new ArrayList<User>();
		
		
		BufferedReader fileReader = null;
	      
        try {
             
             
            String line = "";
             
            //Create the file reader
            String fileToRead = new File(".").getAbsolutePath() +"\\"+ DATA_FILE_NAME;
            System.out.println("fileToRead: " + fileToRead);
            fileReader = new BufferedReader(new FileReader(fileToRead));
             
            //Read the CSV file header to skip it
            fileReader.readLine();
             
            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(DELIMITER);
                
                for(String s : tokens) {
                	System.out.println(s);
                }
                
                if (tokens.length > 0) {

                	User u = new User();
                    u.setFirstName(tokens[FIRSTNAME]);
                    u.setLastName(tokens[LASTNAME]);
                    u.setEmail(tokens[EMAIL]);;
                    u.setAddress(tokens[ADDRESS]);
                    u.setPassword(tokens[PASSWORD]);
                    u.setPhone(tokens[PHONE]);
                    
                    users.add(u);
                }
            }

        } 
        catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }		
		
        return users;
	}
	
}
