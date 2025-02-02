package com.epsi.epsi_pixel_power_brawl.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

	private final Map<String, WaitingUser> waitingUsers = new ConcurrentHashMap<>();
	private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		System.out.println("Nouvelle connexion WebSocket : " + session.getId());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("Message reçu : " + message.getPayload());

		try {
			// Convertir le message JSON en objet SendTeamInfo
			SendTeamInfo teamInfo = objectMapper.readValue(message.getPayload(), SendTeamInfo.class);

			// Ajouter l'utilisateur à la liste d'attente
			waitingUsers.put(teamInfo.username,
					new WaitingUser(teamInfo.username, teamInfo.pokemonTeam, session.getId()));
			sessions.put(session.getId(), session);

			System.out.println("Utilisateur ajouté en attente : " + teamInfo.username);

			// Répondre au client pour confirmer
			session.sendMessage(new TextMessage("Vous êtes en attente d'un combat, " + teamInfo.username));

		} catch (Exception e) {
			e.printStackTrace();
			session.sendMessage(new TextMessage("Erreur dans le format du message"));
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		// Retirer l'utilisateur de la liste quand il se déconnecte
		sessions.remove(session.getId());
		waitingUsers.values().removeIf(user -> user.getSessionId().equals(session.getId()));

		System.out.println("Connexion WebSocket fermée : " + session.getId());
	}

	public Map<String, WaitingUser> getWaitingUsers() {
		return waitingUsers;
	}

	// Classe pour représenter un utilisateur en attente d'un combat
	static class WaitingUser {
		private String username;
		private SimplifiedPokemonTeam team;
		private String sessionId;

		public WaitingUser(String username, SimplifiedPokemonTeam team, String sessionId) {
			this.username = username;
			this.team = team;
			this.sessionId = sessionId;
		}

		public String getUsername() {
			return username;
		}

		public SimplifiedPokemonTeam getTeam() {
			return team;
		}

		public String getSessionId() {
			return sessionId;
		}
	}

	// Modèle correspondant au JSON reçu
	static class SendTeamInfo {
		public SimplifiedPokemonTeam pokemonTeam;
		public String username;
		public String auth_token;
	}

	static class SimplifiedPokemonTeam {
		public SimplifiedPokemon[] pokemons;
	}

	static class SimplifiedPokemon {
		public int pokedexID;
		public boolean isShiny;
	}
}
