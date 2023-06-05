package raf.news.rafnews.repositories;

import raf.news.rafnews.entities.Category;
import raf.news.rafnews.entities.News;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlNewsRepository extends MySqlAbstractRepository implements NewsRepository {

    @Override
    public News addNews(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select * from category where id = ?");
            preparedStatement.setInt(1,news.getCategoryId());
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()){
                return null;
            }

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO news (title, content, timeCreated, numOfVisits, userId, categoryId) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setDouble(3, news.getTimeCreated());
            preparedStatement.setInt(4, news.getNumOfVisits());
            preparedStatement.setInt(5, news.getUserId());
            preparedStatement.setInt(6, news.getCategoryId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                news.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public News updateNews(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select * from category where id = ?");
            preparedStatement.setInt(1,news.getCategoryId());
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()){
                return null;
            }

            preparedStatement = connection.prepareStatement("select * from users where id = ?");
            preparedStatement.setInt(1,news.getUserId());
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()){
                return null;
            }

            preparedStatement = connection.prepareStatement("update news set title = ?, content = ?, timeCreated = ?, userId = ?, categoryId = ? where id = ?");
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setDouble(3, news.getTimeCreated());
            preparedStatement.setInt(4, news.getUserId());
            preparedStatement.setInt(5, news.getCategoryId());
            preparedStatement.setInt(6, news.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public void increaseVisits(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            int visits = 0;

            preparedStatement = connection.prepareStatement("select * from news where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                visits = resultSet.getInt("numOfVisits");
            }

            preparedStatement = connection.prepareStatement("update news set numOfVisits = ? where id = ?");
            preparedStatement.setInt(1, visits + 1);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public List<News> allNews() {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from news");
            while (resultSet.next()) {
                news.add(new News(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getDouble("timeCreated"), resultSet.getInt("numOfVisits"), resultSet.getInt("userId"), resultSet.getInt("categoryId")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public News deleteNews(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("delete from news_tag_table where newsId = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("delete from comment where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("delete from news where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return new News();
    }
}
