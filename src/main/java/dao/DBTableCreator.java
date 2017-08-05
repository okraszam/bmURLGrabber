package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBTableCreator {

    PreparedStatement createURLDescriptionTabIfNotExistStatement (Connection connection) {

        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder.append("CREATE TABLE IF NOT EXISTS bmurlgrabberdb.urldescription\n")
                    .append("(\n")
                    .append("id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,\n")
                    .append("dateOfArchivisation VARCHAR(20),\n")
                    .append("contentFileName VARCHAR(50),\n")
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

    PreparedStatement createURLContentTabIfNotExistStatement (Connection connection) {

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

}
