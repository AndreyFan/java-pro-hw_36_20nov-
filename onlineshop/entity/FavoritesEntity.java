package de.telran.onlineshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Favorites")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class FavoritesEntity {
    @Id
    @Column(name = "FavoriteID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteId;

    @Column(name = "UserID")
    private Long userId;

    @Column(name = "ProductID")
    private Long productId;

}