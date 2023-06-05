package raf.news.rafnews.repositories;

import raf.news.rafnews.entities.News_Tag_Table;
import raf.news.rafnews.entities.Tag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlNews_Tag_TableRepository extends MySqlAbstractRepository implements News_Tag_TableRepository{


    @Override
    public List<News_Tag_Table> allEntities() {
        List<News_Tag_Table> entities = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from news_tag_table");
            while (resultSet.next()) {
                entities.add(new News_Tag_Table(resultSet.getInt("newsId"), resultSet.getInt("tagId")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return entities;
    }

    @Override
    public News_Tag_Table addEntity(News_Tag_Table entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select * from news where id = ?");
            preparedStatement.setInt(1, entity.getNewsId());
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()){
                return null;
            }

            preparedStatement = connection.prepareStatement("select * from tags where id = ?");
            preparedStatement.setInt(1, entity.getTagId());
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()){
                return null;
            }

            preparedStatement = connection.prepareStatement("select * from news_tag_table where newsId = ? and tagId = ?");
            preparedStatement.setInt(1, entity.getNewsId());
            preparedStatement.setInt(2, entity.getTagId());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return new News_Tag_Table(resultSet.getInt("newsId"), resultSet.getInt("tagId"));
            }

            preparedStatement = connection.prepareStatement("INSERT INTO news_tag_table (newsId, tagId) VALUES(?, ?)");
            preparedStatement.setInt(1, entity.getNewsId());
            preparedStatement.setInt(2, entity.getTagId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return entity;
    }

    @Override
    public News_Tag_Table deleteEntity(int newsId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("delete from news_tag_table where newsId = ?");
            preparedStatement.setInt(1, newsId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return new News_Tag_Table();
    }
}
