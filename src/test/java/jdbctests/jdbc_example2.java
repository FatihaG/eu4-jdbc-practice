package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_example2 {


    String dbUrl="jdbc:oracle:thin:@18.208.178.105:1521:xe";
    String dbUsername="hr";
    String bdPassword= "hr";

    @Test
    public void test1() throws SQLException {

//create connection

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,bdPassword);
        //create statement object
        Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("Select * from departments");


        //how many rows we have for the query
        //go to last row
        resultSet.last();
        //get the row count
        int rowCount= resultSet.getRow();
        System.out.println(rowCount);

        //we need to move before first row to get full list since we are at the last row now
        resultSet.beforeFirst();

        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }
        resultSet = statement.executeQuery("Select * from regions");

        // tek connectionla birden fazla query yazabilirsin. connectionu kapatmadan
        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        //close all connection
        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void MetaDataExample() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,bdPassword);
        //create statement object
        Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("Select * from departments");

        //get the database related data inside the dbMetaData object
        DatabaseMetaData dbMetadata=connection.getMetaData();

        System.out.println("User = "+ dbMetadata.getUserName());
        System.out.println("Database Product Name = "+ dbMetadata.getDatabaseProductName());
        System.out.println("Database product version = "+ dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver name = "+ dbMetadata.getDriverName());
        System.out.println("Driver version = "+ dbMetadata.getDriverVersion());

        //get the resultset object metadata

        ResultSetMetaData rsMetadata= resultSet.getMetaData();

        //how many column
        int colCount = rsMetadata.getColumnCount();
        System.out.println(colCount);

        //column names
        System.out.println(rsMetadata.getColumnName(1));
        System.out.println(rsMetadata.getColumnName(2));

        // print all column mane dynamically

        for (int i=1; i<= colCount;i++){
            System.out.println(rsMetadata.getColumnName(i));
        }






        //close all connection
        resultSet.close();
        statement.close();
        connection.close();


    }

}
