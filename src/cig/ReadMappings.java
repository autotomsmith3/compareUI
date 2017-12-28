package cig;

//Reading and writing Work well on Dec 28, 2017. Submitted on Dec 28, 2017.
//Description: It can retrieve all rows by styleids, modify the code elephantList.get(17) to get all OEM="~Y~"
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ReadMappings {
	public static void ReadColor(String inputfile, String outputfile, String[] styleids) {
		// TODO Auto-generated method stub

		String StyleID_0 = "";
		String ImageID_1 = "";
		String FileName_2 = "";
		String Ext1MfrFullCode_3 = "";
		String Ext2MfrFullCode_4 = "";
		String Ext1RGBHex_5 = "";
		String Ext2RGBHex_6 = "";
		String Type_7 = "";
		String Angle_8 = "";
		String Background_9 = "";
		String Size_10 = "";
		String Year_11 = "";
		String DivisionName_12 = "";
		String ModelName_13 = "";
		String BodyType_14 = "";
		String Carryover_15 = "";
		String ExactMatch_16 = "";
		String OEMTemp_17 = "";
		// Assume default encoding.
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(outputfile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Always wrap FileWriter in BufferedWriter.
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		System.out.println("Started!");
		int styleLenth = styleids.length;
		for (String styleid : styleids) {
			try (BufferedReader br = new BufferedReader(new FileReader(inputfile))) {
				String line;
				while ((line = br.readLine()) != null) {
					// process the line.
					List<String> elephantList = Arrays.asList(line.split(","));
					StyleID_0 = elephantList.get(0);
					// StyleID_0=elephantList.get(17);

					if (StyleID_0.equalsIgnoreCase(styleid)) {
						// if (StyleID_0.equalsIgnoreCase("~Y~")) {
						bufferedWriter.write(line);
						bufferedWriter.newLine();

						System.out.println(line);
						// System.out.println(elephantList);

					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Total StyleIDs=" + styleLenth + ". Complete!");

	}

	public static void ReadVehicle(String inputfile, String outputfile, String[] styleids) {
		// TODO Auto-generated method stub

		String StyleID_0 = "";
		String ImageID_1 = "";
		String FileName_2 = "";
		String Type_3 = "";
		String Background_4 = "";
		String Size_5 = "";
		String Carryover_6 = "";
		String Year_7 = "";
		String DivisionName_8 = "";
		String ModelName_9 = "";
		String BodyType_10 = "";
		String ExactMatch_11 = "";
		String OEMTemp_12 = "";
		// Assume default encoding.
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(outputfile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Always wrap FileWriter in BufferedWriter.
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		System.out.println("Started!");
		int styleLenth = styleids.length;
		for (String styleid : styleids) {

			try (BufferedReader br = new BufferedReader(new FileReader(inputfile))) {
				String line;
				while ((line = br.readLine()) != null) {
					// process the line.
					List<String> elephantList = Arrays.asList(line.split(","));
					StyleID_0 = elephantList.get(0);
					// StyleID_0=elephantList.get(12); //

					if (StyleID_0.equalsIgnoreCase(styleid)) {
						// if (StyleID_0.equalsIgnoreCase("~Y~")) {
						bufferedWriter.write(line);
						bufferedWriter.newLine();

						System.out.println(line);
						// System.out.println(elephantList);

					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Total StyleIDs=" + styleLenth + ". Complete!");

	}

	public static void main(String[] args) {
		
//		//US mapping *****************************************************
		String inputFileName_color = "C:\\chrome\\loader\\CIG-MAPPING-IMPORT\\zipfiles\\US\\unzipped\\US_ImageGalleryMapping_2017_Color.txt";
		String inputFileName_vehicle = "C:\\chrome\\loader\\CIG-MAPPING-IMPORT\\zipfiles\\US\\unzipped\\US_ImageGalleryMapping_2017_Vehicle.txt";

		String outputFileName_color = "C:\\chrome\\loader\\CIG-MAPPING-IMPORT\\zipfiles\\US\\unzipped\\StyleID_US_ImageGalleryMapping_2017_Color.txt";
		String outputFileName_vehicle = "C:\\chrome\\loader\\CIG-MAPPING-IMPORT\\zipfiles\\US\\unzipped\\StyleID_US_ImageGalleryMapping_2017_Vehcile.txt";

		String[] styleIDs = { "387896", "381489" };// "387896","374391","389544"
//		//US mapping *****************************************************
		
//		//CA mapping *****************************************************
//		String inputFileName_color = "C:\\chrome\\loader\\CIG-MAPPING-IMPORT\\zipfiles\\CA\\unzipped\\CA_ImageGalleryMapping_2017_Color.txt";
//		String inputFileName_vehicle = "C:\\chrome\\loader\\CIG-MAPPING-IMPORT\\zipfiles\\CA\\unzipped\\CA_ImageGalleryMapping_2017_Vehicle.txt";
//
//		String outputFileName_color = "C:\\chrome\\loader\\CIG-MAPPING-IMPORT\\zipfiles\\CA\\unzipped\\StyleID_CA_ImageGalleryMapping_2017_Color.txt";
//		String outputFileName_vehicle = "C:\\chrome\\loader\\CIG-MAPPING-IMPORT\\zipfiles\\CA\\unzipped\\StyleID_CA_ImageGalleryMapping_2017_Vehcile.txt";
//
//		String[] styleIDs = { "389544" };// "387896","374391","389544"
//		//CA mapping *****************************************************		
		
//		ReadColor(inputFileName_color, outputFileName_color, styleIDs);
		 ReadVehicle(inputFileName_vehicle, outputFileName_vehicle, styleIDs);
		System.out.println("Done!");
	}

}
