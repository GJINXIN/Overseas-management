/**
 * auther:xpl
 * create date:2019.1.7
 */

package com.salary;
import java.sql.*;
import java.util.ArrayList;

public class ConnectDatabaseTest {
    public ConnectDatabaseTest(){}
    public ArrayList getTestResult(){
        ArrayList<String> arr=new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); //com.mysql.cj.jdbc.Driver已被弃用
            String url = "jdbc:mysql://localhost:3306/salarysystem"+"?serverTimezone=GMT%2B8";//后面不加时区会报错
            String user="staff";
            String password="staff";
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement stat=conn.createStatement();
            String commend="select * from staff";
            ResultSet result=stat.executeQuery(commend);
            int i=0;
            while(result.next()){
                arr.add(result.getString(1)+" "+result.getString(2)+" "+result.getString(3)
                        +" "+result.getString(4)+" "+result.getString(5));
            }
            result.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return arr;
    }
}
