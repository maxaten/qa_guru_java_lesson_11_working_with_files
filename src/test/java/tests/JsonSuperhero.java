package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.models.SuperHeroModel;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class JsonSuperhero {

    private ClassLoader cl = JsonSuperhero.class.getClassLoader();

    @Test
    void parseJson() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = cl.getResourceAsStream("superhero.json")) {
            assert is != null;
            try (InputStreamReader isr = new InputStreamReader(is)) {
                SuperHeroModel superheroModel = objectMapper.readValue(isr, SuperHeroModel.class);
                SuperHeroModel.Superhero superhero = superheroModel.getSuperhero();

                // Проверьте значения полей объекта superhero
                Assertions.assertEquals("Человек-Паук", superhero.getName());
                Assertions.assertEquals("Питер Паркер", superhero.getAlias());
                Assertions.assertEquals(List.of(
                        "Способность прилипать к стенам и потолкам",
                        "Сверхчеловеческая сила и выносливость",
                        "Быстрые рефлексы и острая чуть",
                        "Паучье чутье и способность к наружному восприятию опасности",
                        "Использование паутины как ловушки и для перемещения по городу"
                ), superhero.getPowers());
                Assertions.assertEquals(List.of(
                        "Эмоциональные трудности из-за потери дяди Бена",
                        "Иногда сомневается в своих способностях"
                ), superhero.getWeaknesses());
                Assertions.assertEquals(List.of(
                        "Зеленый Гоблин",
                        "Доктор Осьминог",
                        "Веном"
                ), superhero.getEnemies());
                Assertions.assertEquals("Питер Паркер был укушен радиоактивным пауком на научной выставке, после чего получил свои суперсилы и начал бороться со злом в Нью-Йорке.", superhero.getOrigin());
            }
        }
    }
}