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
    public boolean addUserDetail(UserDetail userDetail) {
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
            return false;
        }
        return true;
    }



    //READ: SELECT ONE BY ID
    public UserDetail getUserDetail(Account account) {
        if (account==null) return null;
        String query = "SELECT * FROM userdetail WHERE accountUsername='"+account.getUsername()+"'";

        try (Connection connection = MySQLConfig.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()){
                return new UserDetail(
                        resultSet.getString("firstname"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email"),
                        account
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
                        new Account(resultSet.getString("accountUsername"),"hidden"))
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
    public int deleteUserDetail(Account account) {

        if (getUserDetail(account)==null){
            return 0;
        }

        String query = "DELETE FROM userdetail WHERE accountUsername='"+account.getUsername()+"'";

        try (Connection connection = MySQLConfig.getConnection()) {

            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            return -1;
        }
        return 1;
    }

}