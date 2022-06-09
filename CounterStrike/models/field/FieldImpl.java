package CounterStriker.src.CounterStriker.models.field;

import CounterStriker.src.CounterStriker.common.OutputMessages;
import CounterStriker.src.CounterStriker.models.players.Player;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FieldImpl implements Field{
    @Override
    public String start(Collection<Player> players) {

        List<Player> terrorists = getPlayersByType(players, "Terrorist");
        List<Player> counterTerrorists = getPlayersByType(players, "CounterTerrorist");


        while (counterTerrorists.stream().anyMatch(Player::isAlive) && terrorists.stream().anyMatch(Player::isAlive)) {
            for (Player terrorist : terrorists) {
                for (Player contraTerrorist : counterTerrorists) {
                    contraTerrorist.takeDamage(terrorist.getGun().fire());
                }
            }

            counterTerrorists = counterTerrorists.stream().filter(Player::isAlive).collect(Collectors.toList());

            for (Player contraTerrorist : counterTerrorists) {
                for (Player terrorist : terrorists) {
                    terrorist.takeDamage(contraTerrorist.getGun().fire());
                }
            }

            terrorists = terrorists.stream().filter(Player::isAlive).collect(Collectors.toList());
        }

        return terrorists.stream().anyMatch(Player::isAlive) ? OutputMessages.TERRORIST_WINS : OutputMessages.COUNTER_TERRORIST_WINS;
    }

    private List<Player> getPlayersByType(Collection<Player> players, String type) {
        return players
                .stream()
                .filter(player -> player
                        .getClass()
                        .getSimpleName()
                        .equals(type))
                .collect(Collectors.toList());
    }
}
