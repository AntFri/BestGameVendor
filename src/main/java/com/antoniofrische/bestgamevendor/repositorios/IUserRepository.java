package com.antoniofrische.bestgamevendor.repositorios;

import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    public UserEntity findByEmail(String email);

    @Query("SELECT u FROM UserEntity u WHERE u.nombre LIKE %:searchTerm% OR u.apellido LIKE %:searchTerm% OR u.email LIKE %:searchTerm% OR u.region.nombre LIKE %:searchTerm%")
    List<UserEntity> search(@Param("searchTerm") String searchTerm);
}
