package tests.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SuperHeroModel {

    @JsonProperty("superhero")
    private Superhero superhero;

    public Superhero getSuperhero() {
        return superhero;
    }

    public static class Superhero {
        private String name;
        private String alias;
        private List<String> powers;
        private List<String> weaknesses;
        private List<String> enemies;
        private String origin;

        public String getName() {
            return name;
        }

        public String getAlias() {
            return alias;
        }

        public List<String> getPowers() {
            return powers;
        }

        public List<String> getWeaknesses() {
            return weaknesses;
        }

        public List<String> getEnemies() {
            return enemies;
        }

        public String getOrigin() {
            return origin;
        }
    }
}