package esisa.ac.ma.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import esisa.ac.ma.entities.Favorite;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM Favorites_Table")
    List<Favorite> getAll();

    @Insert
    void add(Favorite favorite);
    @Delete
    void deleteByFav(Favorite favorite);


    @Query("DELETE FROM Favorites_Table WHERE contactName = :contactName")
    void deleteByContactName(String contactName);
    // Modify the parameter type to Favorite
    @Query("DELETE FROM Favorites_Table")
    void clear();


}

