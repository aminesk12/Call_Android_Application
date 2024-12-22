package esisa.ac.ma.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import esisa.ac.ma.entities.Favorite;

@Database(entities = {Favorite.class}, version = 1)
public abstract class FavoriteDB extends RoomDatabase {
    public abstract FavoriteDao favoriteDao();
}

