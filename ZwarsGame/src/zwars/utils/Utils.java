package zwars.utils;

import java.io.*;
/**
utility class to load a file as a string. used to load the positions of tiles of a map.
*/
public class Utils {
	/**
	loads a file and returns as a string seperated by new lines as a String.
	@param path location of the file which has to be loaded
	@return String format of the file
	*/
	public static String loadFileAsString(String path){
		StringBuilder builder=new StringBuilder();

		try{
		    BufferedReader br =new BufferedReader(new FileReader(path));
		    String line;
		    while((line=br.readLine()) != null)
		    	builder.append(line + "\n");

		    br.close();
		}catch(IOException e){
			e.printStackTrace();
		}

		return builder.toString();
	}
	/**
	method to get integer from a number with handling the NumberFormatException so we dont get it in main class.
	@param number number as a String
	@return number as a integer
	*/
	public static int parseInt(String number){
		try{
			return Integer.parseInt(number);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}
}
