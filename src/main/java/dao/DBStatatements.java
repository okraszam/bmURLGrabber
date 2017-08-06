package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBStatatements {

    PreparedStatement archiveURLDescriptionStatement (Connection connection,
                                                      String dateOfArchivisation,
                                                      String contentFileName,
                                                      String complereURL) {

        StringBuilder insertionBuilder = new StringBuilder();
        insertionBuilder.append("INSERT INTO bmurlgrabberdb.urldescription ")
                        .append("(dateOfArchivisation, contentFileName, completeURL) ")
                        .append("VALUES ('" + dateOfArchivisation + "', '" + contentFileName + "', '" + complereURL + "');");

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(insertionBuilder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;

    }

    PreparedStatement archiveURLContentStatement (Connection connnection,
                                                  String dateOfArchivisation,
                                                  String contentFileName,
                                                  File urlContent) {

        StringBuilder insertionBuilder = new StringBuilder();
        insertionBuilder.append("INSERT INTO bmurlgrabberdb.urlcontent ")
                        .append("(dateOfArchivisation, contentFileName, urlContent) ")
                        .append("VALUES ('" + dateOfArchivisation + "', '" + contentFileName + "', ?);");

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connnection.prepareStatement(insertionBuilder.toString());
            InputStream inputStream = new FileInputStream(urlContent);
            preparedStatement.setBlob(1, inputStream);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    PreparedStatement getTableLengthStatement(Connection connection) {

        String dbName = DBConnectionBuilder.databaseName;
        String tableName = DBConnectionBuilder.descriptionTableName;

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT COUNT(*) AS total FROM ")
                    .append(dbName)
                    .append(".")
                    .append(tableName + ";");

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(queryBuilder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;

    }

}

