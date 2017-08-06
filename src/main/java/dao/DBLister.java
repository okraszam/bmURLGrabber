package dao;

import dao.util.DBTablePrinter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBLister {

    private static DBStatatements dbStatatements = new DBStatatements();

//    public static int checkCurrentDBRowCount() {
//
//        Connection connection = DBConnectionBuilder.createConnectionToDB();
//        PreparedStatement currentDBRowCountStatement = dbStatatements.getURLDescriptionTableLength(connection);
//
//        ResultSet resultSet;
//        try {
//            resultSet = currentDBRowCountStatement.executeQuery();
//
//            while (resultSet.next()) {
//                return resultSet.getInt(1);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }

    public void printEntireBDTable () {

        Connection connection = DBConnectionBuilder.createConnectionToDB();
//        return DBTablePrinter.printTable(connection, DBConnectionBuilder.descriptionTableName);

    }

}
