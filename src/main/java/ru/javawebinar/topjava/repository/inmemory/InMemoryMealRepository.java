package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collections;
import java.util.List;

@Repository
public class InMemoryMealRepository implements MealRepository{
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);


    @Override
    public Meal save(Meal meal) {
        log.info("save {}", meal);
        return meal;
    }

    @Override
    public boolean delete(int id) {
        log.info("delete{}", id);
        return true;
    }

    @Override
    public Meal get(int id) {
        log.info("get {}", id);
        return null;
    }

    @Override
    public List<Meal> getAll() {
        log.info("getAll");
        return Collections.emptyList();
    }
}
