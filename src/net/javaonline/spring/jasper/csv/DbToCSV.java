package net.javaonline.spring.jasper.csv;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DbToCSV {
    public static void main(String[] args) {
      
    	 String filename ="X:\\test.csv";
    	 
        try {
            FileWriter fw = new FileWriter(filename);
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            Class.forName("org.postgresql.Driver").newInstance();
            //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ekosp", "root", "");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://10.50.50.51:5432/db_atic-ewallet-tani","postgres","postgre");
           
            String query = "select 1 as KODE, txn_date as TANGGAL, m_ba.bank_bin as KODE_BANK_ISSUER, m_ba2.bank_bin as " +
            		"KODE_BANK_ACQUIRER, txn_type as KODE_BANTUAN, " +
            		"t_txn.product_code as KODE_JENIS_SUBSIDI, txn_tid as TERMINAL_ID, txn_rrn as RRN, " +
            		"m_wa.card_debit_no as NO_KARTU, (t_txn.txn_amount * m_sb.coversion_rate_het) as AMOUNT, " +
            		"txn_amount as Quantity, m_sb.sproduct_unit as Unit_Satuan from thist_txnrecord t_txn " +
            		"join public.mdata_wallet_card m_wa on t_txn.card_debit_no = m_wa.card_debit_no " +
            		"join public.mdata_bankpartner m_ba on m_ba.bank_code = m_wa.bank_code " +
            		"join public.mdata_merchant_terminal m_me on m_me.tid = t_txn.txn_tid " +
            		"join public.mdata_bankpartner m_ba2 on m_ba2.bank_code = m_me.bank_code " +
            		"join public.mdata_scheme_business m_sb on m_sb.product_code = t_txn.product_code " +
            		"where txn_proc_code not in ('INQR')";
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            // crete csv header
            List<String> column = new ArrayList<>(Arrays.asList("KODE","TANGGAL","KODE_BANK_ISSUER","KODE_BANK_ACQUIRER",
            		"KODE_BANTUAN","KODE_JENIS_SUBSIDI","TERMINAL_ID","RRN","NO_KARTU","AMOUNT","Quantity","Unit Satuan"));
            
            for (String s : column) {
            	fw.append(s);
                fw.append(";");
            }
            fw.append('\n');
            
            // fill tiap kolom
            while (rs.next()) {
            	
            	for ( int i=1; i < column.size()+1; i++) {
            		
            		fw.append(rs.getString(i));
                    fw.append(';');
            	}
            	
                fw.append('\n');
               }
            
            // fill resume 
           /* String query2 = "select * from emp_master	";
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query2);*/
                       
            
            fw.flush();
            fw.close();
            conn.close();
            System.out.println("CSV File is created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}