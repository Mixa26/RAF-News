package raf.news.rafnews.repositories;

import raf.news.rafnews.entities.News;
import raf.news.rafnews.entities.News_Tag_Table;
import raf.news.rafnews.entities.Tag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlTagRepository extends MySqlAbstractRepository implements TagRepository{


    @Override
    public List<Tag> allTags() {
        List<Tag> tags = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tags");
            while (resultSet.next()) {
                tags.add(new Tag(resultSet.getInt("id"), resultSet.getString("tag")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tags;
    }

    @Override
    public Tag addTag(Tag tag){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select * from tags where tag = ?");
            preparedStatement.setString(1, tag.getTag());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return new Tag(resultSet.getInt("id"), resultSet.getString("tag"));
            }

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO tags (tag) VALUES(?)", generatedColumns);
            preparedStatement.setString(1, tag.getTag());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                tag.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tag;
    }

    @Override
    public Tag deleteTag(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("delete from tags where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return new Tag();
    }
}
