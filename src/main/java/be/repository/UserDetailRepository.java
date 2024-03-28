package be.repository;

import be.config.MySQLConfig;
import be.model.Account;
import be.model.UserDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDetailRepository {

    //CREATE: INSERT
    public void addUserDetail(UserDetail userDetail) {
        String query = "INSERT INTO userdetail (firstName, lastName, email, accountUsername)" +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = MySQLConfig.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userDetail.getFirstName());
            statement.setString(2, userDetail.getLastName());
            statement.setString(3, userDetail.getEmail());
            statement.setString(4, userDetail.getAccount().getUsername());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //READ: SELECT ONE BY ID
    public UserDetail getUserDetail(Account account) {
        String query = "SELECT * FROM userdetail WHERE accountUsername="+account.getUsername();

        try (Connection connection = MySQLConfig.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()){
                return new UserDetail(
                        resultSet.getString("firstname"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email"),
                        (Account) resultSet.getObject("accountUsername")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    //READ: SELECT ALL
    public List<UserDetail> getAllUserDetail() {
        String query = "SELECT * FROM userdetail";

        try (Connection connection = MySQLConfig.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<UserDetail> list = new ArrayList<>();

            while(resultSet.next()){
                list.add(new UserDetail(
                        resultSet.getString("firstname"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email"),
                        (Account) resultSet.getObject("accountUsername"))
                );
            }

            if (list.size()>0)
                return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }



    //UPDATE: UPDATE
    public boolean changeUserDetail(UserDetail userDetail) {
        String query = "UPDATE userdetail SET firstName=?, lastName=?, email=?, accountUsername=? WHERE accountUsername="+userDetail.getAccount().getUsername();

        try (Connection connection = MySQLConfig.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userDetail.getFirstName());
            statement.setString(2, userDetail.getLastName());
            statement.setString(3, userDetail.getEmail());
            statement.setString(4, userDetail.getAccount().getUsername());

            return statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



    //DELETE: DELETE
    public boolean deleteUserDetail(Account account) {
        String query = "DELETE FROM userdetail WHERE accountUsername"+account.getUsername();

        try (Connection connection = MySQLConfig.getConnection()) {

            Statement statement = connection.createStatement();
            return statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



}
