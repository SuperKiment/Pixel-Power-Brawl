package com.epsi.epsi_pixel_power_brawl.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.epsi.epsi_pixel_power_brawl.model.Utilisateur;

public interface UserRepository extends JpaRepository<Utilisateur, Long> {
  Utilisateur findByUsername(String username);
}