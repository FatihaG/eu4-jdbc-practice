package jdbctests;

import java.sql.*;

public class Main1 {


    public static void main(String[] args) throws SQLException {

        String dbUrl="jdbc:oracle:thin:@18.208.178.105:1521:xe";
        String dbUsername="hr";
        String bdPassword= "hr";


      //create connection

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,bdPassword);
        //create statement object
        Statement statement= connection.createStatement();
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("Select first_name,last_name,salary from employees");

        //move pointer to the first row
        resultSet.next();
        //getting information with column name
        System.out.println(resultSet.getString("first_name"));

      // System.out.println(resultSet.getInt(1)+" - "+ resultSet.getString("first_name"));

        //getting information with column index(satrts from 1)
        System.out.println(resultSet.getString(2));


        //move pointer to second column

        resultSet.next();
        //getting information with column name
        System.out.println(resultSet.getString("first_name"));

        //getting information with column index(satrts from 1)
        System.out.println(resultSet.getString(2));
        //System.out.println(resultSet.getInt(1)+" - "+ resultSet.getString("first_name"));


     while(resultSet.next()){
         System.out.println(resultSet.getString(1)+" - "+ resultSet.getString(2)+" "+ resultSet.getString(3));
     }

        //close all connection
        resultSet.close();
        statement.close();
        connection.close();





    }
}
