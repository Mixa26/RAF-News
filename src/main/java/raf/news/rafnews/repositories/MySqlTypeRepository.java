package raf.news.rafnews.repositories;

import raf.news.rafnews.entities.Type;
import raf.news.rafnews.entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlTypeRepository extends MySqlAbstractRepository implements TypeRepository{

    @Override
    public List<Type> allTypes() {
        List<Type> types = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from type");
            while (resultSet.next()) {
                types.add(new Type(resultSet.getInt("id"), resultSet.getString("userType")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return types;
    }
}
