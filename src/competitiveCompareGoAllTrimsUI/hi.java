package competitiveCompareGoAllTrimsUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class hi {
	public void loadTextFromDataFolder(String env, String filePathName) throws IOException {
	    boolean loadFromClasspath = true;
	    String fileName = "StagingAllURLs.txt"; // provide an absolute path here to be sure that file is found
	    BufferedReader reader = null;
	    try {
	        
	        if (loadFromClasspath) {
	            // loading from classpath
	            // see the link above for more options
	            InputStream in = getClass().getClassLoader().getResourceAsStream(filePathName); 
	            reader = new BufferedReader(new InputStreamReader(in));
	        } else {
	            // load from file system
	            reader = new BufferedReader(new FileReader(new File(fileName)));
	        }

	        String line = null;
	        while ( (line = reader.readLine()) != null) {
	            // do something with the line here
	            System.out.println("Line read: " + line);
	        }
	        List<String> lines = new ArrayList<String>();
	        String a[] = lines.toArray(new String[0]);
	        int arrsize=a.length;
	        System.out.println(a+"\n\nHello 23th  world!!!");
	        System.out.println("\nArr lenght="+arrsize);
	        
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(null,e.getMessage()+" for lol.txt","File Error",JOptionPane.ERROR_MESSAGE);
	    } finally {
	        if (reader != null) {
	            reader.close();  
	        }
	    }
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello 23th  world!");
		hi loadTextData=new hi(); 
		
		loadTextData.loadTextFromDataFolder("Staging","./data/StagingAllURLs.txt");
		
	}

}
