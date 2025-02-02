package com.epsi.epsi_pixel_power_brawl.websocket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

	private final ConcurrentHashMap<String, WaitingUser> waitingUsers = new ConcurrentHashMap<>();
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		System.out.println("Nouvelle connexion WebSocket : " + session.getId());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("Message reçu : " + message.getPayload());

		try {
			// Désérialiser le JSON en objet SendTeamInfo
			SendTeamInfo teamInfo = objectMapper.readValue(message.getPayload(), SendTeamInfo.class);

			System.out.println(teamInfo.getUsername());

			// Ajouter l'utilisateur à la liste d'attente avec son équipe
			waitingUsers.put(session.getId(),
					new WaitingUser(session, teamInfo.getUsername(), teamInfo.getPokemonTeam()));

			System.out.println(teamInfo.getUsername() + " ajouté à la liste d'attente.");
		} catch (Exception e) {
			System.err.println("Erreur de parsing du message JSON : " + e.getMessage());
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		WaitingUser removedUser = waitingUsers.remove(session.getId());
		if (removedUser != null) {
			System.out.println(removedUser.getUsername() + " retiré de la liste d'attente.");
		}
	}

	// Envoie la liste des utilisateurs en attente toutes les 5 secondes
	@Scheduled(fixedRate = 5000)
	public void sendWaitingUsersList() {
		if (waitingUsers.isEmpty())
			return;

		try {
			UpdateMatchmaking update = new UpdateMatchmaking(waitingUsers.values());
			String userListJson = objectMapper.writeValueAsString(update);

			for (WaitingUser user : waitingUsers.values()) {
				user.getSession().sendMessage(new TextMessage(userListJson));
			}

			System.out.println("Liste des utilisateurs en attente envoyée.");
		} catch (IOException e) {
			System.err.println("Erreur d'envoi des utilisateurs en attente : " + e.getMessage());
		}
	}

	private static class WaitingUser {
		@JsonIgnore
		private final WebSocketSession session;
		private final String username;
		private final SimplifiedPokemonTeam pokemonTeam;

		public WaitingUser(WebSocketSession session, String username, SimplifiedPokemonTeam pokemonTeam) {
			this.session = session;
			this.username = username;
			this.pokemonTeam = pokemonTeam;
		}

		public WebSocketSession getSession() {
			return session;
		}

		public String getUsername() {
			return username;
		}

		public SimplifiedPokemonTeam getPokemonTeam() {
			return pokemonTeam;
		}
	}

	private class UpdateMatchmaking {
		private Collection<WaitingUser> updateWaitingUsers;
		private final String type = "UpdateMatchmaking";

		public UpdateMatchmaking(Collection<WaitingUser> updateWaitingUsers) {
			this.updateWaitingUsers = updateWaitingUsers;
		}
		
		public Collection<WaitingUser> getUpdateWaitingUsers() {
			return updateWaitingUsers;
		}
		
		public String getType() {
            return type;
        }
	}

}
