package com.epsi.epsi_pixel_power_brawl.service;

import com.epsi.epsi_pixel_power_brawl.dto.UserDto;
import com.epsi.epsi_pixel_power_brawl.model.Utilisateur;

public interface IUserService {
    Utilisateur registerNewUserAccount(UserDto userDto) throws Exception;
}
