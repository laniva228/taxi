package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaxiDAO {

    public List<String> getAllTrips() {

        List<String> trips = new ArrayList<>();

        String sql = """
                SELECT trips.id, drivers.name AS driver,
                passengers.name AS passenger,
                trips.from_address, trips.to_address, trips.price
                FROM trips
                JOIN drivers ON trips.driver_id = drivers.id
                JOIN passengers ON trips.passenger_id = passengers.id
                """;

        try (
                Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql)
        ) {

            while (result.next()) {

                String trip = "ID: " + result.getInt("id") +
                        ", Водитель: " + result.getString("driver") +
                        ", Пассажир: " + result.getString("passenger") +
                        ", Откуда: " + result.getString("from_address") +
                        ", Куда: " + result.getString("to_address") +
                        ", Цена: " + result.getDouble("price");

                trips.add(trip);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trips;
    }


    public void addTrip(int driverId, int passengerId,
                        String from, String to, double price) {

        String sql = """
                INSERT INTO trips
                (driver_id, passenger_id, from_address, to_address, price)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, driverId);
            statement.setInt(2, passengerId);
            statement.setString(3, from);
            statement.setString(4, to);
            statement.setDouble(5, price);

            statement.executeUpdate();

            System.out.println("Поездка добавлена!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void findTrip(int id) {

        String sql = "SELECT * FROM trips WHERE id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                System.out.println("ID: " + result.getInt("id"));
                System.out.println("Водитель ID: " + result.getInt("driver_id"));
                System.out.println("Пассажир ID: " + result.getInt("passenger_id"));
                System.out.println("Откуда: " + result.getString("from_address"));
                System.out.println("Куда: " + result.getString("to_address"));
                System.out.println("Цена: " + result.getDouble("price"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateTrip(int id, String from, String to, double price) {

        String sql = """
                UPDATE trips
                SET from_address = ?, to_address = ?, price = ?
                WHERE id = ?
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, from);
            statement.setString(2, to);
            statement.setDouble(3, price);
            statement.setInt(4, id);

            statement.executeUpdate();

            System.out.println("Поездка изменена!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteTrip(int id) {

        String sql = "DELETE FROM trips WHERE id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            statement.executeUpdate();

            System.out.println("Поездка удалена!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}