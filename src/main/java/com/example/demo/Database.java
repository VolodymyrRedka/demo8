package com.example.demo;

import java.sql.*;

public class Database {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "RedkaVolodymyr1991";
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement statement = con.createStatement();

            statement.executeUpdate("drop table if exists Books");
            statement.executeUpdate("drop table if exists Users1");

            statement.executeUpdate("create table Books (id SERIAL PRIMARY KEY, name varchar(30) NOT NULL)");
            statement.executeUpdate("insert into Books (name) values ('Inferno')");
            statement.executeUpdate("insert into Books (name) values ('Solomon key')");
            statement.executeUpdate("insert into Books (name) values ('Beautiful')");
            statement.executeUpdate("insert into Books (name) values ('Step by step')");


            statement.executeUpdate("create table Users1 (id SERIAL PRIMARY KEY, name varchar(30) NOT NULL, password varchar(30) NOT NULL)");
            statement.executeUpdate("insert into Users1 (name, password) values ('Max', '123')");
            statement.executeUpdate("insert into Users1 (name, password) values ('John', '456')");

            ResultSet resultSet = statement.executeQuery("select * from Books");
            System.out.println("Книги:");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getString(2));
                System.out.println("______________");
            }
            System.out.println("______________");

            ResultSet resultSet2 = statement.executeQuery("select name from Books where id=4");
            System.out.println("Назва першої книги:");
            while (resultSet2.next()) {
                System.out.println(resultSet2.getString(1));
            }
            System.out.println("______________");

            String userId1 = "1";
            ResultSet resultSet3 = statement.executeQuery("select * from Users1 where id = '" + userId1 + "'");
            System.out.println("Користувачі:");
            while (resultSet3.next()) {
                System.out.println("userName: " + resultSet3.getString("name"));
                System.out.println("userPassword: " + resultSet3.getString("password"));
                System.out.println("______________");
            }

            String userId2 = "2";
            PreparedStatement preparedStatement = con.prepareStatement("select * from Users1 where id = ?");
            preparedStatement.setInt(1, Integer.parseInt(userId2));
            ResultSet resultSet4 = preparedStatement.executeQuery();
            System.out.println("Користувач:");
            while (resultSet4.next()) {
                System.out.println("userName: " + resultSet4.getString("name"));
                System.out.println("userPassword: " + resultSet4.getString("password"));
                System.out.println("______________");
            }
            CallableStatement stmt = con.prepareCall("{call get_devices_by_user_id(?)}");
            stmt.setInt(1, 56); // передаємо user_id
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String mac_address = rs.getString("mac_address");
                System.out.println("ID: " + id + ", Name: " + name + ", MAC Address: " + mac_address);
                System.out.println("______________");
            }
            CallableStatement cs = con.prepareCall("{call getUsersAndDevices()}");
            ResultSet rs1 = cs.executeQuery();
            while (rs1.next()) {
                String firstName = rs1.getString("first_name");
                String lastName = rs1.getString("last_name");
                String phoneNumber = rs1.getString("phone_number");
                String gender = rs1.getString("gender");
                String deviceName = rs1.getString("name");
                String macAddress = rs1.getString("mac_address");
                System.out.println(firstName + " " + lastName + " (" + gender + ")");
                System.out.println("Phone: " + phoneNumber);
                System.out.println("Device: " + deviceName + " (" + macAddress + ")");
                System.out.println("______________");
            }
            //PreparedStatement preparedStatement2 = con.prepareStatement("",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            Statement statement2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet5 = statement2.executeQuery("SELECT * FROM Books"); // виправлено назву змінної і запит
            if(resultSet5.next()) {
                System.out.println(resultSet5.getString("name"));
            }
            if(resultSet5.next()) {
                System.out.println(resultSet5.getString("name"));
            }

            resultSet5 = statement2.executeQuery("SELECT name FROM Books"); // виправлено назву змінної і запит
            if(resultSet5.next()) { // додано умову перевірки наявності запису
                System.out.println(resultSet5.getString("name"));
            }
            System.out.println("______________");

            if (resultSet5.relative(2))
                System.out.println(resultSet5.getString("name"));
            System.out.println("______________");
            if (resultSet5.relative(-2))
                   System.out.println(resultSet5.getString("name"));
            System.out.println("______________");
            if (resultSet5.absolute(3))
                System.out.println(resultSet5.getString("name"));
            System.out.println("______________");
            if (resultSet5.first())
                System.out.println(resultSet5.getString("name"));
            System.out.println("______________");
            if (resultSet5.last())
                System.out.println(resultSet5.getString("name"));
            System.out.println("______________");


            //PreparedStatement preparedStatement2 = con.prepareStatement("sgl",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Statement statement3 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet6 = statement3.executeQuery("select * from Books");
            while (resultSet6.next()) {
                System.out.println(resultSet6.getInt("id"));
                System.out.println(resultSet6.getString("name"));
            }
            System.out.println("______________");
            resultSet6.last();
            resultSet6.updateString("name", "new Value");
            resultSet6.updateRow();
            resultSet6.moveToInsertRow();
            resultSet6.updateInt("id", 5);
            resultSet6.updateString("name", "inserted row");
            resultSet6.insertRow();
            resultSet6.moveToCurrentRow();
            resultSet6.updateString("name", "updated inserted row");
            resultSet6.updateRow();
            resultSet6.absolute(2);
            resultSet6.deleteRow();
            resultSet6.beforeFirst();
            while (resultSet6.next()) {
                System.out.println(resultSet6.getInt("id"));
                System.out.println(resultSet6.getString("name"));
            }
            System.out.println("______________");
            DatabaseMetaData databaseMetaData = con.getMetaData();
            ResultSet resultSet7 = databaseMetaData.getTables(null,null,null, new String[]{"Teble"});
            while (resultSet7.next()){
                System.out.println(resultSet7.getString(1));
            }
            System.out.println("______________");
            //0Statement statement8 = con.createStatement();
            //statement8.execute("select * from Books");
            ResultSet resultSet8 = statement.executeQuery("select * from Books");
            ResultSetMetaData resultSetMetaData = resultSet8.getMetaData();
            for (int i=1;i<=resultSetMetaData.getColumnCount();i++){
                System.out.println(resultSetMetaData.getColumnLabel(i));
                System.out.println(resultSetMetaData.getColumnType(i));
            }
            System.out.println("______________");

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}