package raf.news.rafnews.repositories;

import raf.news.rafnews.entities.Category;
import raf.news.rafnews.entities.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCommentRepository extends MySqlAbstractRepository implements CommentRepository{

    @Override
    public Comment addComment(Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select * from news where id = ?");
            preparedStatement.setInt(1, comment.getNewsId());
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()){
                return null;
            }

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO comment (author, content, timeCreated, newsId) VALUES(?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, comment.getAuthor());
            preparedStatement.setString(2, comment.getContent());
            preparedStatement.setDouble(3, comment.getTimeCreated());
            preparedStatement.setInt(4, comment.getNewsId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                comment.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comment;
    }

    @Override
    public List<Comment> allPostComments(int id) {
        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select * from news where newsId = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                comments.add(new Comment(resultSet.getInt("id"), resultSet.getString("author"), resultSet.getString("content"), resultSet.getDouble("timeCreated"), resultSet.getInt("newsId")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comments;
    }

    @Override
    public List<Comment> allComments() {
        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from comment");
            while (resultSet.next()) {
                comments.add(new Comment(resultSet.getInt("id"), resultSet.getString("author"), resultSet.getString("content"), resultSet.getDouble("timeCreated"), resultSet.getInt("newsId")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comments;
    }
}
