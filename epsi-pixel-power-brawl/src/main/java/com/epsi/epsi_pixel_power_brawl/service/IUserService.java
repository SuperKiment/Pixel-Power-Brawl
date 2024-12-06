package com.epsi.epsi_pixel_power_brawl.service;

import com.epsi.epsi_pixel_power_brawl.dto.RegisterDto;
import com.epsi.epsi_pixel_power_brawl.model.Utilisateur;

public interface IUserService {
    Utilisateur registerNewUserAccount(RegisterDto userDto) throws Exception;
    boolean authenticate(String username, String password);
}
