package pl.javastart.zad22web;

import org.springframework.ui.Model;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class PrzychodyDao {

    private static final String URL = "jdbc:mysql://localhost:3306/wydatki?characterEncoding=utf8";
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


        public void show(String type ) throws SQLException {
            Statement statement = connection.createStatement();
            String query= "select * from home_budget where type ='"+type+"' ";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                long id = resultSet.getLong("id");
                type= resultSet.getString("type");
                String description= resultSet.getString("description");
                long amount =resultSet.getLong("amount");
                Date date=resultSet.getDate("date");

                System.out.println (id+ " " + type + "  " +description+"  KWOTA: "+amount+" DNIA: "+date);
            }
        }



        public void save (Przychody przychody) {
            final String sql="insert into home_budget (type, description, amount, date ) values (?,?,?,?)";
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


