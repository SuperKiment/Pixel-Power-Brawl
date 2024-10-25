package com.epsi.epsi_pixel_power_brawl.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.epsi.epsi_pixel_power_brawl.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findByEmail(String email);

  List<User> findByPassword(String password);
}