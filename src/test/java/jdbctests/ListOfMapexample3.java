package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfMapexample3 {

    String dbUrl = "jdbc:oracle:thin:@18.208.178.105:1521:xe";
    String dbUsername = "hr";
    String bdPassword = "hr";

    @Test
    public void ListofExample() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, bdPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("Select * from departments");

        //get the resultset object metadata

        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list for keeping all rows a map
        List<Map<String,Object>> queryData= new ArrayList<>();


        Map<String ,Object> row1= new HashMap<>();
        row1.put("first_name","Steven");
        row1.put("last_name","King");
        row1.put("salary",2400);
        row1.put("job_id","AD_PRES");

        System.out.println(row1.toString());
        Map<String ,Object> row2= new HashMap<>();
        row2.put("first_name","Neena");
        row2.put("Last_name","Kochhar");
        row2.put("salary",17000);
        row2.put("job_id","AD_VP");

        System.out.println(row2.toString());

        System.out.println(row2.get("salary"));

        //adding rws to my list
        queryData.add(row1);
        queryData.add(row2);

        //get the steven last name driectly from the list

        System.out.println(queryData.get(0).get("last_name"));


        //close all connection
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void ListofExample2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, bdPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("Select first_name,last_name,salary,job_id from employees\n"+"where rownum<6");

        //get the resultset object metadata

        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list for keeping all rows a map
        List<Map<String,Object>> queryData= new ArrayList<>();

        //move to first row
        resultSet.next();
        Map<String ,Object> row1= new HashMap<>();
        row1.put(rsMetadata.getColumnName(1),resultSet.getString(1));
        row1.put(rsMetadata.getColumnName(2),resultSet.getString(2));
        row1.put(rsMetadata.getColumnName(3),resultSet.getString(3));
        row1.put(rsMetadata.getColumnName(4),resultSet.getString(4));


        System.out.println(row1.toString());

        resultSet.next();
        Map<String ,Object> row2= new HashMap<>();
        row2.put(rsMetadata.getColumnName(1),resultSet.getString(1));
        row2.put(rsMetadata.getColumnName(2),resultSet.getString(2));
        row2.put(rsMetadata.getColumnName(3),resultSet.getString(3));
        row2.put(rsMetadata.getColumnName(4),resultSet.getString(4));

        System.out.println(row2.toString());

        System.out.println(row2.get("SALARY"));

        //adding rws to my list
        queryData.add(row1);
        queryData.add(row2);

        //get the steven last name driectly from the list

        System.out.println(queryData.get(0).get("LAST_NAME"));



        //close all connection
        resultSet.close();
        statement.close();
        connection.close();
    }
}
