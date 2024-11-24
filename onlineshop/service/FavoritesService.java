package de.telran.onlineshop.service;

import de.telran.onlineshop.entity.FavoritesEntity;
import de.telran.onlineshop.model.Favorite;
import de.telran.onlineshop.repository.FavoritesRepository;
import de.telran.onlineshop.repository.UsersRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritesService {

    private final FavoritesRepository favoritesRepository;
    List<Favorite> favoriteList;

    @PostConstruct
    public void initFavorites() {
        FavoritesEntity favorite1 = new FavoritesEntity(1L, 1L,5L);
        favoritesRepository.save(favorite1);

        FavoritesEntity favorite2 = new FavoritesEntity(2L, 2L,10L);
        favoritesRepository.save(favorite2);

        FavoritesEntity favorite3 = new FavoritesEntity(3L, 4L,4L);
        favoritesRepository.save(favorite3);

        System.out.println("Выполняем логику при создании объекта " + this.getClass().getName());
    }

    @GetMapping
    public List<Favorite> getAllFavorites() {
        return favoriteList;
    }

    @GetMapping(value = "/find/{id}")
    public Favorite getFavoriteById(@PathVariable Long id) { ///categories/find/3
        return favoriteList.stream()
                .filter(favorite -> favorite.getFavoriteID() == id)
                .findFirst()
                .orElse(null);
    }

    @PostMapping()
    public boolean addFavorite(@RequestBody Favorite favorite) {
        return favoriteList.add(favorite);
    }

    @PutMapping()
    public Favorite updateFavorite(@RequestBody Favorite updFavorite) {
        Favorite result = favoriteList.stream()
                .filter(favorite -> favorite.getFavoriteID() == updFavorite.getFavoriteID())
                .findFirst()
                .orElse(null);
        if (result != null) {
            result.setProductID(updFavorite.getProductID());
            result.setUserID(updFavorite.getUserID());
        }
        return result;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteFavorite(@PathVariable Long id) {
        Iterator<Favorite> iterator = favoriteList.iterator();
        while (iterator.hasNext()) {
            Favorite favorite = iterator.next();
            if (favorite.getFavoriteID() == id) {
                iterator.remove();
            }
        }
    }

    @PreDestroy
    void destroyFavoritesList() {
        favoriteList.clear();
        System.out.println("Выполняем логику при окончании работы с  объектом " + this.getClass().getName());
    }

}
