package test;
//http://javafries.blogspot.ca/2015/10/reading-excel-file-and-sorting-data.html



	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.nio.file.FileSystems;
	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.nio.file.StandardCopyOption;
	import java.util.HashMap;
	import java.util.Iterator;
	import java.util.LinkedHashMap;
	import java.util.LinkedList;
	import java.util.List;
	import java.util.ListIterator;
	import java.util.Map;
	import java.util.Map.Entry;

	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class ExcelSheetSort {
	//	public class ExcelReader
	{
	    HashMap testData = new HashMap();

	    TestVO testVO = new TestVO();

	    private HashMap loadCSVLines(File fileName)
	    {
	        // Used the LinkedHashMap and LikedList to maintain the order
	        HashMap<String, LinkedHashMap<Integer, List>> outerMap = new LinkedHashMap<String, LinkedHashMap<Integer, List>>();

	        LinkedHashMap<Integer, List> hashMap = new LinkedHashMap<Integer, List>();

	        String sheetName = null;
	        // Create an ArrayList to store the data read from excel sheet.
	        // List sheetData = new ArrayList();
	        FileInputStream fis = null;
	        try
	        {
	            fis = new FileInputStream(fileName);
	            // Create an excel workbook from the file system
	            XSSFWorkbook workBook = new XSSFWorkbook(fis);
	            // Get the first sheet on the workbook.
	            for (int i = 0; i < workBook.getNumberOfSheets(); i++)
	            {
	                XSSFSheet sheet = workBook.getSheetAt(i);
	                // XSSFSheet sheet = workBook.getSheetAt(0);
	                sheetName = workBook.getSheetName(i);

	                Iterator rows = sheet.rowIterator();
	                while (rows.hasNext())
	                {
	                    XSSFRow row = (XSSFRow) rows.next();
	                    Iterator cells = row.cellIterator();

	                    List data = new LinkedList();
	                    while (cells.hasNext())
	                    {
	                        XSSFCell cell = (XSSFCell) cells.next();
	                        cell.setCellType(Cell.CELL_TYPE_STRING);
	                        data.add(cell);
	                    }
	                    hashMap.put(row.getRowNum(), data);

	                    // sheetData.add(data);
	                }
	                outerMap.put(sheetName, hashMap);
	                hashMap = new LinkedHashMap<Integer, List>();
	            }

	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	        finally
	        {
	            if (fis != null)
	            {
	                try
	                {
	                    fis.close();
	                }
	                catch (IOException e)
	                {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	        }

	        return outerMap;

	    }

	    public HashMap loadXLSTestData(String lob)
	    {

	        // HashMap testData = null;
	        Path desktop = FileSystems.getDefault().getPath(
	                System.getProperty("user.home") + "/Desktop");
	        File file = new File(desktop.toString() + "\\AutomationTestSuite");
	        if (!file.exists())
	        {
	            if (file.mkdir())
	            {
	                System.out.println("Directory is created!");
	            }
	            else
	            {
	                System.out.println("Failed to create directory!");
	            }
	        }
	        Path oriPath = FileSystems.getDefault().getPath("src/com/aon/automation/properties",
	                lob + "AutomationTestCases.xlsx");
	        // Path copyPath =
	        // FileSystems.getDefault().getPath(System.getProperty("user.home")+"/Desktop",
	        // "SFAutomationTestCases.xlsx");
	        Path copyPath = FileSystems.getDefault().getPath(file.toString(),
	                lob + "AutomationTestCases.xlsx");
	        File f = copyPath.toFile();

	        if (!f.exists())
	        {
	            try
	            {
	                Files.copy(oriPath, copyPath, StandardCopyOption.COPY_ATTRIBUTES);
	                System.out.println("Please update test data under path " + f
	                        + " To run this test automation.");
	            }
	            catch (Exception e)
	            {
	                e.printStackTrace();
	            }

	        }
	        else
	        {
	            testData = loadCSVLines(f);

	        }
	        return testData;
	    }

	    public HashMap XLSUtility(String lob, String testCase, String testDataRow)
	    {
	        loadXLSTestData(lob);
	        List attributes = new LinkedList();
	        HashMap returningMap = new LinkedHashMap();
	        ListIterator iterateOverHeading = null;
	        LinkedList valuesforMap = new LinkedList();
	        Iterator excelSheetEntries = testData.entrySet().iterator();
	        while (excelSheetEntries.hasNext())
	        {
	            Entry thisEntry = (Entry) excelSheetEntries.next();
	            Object key = thisEntry.getKey();
	            if (key.equals(testCase))
	            {
	                Map value = (LinkedHashMap) thisEntry.getValue();
	                String str = null;
	                Iterator iterateOverValue = value.entrySet().iterator();
	                while (iterateOverValue.hasNext())
	                {
	                    Entry headings = (Entry) iterateOverValue.next();
	                    Object headigKey = headings.getKey();
	                    if (headigKey.equals(0))
	                    {
	                        attributes = (LinkedList) headings.getValue();

	                    }
	                    if (headigKey.toString().equals(testDataRow))
	                    {
	                        valuesforMap = (LinkedList) headings.getValue();

	                        Iterator values = valuesforMap.iterator();

	                        iterateOverHeading = attributes.listIterator();
	                        INNER: while (iterateOverHeading.hasNext())
	                        {
	                            str = String.valueOf(iterateOverHeading.next());
	                            while (values.hasNext())
	                            {
	                                String val = String.valueOf(values.next());
	                                returningMap.put(str, val);
	                                continue INNER;
	                            }

	                        }
	                    }

	                }

	                break;
	            }

	        }
	        System.out.println(returningMap);

	        return returningMap;
	    }
	   
	   

	    public static void main(String[] args)
	    {
	    	ExcelSheetSort exl = new ExcelSheetSort();
//	    	ExcelReader exl = new ExcelReader();
	        
	        exl.XLSUtility("SF", "InBound", "2");
	    }
	}