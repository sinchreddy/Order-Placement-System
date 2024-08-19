package main.java.com.nursery.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main{
    public Statement s;
    public static void main(String[] args) {
        Statement s;
        try{
            Connection obj = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?user=root", "root", "9008496759s");
            s= obj.createStatement();
            System.out.println(obj);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}