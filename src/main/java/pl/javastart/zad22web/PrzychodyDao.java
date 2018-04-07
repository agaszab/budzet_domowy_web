package pl.javastart.zad22web;

import org.springframework.ui.Model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrzychodyDao {

    private static final String URL = "jdbc:mysql://localhost:3306/budzet?characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASS = "root";
    private Connection connection;


        public PrzychodyDao() {


            try {
                Class.forName ("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection (URL, USER, PASS);
            } catch (ClassNotFoundException e) {
                System.out.println("No driver found");
            } catch (SQLException e) {
                System.out.println("Couldnot establishconnection");
            }
        }


    public List<Przychody> show( String type ) throws SQLException {
        List<Przychody> listaPrzychodow = new ArrayList<>();
        Statement statement = connection.createStatement();
        String query= "select * from przychody where type ='"+type+"' ";
        ResultSet result = statement.executeQuery(query);
        while(result.next()) {
            long id=Long.parseLong(result.getString("id"));
            String typ=type;
            String desc=result.getString("description");
            long amount=Long.parseLong(result.getString("amount"));
            Date date=result.getDate("date");
            Przychody przychody= new Przychody (id, type, desc, amount, date);
            listaPrzychodow.add(przychody);
        }
        return listaPrzychodow;
    }


        public void save (Przychody przychody) {
            final String sql="insert into przychody (type, description, amount, date ) values (?,?,?,?)";
            try
            {

                PreparedStatement prepStmt = connection.prepareStatement(sql);

                prepStmt.setString(1, przychody.getType());
                prepStmt.setString(2, przychody.getDescription());
                prepStmt.setDouble(3, przychody.getAmount());
                prepStmt.setDate(4, przychody.getDate());
                prepStmt.executeUpdate();

            }
            catch(SQLException e ) {
                System.out.println( "Could not save record ");
                e.printStackTrace();
            }
        }



        public void close() {
            try {
                connection.close();
            } catch ( SQLException e) {
                e.printStackTrace();
            }
        }



    public String addForm (Model model, String typ) {
        model.addAttribute("newPrzychody" , new Przychody());
        return "formularz";
    }

    }


