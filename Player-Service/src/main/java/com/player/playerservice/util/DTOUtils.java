package com.player.playerservice.util;

import com.player.playerservice.model.Player;
import org.types.PlayerDTO;

import java.util.ArrayList;
import java.util.List;

public class DTOUtils {
    public static List<PlayerDTO> mapPlayerListToPlayerDTOList(List<Player> players) {
        List<PlayerDTO> playerDTOList = new ArrayList<>();
        for(Player player: players) {
            playerDTOList.add(new PlayerDTO(player.getId(), player.getFirstName(), player.getLastName(), player.getTeamId(), player.getTeamId()));
        }
        return playerDTOList;
    }
}
