package ru.babaev.Controllers;

import ru.babaev.Models.Comission;
import ru.babaev.Service.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.NoSuchElementException;

public class ComissionController {
    private String cardRegex = "\\d{16}";
    private final DBConnection dbClient = DBConnection.getInstance();
    Connection connection = dbClient.connection;
    public ComissionController() throws SQLException, ClassNotFoundException {
    }

    public BigDecimal read(String card) throws SQLException {

        if (card == null || card.isEmpty()){
            throw new NoSuchElementException("Card is not presented");
        }

        if (card.matches(cardRegex) == false){
            throw new NoSuchElementException("Card does not match regex " + cardRegex);
        }
        Statement stmt = connection.createStatement();

        BigDecimal comission = new BigDecimal(-1);
        int commisionsCount = 0;
        String query = "SELECT comission FROM \"Comission\" WHERE card like \'" + card + "\'";

        ResultSet rs = stmt.executeQuery(query);

        while(rs.next()) {
            comission = rs.getBigDecimal("comission");
            commisionsCount++;
        }

        if(commisionsCount == 0){
            throw new NoSuchElementException("Query with card " + card + " did not return any accounts");
        }
        if (commisionsCount > 1){
            throw new NoSuchElementException("Iternal server error: query with card " + card + " returned two or more comissions");
        }
        return comission;
    }

    public boolean update(){
        return false;
    }
}
