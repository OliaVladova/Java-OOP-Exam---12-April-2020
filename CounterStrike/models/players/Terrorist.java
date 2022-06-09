package CounterStriker.src.CounterStriker.models.players;

import CounterStriker.src.CounterStriker.models.guns.Gun;

public class Terrorist extends PlayerImpl{
    public Terrorist(String username, int health, int armor, Gun gun) {
        super(username, health, armor, gun);
    }
}
