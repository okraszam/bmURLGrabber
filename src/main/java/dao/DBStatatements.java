package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBStatatements {

    PreparedStatement createURLDescriptionTabIfNotExist (Connection connection) {

        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder.append("CREATE TABLE IF NOT EXISTS bmurlgrabberdb.urldescription\n")
                    .append("(\n")
                    .append("id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,\n")
                    .append("dateOfArchivisation VARCHAR(20),\n")
                    .append("shortFormOfURL VARCHAR(50),\n")
                    .append("completeURL VARCHAR(2033)\n")
                    .append(");\n");

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(tableBuilder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;

    }

    PreparedStatement createURLContentTabIfNotExist (Connection connection) {

        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder.append("CREATE TABLE IF NOT EXISTS bmurlgrabberdb.urlcontent\n")
                    .append("(\n")
                    .append("id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,\n")
                    .append("dateOfArchivisation VARCHAR(20),\n")
                    .append("urlContent MEDIUMBLOB\n")
                    .append(");");

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(tableBuilder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;

    }


    PreparedStatement archiveURLDescriptionStatement (Connection connection, String dateOfArchivisation, String shortFormOfURL, String complereURL) {

        StringBuilder insertionBuilder = new StringBuilder();
        insertionBuilder.append("INSERT INTO bmurlgrabberdb.urldescription ")
                        .append("(dateOfArchivisation, shortFormOfURL, completeURL) ")
                        .append("VALUES ('" + dateOfArchivisation + "', '" + shortFormOfURL + "', '" + complereURL + "');");

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(insertionBuilder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;

    }

    PreparedStatement archiveURLContentStatement (Connection connnection, String dateOfArchivisation, File urlContent) {

        StringBuilder insertionBuilder = new StringBuilder();
        insertionBuilder.append("INSERT INTO bmurlgrabberdb.urlcontent ")
                        .append("(dateOfArchivisation, urlContent) ")
                        .append("VALUES ('" + dateOfArchivisation + "', ?);");

//        String insertionCommand =  "INSERT INTO bmurlgrabberdb.urlcontent (dateOfArchivisation, urlContent) VALUES (?, ?);";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connnection.prepareStatement(insertionBuilder.toString());
            InputStream inputStream = new FileInputStream(urlContent);
//            preparedStatement.setString(1, dateOfArchivisation);
            preparedStatement.setBlob(1, inputStream);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

}

