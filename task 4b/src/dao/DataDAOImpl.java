package dao;

import model.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataDAOImpl implements DataDAO {

    private static final String TABLE_NAME = "task4";

    private final Connection conn;

    public DataDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean create(Data data) {

        int rowInserted = 0;

        try (PreparedStatement createStatement = conn.prepareStatement("INSERT INTO " + TABLE_NAME + " (fisier, suma) VALUES (?, ?)")) {
            createStatement.setString(1, data.getFile());
            createStatement.setInt(2, data.getSum());

            rowInserted = createStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowInserted == 1;
    }

    @Override
    public List<Data> findAll() {

        List<Data> datas = new ArrayList<>();

        try (PreparedStatement queryStatement = conn.prepareStatement("SELECT * FROM " + TABLE_NAME)) {
            ResultSet resultSet = queryStatement.executeQuery();

            while (resultSet.next()) {
                Data data = new Data();

                data.setId(resultSet.getInt("cheie"));
                data.setFile(resultSet.getString("fisier"));
                data.setSum(resultSet.getInt("suma"));

                datas.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datas;
    }

    @Override
    public Data findById(int cheie) {

        Data data = null;

        try(PreparedStatement queryStatement = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE cheie = ?")) {
            queryStatement.setInt(1, cheie);

            ResultSet resultSet = queryStatement.executeQuery();

            if(resultSet.next()){
                data = new Data();

                data.setId(resultSet.getInt("cheie"));
                data.setFile(resultSet.getString("fisier"));
                data.setSum(resultSet.getInt("suma"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public boolean update(Data data) {

        int rowInserted = 0;

        try(PreparedStatement updateStatement = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET fisier = ?, suma = ?, WHERE cheie = ? ")) {
            updateStatement.setString(1, data.getFile());
            updateStatement.setInt(2, data.getSum());
            updateStatement.setInt(3, data.getId());

            rowInserted = updateStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowInserted == 1;
    }

    @Override
    public boolean delete(Data data) {

        int rowDeleted = 0;

        try (PreparedStatement deleteStatement = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE cheie = ?")) {

            deleteStatement.setInt(1, data.getId());

            rowDeleted = deleteStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowDeleted == 1;
    }
}
