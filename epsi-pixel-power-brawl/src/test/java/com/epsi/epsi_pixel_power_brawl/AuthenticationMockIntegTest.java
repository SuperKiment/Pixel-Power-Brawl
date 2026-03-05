package com.epsi.epsi_pixel_power_brawl;

import static org.mockito.Mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.epsi.epsi_pixel_power_brawl.model.Utilisateur;
import com.epsi.epsi_pixel_power_brawl.repository.UserRepository;
import com.epsi.epsi_pixel_power_brawl.service.UserService;
import com.epsi.epsi_pixel_power_brawl.util.jwt.JwtUtil;

@ExtendWith(MockitoExtension.class)
public class AuthenticationMockIntegTest {
	
	
    @Value("${test.username}")
    private String usernameCorrect;

    @Value("${test.password}")
    private String passwordCorrect;

	@Mock
	private UserRepository userRepository;

	@Mock
	private JwtUtil jwtUtil;
	
    @Mock 
    private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserService userService;

	@Test
	public void testAuthenticate_Success() {
        String username = "testuser";
        String password = "testpassword";
        String encodedPassword = "encodedPassword";
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setUsername(username);
		utilisateur.setPassword(encodedPassword);
		utilisateur.setRoles(List.of("ROLE_USER"));
		
	    when(userRepository.findByUsername(username)).thenReturn(utilisateur);
	    when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);
	    
		boolean loggedIn = userService.authenticate(utilisateur.getUsername(), password);

		assertTrue(loggedIn, "User should be logged in");

		verify(userRepository, times(1)).findByUsername(username);
        verify(passwordEncoder, times(1)).matches(password, encodedPassword);
	}

	@Test
	public void testAuthenticate_Failure() {
		String username = "testuser";
		String password = "wrongpassword";

		when(userRepository.findByUsername(username)).thenReturn(null);

		assertThrows(UsernameNotFoundException.class, () -> {
			userService.authenticate(username, password);
		}, "Authentication should fail for invalid credentials");

		verify(userRepository, times(1)).findByUsername(username);
	    verify(passwordEncoder, never()).matches(any(), any());
	}
}
