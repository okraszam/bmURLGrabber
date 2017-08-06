//package dao;
//
//import dao.util.DBTablePrinter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import javax.ejb.Stateless;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//@Stateless
//public class DBLister {
//
//    private static Logger LOG = LoggerFactory.getLogger(DBArchiver.class);
//    private static DBStatatements dbStatatements = new DBStatatements();
//
////    public int checkCurrentDBRowCount() {
////
////        int currentDBRowCount = 0;
////        Connection connection = DBConnectionBuilder.createConnectionToDB();
////        PreparedStatement currentDBRowCountStatement = dbStatatements.getTableLengthStatement(connection);
////
////        ResultSet resultSet;
////        try {
////            resultSet = currentDBRowCountStatement.getResultSet();
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////
////
////
////
////
////        return currentDBRowCount;
////
////    }
//
////    public String printEntireBDTable () {
////
////        Connection connection = DBConnectionBuilder.createConnectionToDB();
////        DBTablePrinter.printTable(connection, DBConnectionBuilder.descriptionTableName);
////
////
////
////
////
////    }
//
//}
