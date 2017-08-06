package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBArchiver {

    private static Logger LOG = LoggerFactory.getLogger(DBArchiver.class);

    private static DBTableCreator dbTableCreator  = new DBTableCreator();
    private static DBStatatements dbStatatements = new DBStatatements();

    public static void addURLDescriptionToDB (String dateOfArchivisation, String contentFileName, String completeURL) {

        try {
            Connection connection = DBConnectionBuilder.createConnectionToDB();
            PreparedStatement tableCreationStatement = dbTableCreator.createURLDescriptionTabIfNotExistStatement(connection);
            PreparedStatement insertionStatement = dbStatatements.archiveURLDescriptionStatement(connection,
                                                                                                dateOfArchivisation,
                                                                                                contentFileName,
                                                                                                completeURL);
            tableCreationStatement.executeUpdate();
            int updatingRow = insertionStatement.executeUpdate();

            if (updatingRow > 0) {
                LOG.info("New URLDescription has been added to bmURLGrabberDB");
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void addURLContentToDB (String dateOfArchivisation, String contentFileName, File urlContent) {

        try {
            Connection connection = DBConnectionBuilder.createConnectionToDB();
            PreparedStatement tableCreationStatement = dbTableCreator.createURLContentTabIfNotExistStatement(connection);
            PreparedStatement preparedStatement = dbStatatements.archiveURLContentStatement(connection,
                                                                                            dateOfArchivisation,
                                                                                            contentFileName,
                                                                                            urlContent);
            tableCreationStatement.executeUpdate();
            int updatingRow = preparedStatement.executeUpdate();

            if (updatingRow > 0) {
                LOG.info("New URLContent has been added to bmURLGrabberDB");
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
