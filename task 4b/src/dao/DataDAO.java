package dao;

import model.Data;

import java.util.List;

public interface DataDAO {

    boolean create(Data data);      // Create

    List<Data> findAll();           // Read

    Data findById(int cheie);       // Read

    boolean update(Data data);      // Update

    boolean delete(Data data);      // Delete

}
