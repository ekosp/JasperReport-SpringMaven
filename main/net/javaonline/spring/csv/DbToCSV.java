package net.javaonline.spring.csv;

public class DbToCSV {
    public static void main(String[] args) {
    	
    	String filename ="X:\\test.txt";
    	String kondisi = "where txn_proc_code not in ('INQR')";
    	
    	FileExporter.exportToCSV(filename, kondisi);

    }
}