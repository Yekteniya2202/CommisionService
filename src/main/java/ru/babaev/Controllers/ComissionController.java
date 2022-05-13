package ru.babaev.Controllers;

import ru.babaev.Models.Comission;
import ru.babaev.Service.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ComissionController {
    private String cardRegex = "\\d{16}";
    private final DBConnection dbClient = DBConnection.getInstance();
    Connection connection = dbClient.connection;
    public ComissionController() throws SQLException, ClassNotFoundException {
    }

    public Comission read(String card) throws SQLException {

        if (card == null || card.isEmpty()){
            throw new NoSuchElementException("Card is not presented");
        }

        if (card.matches(cardRegex) == false){
            throw new NoSuchElementException("Card does not match regex " + cardRegex);
        }
        Statement stmt = connection.createStatement();

        List<Comission> comissions = new ArrayList<Comission>();
        String query = "SELECT comission FROM \"Comission\" WHERE card like \'" + card + "\'";

        ResultSet rs = stmt.executeQuery(query);

        while(rs.next()) {
            Comission comission = new Comission();
            comission.comission = rs.getBigDecimal("comission");
            comissions.add(comission);
        }

        if(comissions.isEmpty()){
            throw new NoSuchElementException("Query with card " + card + " did not return any accounts");
        }
        if (comissions.size() > 1){
            throw new NoSuchElementException("Iternal server error: query with card " + card + " returned two or more comissions");
        }
        return comissions.get(0);
    }

    public boolean update(){
        return false;
    }
}
