package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws SQLException {
        
        Scanner sc=new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("\nEnter Choice:\n 1. Insert Data\n 2. Show Records \n 3. Update Record\n 4. Delete Record\n 5. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    insertData();
                    break;

                case 2:
                    showResults();                    
                    break;
                
                case 3: 
                    updateRecord();
                    break;

                case 4:
                    clearAll();
                    break;
                default: 
                    return;
        } //end of switch
        
    }  //end of while        
} //end of main
    
    static void insertData(){        
        RunQuery crud=new RunQuery();
        Scanner read=new Scanner(System.in);
        System.out.println("Enter Book Name::");
        String Book=read.next();
        System.out.println("Enter Date of Issue in yyyy-mm-dd format::");
        String date = read.next();
        System.out.println("Enter Date of Return in yyyy-mm-dd format::");
        String date1 = read.next();
        if(crud.insertRecord(Book,date,date1)){
            System.out.println("Insert Successful!!");
            //System.out.println(date);
        }   
    }    
    
    static void showResults() throws SQLException{
        RunQuery crud=new RunQuery();
        ResultSet result=crud.displayRecord();
        boolean val=result.next();
        System.out.println("id\tBookName\tDOI\tDOR");
        System.out.println("---------------------------------------");
        while(result.next()){
            int id= result.getInt("id");
            String BookName = result.getString("BookName");
            String DOI = result.getString("DOI");
            String DOR = result.getString("DOR");
            System.out.println(id+"\t"+BookName+"\t"+DOI+"\t"+DOR);
        } 
        System.out.println("----------------------------------------");
        if(val==false)
            System.out.println("Table Empty");
    }
    
    static void updateRecord(){
        RunQuery crud=new RunQuery();
        Scanner read=new Scanner(System.in);
        System.out.println("Enter id to update return date:");
        int id=read.nextInt();
        System.out.println("Enter new return date:");
        String DOR=read.next();
        if (crud.updateRow(id,DOR)) {
            System.out.println(id + " updated!!");
        }         
    }
    
    static void clearAll(){
        RunQuery crud=new RunQuery();
        Scanner read=new Scanner(System.in);
        System.out.println("Enter id to delete:");
        int id=read.nextInt();
        if (crud.deleteRecord(id)) {
            System.out.println(id + " Deleted");
        } else {
            System.out.println("Already Empty");
        }
    }    
}
