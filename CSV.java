import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.Reader;

public class Main {

  public static void main(String[] args) {
String filePath = "healthcare_dataset.csv"; // update if filename
differs

  try (

  Reader reader = new FileReader(filePath);

  CSVParser csvParser = new CSVParser(
reader,
CSVFormat.DEFAULT.builder()
.setHeader()
.setSkipHeaderRecord(true)
.build()
)
) 
{

  for (CSVRecord record : csvParser) {

  String name = record.get("Name");
String age = record.get("Age");
String gender = record.get("Gender");
System.out.println("Name: " + name);
System.out.println("Age: " + age);
