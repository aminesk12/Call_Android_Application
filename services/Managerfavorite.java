package esisa.ac.ma.services;

import android.content.Context;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import androidx.room.*;
import esisa.ac.ma.dao.FavoriteDB;
import esisa.ac.ma.dao.FavoriteDao;
import esisa.ac.ma.entities.Contact;
import esisa.ac.ma.entities.Favorite;

public class Managerfavorite {
    private FavoriteDB db;
    private FavoriteDao favoriteDao;
    private ExecutorService executorService;

    public Managerfavorite(Context context) {
        db= Room.databaseBuilder(context,FavoriteDB.class, "Favorites_Table.db").build();
        favoriteDao= db.favoriteDao();
        executorService= Executors.newSingleThreadExecutor();
    }

    public List<Favorite> getAll(){
        //One can use lambda
        Future<List<Favorite>> results=executorService.submit(new Callable<List<Favorite>>() {
            @Override
            public List<Favorite> call() throws Exception {
                return favoriteDao.getAll();
            }
        });
        try {
            return results.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void add(Favorite favorite){
        executorService.execute(()->favoriteDao.add(favorite));
    }
    public void deleteByName(String FirstName){
        executorService.execute(() -> favoriteDao.deleteByContactName(FirstName));
    }
    public void delete(Favorite favorite){
        executorService.execute(() -> favoriteDao.deleteByFav(favorite));
    }



    public void addToFavorites(Contact contact) {
        executorService.execute(() -> {
            Favorite favorite = new Favorite();
            favorite.setContactName(contact.getFirstName());
            favorite.setContactPhone(contact.getPhones().stream().findFirst().orElse(""));



            System.out.println("icon Passed and data is " + contact.getPhones().stream().findFirst().orElse("")
);

            List<Favorite> allFavorites = favoriteDao.getAll();
            boolean exists = false;
            for (Favorite existingFavorite : allFavorites) {
                if (existingFavorite.getContactName().equals(favorite.getContactName())) {
                    exists = true;
                    break;
                }
            }

            // Add the Favorite object to the database if it doesn't already exist
            if (!exists) {
                favoriteDao.add(favorite);
            } else {
                favoriteDao.deleteByContactName(favorite.getContactName());
            }
        });
    }

}
