package jdbctests;

import org.testng.annotations.Test;

import java.nio.channels.ScatteringByteChannel;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dynamic_list4 {

    String dbUrl="jdbc:oracle:thin:@18.208.178.105:1521:xe";
    String dbUsername="hr";
    String bdPassword= "hr";

    @Test
    public void ListofExample2() throws SQLException {


        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, bdPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("Select * from countries");

        //get the resultset object metadata

        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list for keeping all rows a map
        List<Map<String,Object>> queryData= new ArrayList<>();

        //number of columns
        int colCount = rsMetadata.getColumnCount();

        //loop through each row
        while (resultSet.next()){

            Map<String,Object> row= new HashMap<>();

            for (int i=1; i<=colCount;i++){

                row.put(rsMetadata.getColumnName(i),resultSet.getObject(i));

            }
            //add your map to your list
            queryData.add(row);

        }


        //print the result

        for (Map<String, Object> row : queryData){
            System.out.println(row.toString());
        }


        //close all connection
        resultSet.close();
        statement.close();
        connection.close();
    }



    





}
