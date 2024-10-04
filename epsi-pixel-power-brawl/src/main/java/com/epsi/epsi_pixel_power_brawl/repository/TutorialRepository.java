package com.epsi.epsi_pixel_power_brawl.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.epsi.epsi_pixel_power_brawl.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
  List<Tutorial> findByPublished(boolean published);

  List<Tutorial> findByTitleContaining(String title);
}