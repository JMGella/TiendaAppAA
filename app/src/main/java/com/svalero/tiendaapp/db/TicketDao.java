package com.svalero.tiendaapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TicketDao {
    @Query("SELECT * FROM ticket ORDER BY expirationDate ASC")
    List<Ticket> getAll();


    @Query("SELECT * FROM ticket WHERE id = :id")
    Ticket getById(int id);

    @Query("SELECT * FROM ticket WHERE name LIKE '%' || :searchTerm || '%'")
    List<Ticket> search(String searchTerm);

    @Query("SELECT * FROM ticket WHERE category = :category")
    List<Ticket> findByCategory(String category);

    @Insert
    void insert(Ticket ticket);

    @Update
    void update(Ticket ticket);

    @Query("DELETE FROM ticket WHERE id = :id")
    void deleteById(int id);
}
