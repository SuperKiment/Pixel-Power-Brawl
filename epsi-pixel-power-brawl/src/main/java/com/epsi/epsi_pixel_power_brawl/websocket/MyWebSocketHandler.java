package com.epsi.epsi_pixel_power_brawl.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

	private final ConcurrentHashMap<String, WaitingUser> waitingUsers = new ConcurrentHashMap<>();
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final List<PokemonBattle> battles = new ArrayList<PokemonBattle>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		System.out.println("Nouvelle connexion WebSocket : " + session.getId());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("Message reçu : " + message.getPayload());

		try {
			// Convertir le JSON en objet JsonNode pour accéder au champ "type"
			JsonNode rootNode = objectMapper.readTree(message.getPayload());
			String type = rootNode.get("type").asText();

			switch (type) {
			case "sendTeamInfo":
				// Désérialiser le JSON en objet SendTeamInfo
				SendTeamInfo teamInfo = objectMapper.treeToValue(rootNode, SendTeamInfo.class);
				System.out.println(teamInfo.getUsername());

				// Ajouter l'utilisateur à la liste d'attente avec son équipe
				waitingUsers.put(session.getId(),
						new WaitingUser(session, teamInfo.getUsername(), teamInfo.getPokemonTeam()));

				System.out.println(teamInfo.getUsername() + " ajouté à la liste d'attente.");
				break;

			case "WaitingUserSelected":
				// Désérialiser le JSON en objet ChatMessage
				System.out.println(rootNode);
				WaitingUserSelected waitingUserSelected = objectMapper.treeToValue(rootNode, WaitingUserSelected.class);
				WaitingUser choosingUser = waitingUsers.get(session.getId());
				WaitingUser choosenUser = null;

				for (WaitingUser waitingUser : waitingUsers.values()) {
					if (waitingUser.getUsername().equals(waitingUserSelected.getUsername())) {
						choosenUser = waitingUser;
						break;
					}
				}

				System.out.println(choosingUser.getUsername() + " a choisi " + choosenUser.getUsername());

				BattleBegin battleBeginChoosen = new BattleBegin(choosingUser.getPokemonTeam(),
						choosingUser.getUsername());
				BattleBegin battleBeginChoosing = new BattleBegin(choosenUser.getPokemonTeam(),
						choosenUser.getUsername());

				String battleBeginChoosenStr = objectMapper.writeValueAsString(battleBeginChoosen);
				String battleBeginChoosingStr = objectMapper.writeValueAsString(battleBeginChoosing);

				System.out.println("choosen :");
				System.out.println(battleBeginChoosenStr);

				choosenUser.getSession().sendMessage(new TextMessage(battleBeginChoosenStr));
				choosingUser.getSession().sendMessage(new TextMessage(battleBeginChoosingStr));

				battles.add(new PokemonBattle(choosingUser, choosenUser));

				break;

			case "BattleAction":
				WaitingUser battleUser = waitingUsers.get(session.getId());
				PokemonBattle battle = findUserInBattle(battleUser);
				UserBattleAction userBattleAction = objectMapper.treeToValue(rootNode, UserBattleAction.class);

				if (battle == null) {
					System.err.println("Utilisateur non trouvé dans une bataille.");
					return;
				}

				PokemonBattleUpdate update = null;

				System.out.println(userBattleAction.getAction());

				switch (userBattleAction.getAction()) {
				case "attack":
					update = battle.Turn(BattleAction.ATTACK);
					break;
				case "defense":
					update = battle.Turn(BattleAction.DEFEND);
					break;
				case "heal":
					update = battle.Turn(BattleAction.HEAL);
					break;
				}

				if (update != null) {
					String updateJson = objectMapper.writeValueAsString(update);
					System.out.println("Mise à jour de la bataille : " + updateJson);
					battle.getUser1().getSession().sendMessage(new TextMessage(updateJson));
					battle.getUser2().getSession().sendMessage(new TextMessage(updateJson));
				}
				System.out.println("Fin du tour");

				break;

			default:
				System.err.println("Type de message non reconnu : " + type);
				break;
			}
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
				if (findUserInBattle(user) == null)
					user.getSession().sendMessage(new TextMessage(userListJson));
			}

		} catch (IOException e) {
			System.err.println("Erreur d'envoi des utilisateurs en attente : " + e.getMessage());
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

	private class BattleBegin {
		private SimplifiedPokemonTeam pokemonTeam;
		private String username;
		private final String type = "BattleBegin";

		public BattleBegin(SimplifiedPokemonTeam pokemonTeam, String username) {
			this.pokemonTeam = pokemonTeam;
			this.username = username;
		}

		public SimplifiedPokemonTeam getPokemonTeam() {
			return pokemonTeam;
		}

		public String getUsername() {
			return username;
		}

		public String getType() {
			return type;
		}

	}

	private PokemonBattle findUserInBattle(WaitingUser user) {
		for (PokemonBattle battle : battles) {
			if (battle.getUser1().getUsername().equals(user.getUsername())
					|| battle.getUser2().getUsername().equals(user.getUsername())) {
				return battle;
			}
		}
		return null;
	}
}
