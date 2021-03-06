package CounterStriker.src.CounterStriker.repositories;

import CounterStriker.src.CounterStriker.common.ExceptionMessages;
import CounterStriker.src.CounterStriker.models.players.Player;

import java.util.*;

public class PlayerRepository implements Repository<Player> {
private Map<String, Player> players;

    public PlayerRepository() {
        this.players = new LinkedHashMap<>();
    }

    @Override
    public Collection<Player> getModels() {
        return Collections.unmodifiableCollection(this.players.values());
    }

    @Override
    public void add(Player model) {
        if (model == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_REPOSITORY);
        }
        this.players.put(model.getUsername(), model);
    }

    @Override
    public boolean remove(Player model) {
        return this.players.remove(model.getUsername()) != null;
    }

    @Override
    public Player findByName(String name) {
        return this.players.get(name);
    }
}
