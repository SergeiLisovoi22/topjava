package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println("filteredByStreams:");
        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> result = new ArrayList<>();

        for (UserMeal userMeal : meals) {
//            if (userMeal.getDateTime().isAfter(startTime.atDate(userMeal.getDateTime().toLocalDate())) &&
//                    userMeal.getDateTime().isBefore(endTime.atDate(userMeal.getDateTime().toLocalDate()))) {
//                boolean excess = caloriesPerDay > userMeal.getCalories();
//                result.add(new UserMealWithExcess(LocalDateTime.now(), userMeal.getDescription(),
//                        userMeal.getCalories(), excess));
//            }
            if (userMeal.getDateTime().toLocalTime().compareTo(startTime) >= 0 &&
                    userMeal.getDateTime().toLocalTime().compareTo(endTime) <= 0) {
                boolean excess = caloriesPerDay > userMeal.getCalories();
                result.add(new UserMealWithExcess(LocalDateTime.now(), userMeal.getDescription(),
                        userMeal.getCalories(), excess));
            }
        }
        return result;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> result = new ArrayList<>();

        return result = meals.stream().filter(userMeal -> userMeal.getDateTime().toLocalTime().compareTo(startTime) >= 0 &&
                userMeal.getDateTime().toLocalTime().compareTo(endTime) <= 0).map(userMeal -> new UserMealWithExcess(LocalDateTime.now(),
                userMeal.getDescription(), userMeal.getCalories(), (caloriesPerDay > userMeal.getCalories() ? true :false))).collect(Collectors.toList());
    }
}
