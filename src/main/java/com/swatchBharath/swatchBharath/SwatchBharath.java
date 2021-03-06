package com.swatchBharath.swatchBharath;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is the class which deals with the swatch Bharath use case 
 * @author Harish Penthala
 *
 */
public class SwatchBharath {
	static int failureCount = 0;
	static int successCount = 0;
	static int WeightOfPlastic = 0;
	static int WeightOfMetal = 0;
	static int WeightOfGlass = 0;
	static int totalRewardPoints = 0;
	static boolean submitagain = true;
	public static String userLoggedIn = "";
	
	public static void main(String[] args) {
		// Welcoming the user 
		System.out.println("Welcome to the Swatch Bharth Application !!");
		//fetching input for log in or signup
		boolean signupflag = false;
		SwatchBharath swatchBharath = new SwatchBharath();
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	    BufferedReader bufferReader = new BufferedReader(inputStreamReader);
		
	    try {
			while(failureCount < 3 && successCount == 0 || signupflag) {
               if(signupflag)
            	   signupflag = false;
               
               System.out.println("Please choose from  one of the below actions ");
               System.out.println("1.Login");
               System.out.println("2.Signup");
               
               String userAction = bufferReader.readLine();
               if("Login".equalsIgnoreCase(userAction) || "1".equalsIgnoreCase(userAction)) {
            	   boolean success = swatchBharath.login(bufferReader);
            	   if(success)
            		   successCount++;
            	   else
            		   failureCount++;
               } else if("Signup".equalsIgnoreCase(userAction)|| "2".equalsIgnoreCase(userAction)) {
            	   boolean success = swatchBharath.signup(bufferReader);
            	   if(success) {
            		   successCount++;
            		   signupflag = true;
            	   }
            	   else
            		   failureCount++;
               }
               else {
            	   failureCount++;
            	   if(failureCount < 3) {
            		   System.out.println("Please enter valid Action!!");
            		   System.out.println("1.Login");
            		   System.out.println("2.Signup");
            	   }
               }
            }
			
			if(failureCount >= 3) {
				System.out.println("please Try Again after some time.You have reached maximum attempts!!");
				System.exit(0);
			}
			System.out.println();
			System.out.println("Do you want to submit wastage!!");
			System.out.println("Please choose from  one of the below actions ");
			System.out.println("1.yes");
			System.out.println("2.no");
			
			String wasteCollectionDecision=bufferReader.readLine();
			while(submitagain) {
				if("yes".equalsIgnoreCase(wasteCollectionDecision) || "1".equalsIgnoreCase(wasteCollectionDecision)) {
					System.out.println("Please choose the type of waste ");
					System.out.println("1.plastic");
					System.out.println("2.metal");
					System.out.println("3.glass");
					String typeOfWastage=bufferReader.readLine();
					System.out.println("please select type of brand");
					System.out.println("1.pepsi");
					System.out.println("2.sprite");
					System.out.println("3.coke");
					System.out.println("4.others");
					String typeOfBrand =bufferReader.readLine();
					if("1".equalsIgnoreCase(typeOfWastage) || "plastic".equalsIgnoreCase(typeOfWastage)) {
						if("1".equalsIgnoreCase(typeOfBrand) || "pepsi".equalsIgnoreCase(typeOfBrand)) {
							WeightOfPlastic += 10;
							//System.out.println("User logged in : " + userLoggedIn);
							//Calling the method to load the information of the wastage user adding to JSON file
							//addWastageToJson(userLoggedIn, bufferReader);
						}
						if("2".equalsIgnoreCase(typeOfBrand) || "sprite".equalsIgnoreCase(typeOfBrand)) {
							WeightOfPlastic += 15;
						}
						if("3".equalsIgnoreCase(typeOfBrand) || "coke".equalsIgnoreCase(typeOfBrand)) {
							WeightOfPlastic += 20;
						}
						if("4".equalsIgnoreCase(typeOfBrand) || "others".equalsIgnoreCase(typeOfBrand)) {
							WeightOfPlastic += 5;
						}
					}
					if("2".equalsIgnoreCase(typeOfWastage) || "metal".equalsIgnoreCase(typeOfWastage)) {
						if("1".equalsIgnoreCase(typeOfBrand) || "pepsi".equalsIgnoreCase(typeOfBrand))
							WeightOfMetal += 15;
						if("2".equalsIgnoreCase(typeOfBrand) || "sprite".equalsIgnoreCase(typeOfBrand))
							WeightOfMetal += 20;
						if("3".equalsIgnoreCase(typeOfBrand) || "coke".equalsIgnoreCase(typeOfBrand))
							WeightOfMetal += 25;
						if("4".equalsIgnoreCase(typeOfBrand) || "others".equalsIgnoreCase(typeOfBrand))
							WeightOfMetal += 10;
					}
					if("3".equalsIgnoreCase(typeOfWastage) || "glass".equalsIgnoreCase(typeOfWastage)) {
						if("1".equalsIgnoreCase(typeOfBrand) || "pepsi".equalsIgnoreCase(typeOfBrand))
							WeightOfGlass += 20;
						if("2".equalsIgnoreCase(typeOfBrand) || "sprite".equalsIgnoreCase(typeOfBrand))
							WeightOfGlass += 25;
						if("3".equalsIgnoreCase(typeOfBrand) || "coke".equalsIgnoreCase(typeOfBrand))
							WeightOfGlass += 30;
						if("4".equalsIgnoreCase(typeOfBrand) || "others".equalsIgnoreCase(typeOfBrand))
							WeightOfGlass += 15;
					}
					
					System.out.println("do you want to submit again");
					System.out.println("1.yes");
					System.out.println("2.no");
					wasteCollectionDecision=bufferReader.readLine();
					//if("2".equalsIgnoreCase(submitAgain) || "no".equalsIgnoreCase(submitAgain))
					//submitagain=false;
				}
				else
					submitagain = false;
			}
			//Calling the method to generate the report
			//displayReport()
			
			totalRewardPoints = totalRewardPoints + WeightOfPlastic + WeightOfMetal + WeightOfGlass;
			System.out.println("Congrats you have Earned " + totalRewardPoints + " Rewards points");
			System.out.println("Below are the details of the wastage you have submitted");
			System.out.println("Plastic Waste: "+WeightOfPlastic+", Metal waste: "+WeightOfMetal+", Glass waste: "+WeightOfGlass);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean login(BufferedReader bufferReader) {
		//welcome to login for swatchBharath
		String username = "";
		String emailId = "";
		String mobileNumber = "";
		
		try {
			System.out.println("Please Enter userName:");
			username = bufferReader.readLine();
			userLoggedIn = username;
			System.out.println("Please Enter EmailId:");
			emailId = bufferReader.readLine();
			System.out.println("Please Enter MobileNumber:");
			mobileNumber = bufferReader.readLine();
			System.out.println("Helloo "+username);
			System.out.println("ID : "+emailId);
			System.out.println("Phonenumber: " + mobileNumber);
			StringBuilder s = new StringBuilder();
			File f = new File("C:\\Users\\Rakesh Reddy\\eclipse-workspace\\swatchBharath\\src\\main\\java\\resources\\userdetails.json");
			FileInputStream fin = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fin);
			byte[] buffer = new byte[1024];
			int read = 0;
			while ((read = bis.read(buffer, 0, 1024)) >= 0) {
				s.append(new String(buffer, 0, read));
			}
			
			System.out.println(s.toString());
			try {
				JSONArray users = new JSONArray(s.toString());
				for(int i = 0; i < users.length(); i++) {
					JSONObject user = users.getJSONObject(i);
					if(username.equals(user.get("userName")) && mobileNumber.equals(user.get("mobileNumber")) && emailId.equals(user.get("emailId"))) {
						System.out.println("Hi " + user + ",you have successfully logged in to swatchBharath App");
						return true;
					}
				}
				
				System.out.println("Please enter correct details and try again!!");
				return false;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean signup(BufferedReader bufferReader) {
		//welcome to signup for swatchBharath
		String username = "";
		String emailId = "";
		String mobileNumber = "";
		try {
			System.out.println("Please Enter userName:");
			username = bufferReader.readLine();
			System.out.println("Please Enter EmailId:");
			emailId = bufferReader.readLine();
			System.out.println("Please Enter MobileNumber:");
			mobileNumber = bufferReader.readLine();
			System.out.println("Helloo " + username);
			System.out.println("id " + emailId);
			System.out.println("phonenumber " + mobileNumber);
			JSONObject userRegistration = new JSONObject();
			try {
				userRegistration.put("userName", username);
				userRegistration.put("emailId", emailId);
				userRegistration.put("mobileNumber", mobileNumber);
				System.out.println(userRegistration.toString());
				StringBuilder s = new StringBuilder();
				//InputStream jsonFile = this.getClass().getResourceAsStream("/re/userdetails.json");
				File f = new File("C:\\Users\\Rakesh Reddy\\eclipse-workspace\\swatchBharath\\src\\main\\java\\resources\\userdetails.json");
				FileInputStream fin = new FileInputStream(f);
				BufferedInputStream bis = new BufferedInputStream(fin);
				byte[] buffer = new byte[1024];
				int read = 0;
				while ((read = bis.read(buffer, 0, 1024)) >= 0) {
					s.append(new String(buffer, 0, read));
				}
				
				System.out.println(s.toString());
				JSONArray j = new JSONArray(s.toString());
				j.put(userRegistration);
				FileOutputStream fout = new FileOutputStream(f);
				BufferedOutputStream bos = new BufferedOutputStream(fout);
				bos.write(j.toString().getBytes());
				bos.flush();
				bos.close();
				fout.close();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		System.out.println("Registration Successfull");
		return true;
	}
}