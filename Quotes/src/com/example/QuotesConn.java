package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class QuotesConn {

    private static Connection getConnection() throws ClassNotFoundException,SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_schema", "root", "vasudeva");
        if(conn!=null){
            return conn;
        }
        else {
            throw new RuntimeException("Connection error");
        }
    }

    public static void addQuote(QuotesData qd)  {
        try {
            String sql="INSERT INTO quotes(quote,author) VALUES(?,?)";
            PreparedStatement ps=getConnection().prepareStatement(sql);
            ps.setString(1, qd.getQuote());
            ps.setString(2,qd.getAuthor());
            ps.execute();
        }
       catch (RuntimeException | ClassNotFoundException | SQLException e){
           System.out.println(e.getMessage());
       }
    }

    public static List<QuotesData>  showQuote(){
        List<QuotesData> ll = new ArrayList<>();
        try {
            String sql = "SELECT * FROM quotes";
            Statement st = getConnection().createStatement();
            ResultSet qd = st.executeQuery(sql);
            while (qd.next()) {
                ll.add(new QuotesData(qd.getInt("id"), qd.getString("quote"), qd.getString("author")));
            }
        }
        catch (Exception e){
            System.out.println("ERROR :"+e.getMessage());
        }
        return ll;
    }

    public static void deleteQuote(int i) {
        try{
            String sql="DELETE from quotes where id=?";
            PreparedStatement ps= getConnection().prepareStatement(sql);
            ps.setInt(1,i);
            ps.execute();
            System.out.println("Successfully Delete Quote that had id : "+i);
        }
        catch ( Exception e){
            System.out.println("Error in deleting :"+ e.getMessage());
        }
    }
}
