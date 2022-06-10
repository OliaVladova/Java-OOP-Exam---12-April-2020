package CounterStriker.src.CounterStriker.repositories;

import CounterStriker.src.CounterStriker.common.ExceptionMessages;
import CounterStriker.src.CounterStriker.models.guns.Gun;


import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GunRepository implements Repository<Gun> {
    private Map<String, Gun> guns;

    public GunRepository() {
        this.guns = new LinkedHashMap<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return Collections.unmodifiableCollection(this.guns.values());
    }

    @Override
    public void add(Gun model) {
        if (model == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_GUN_REPOSITORY);
        }
        this.guns.put(model.getName(), model);
    }

    @Override
    public boolean remove(Gun model) {
        return this.guns.remove(model.getName()) != null;
    }

    @Override
    public Gun findByName(String name) {
        return this.guns.get(name);
    }
}