package de.paul2708.framestats.example;

import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class description.
 *
 * @author Paul2708
 */
public final class PlayerStatistics implements Comparable<PlayerStatistics> {

    // TODO: Add javadoc

    private static final String[] NAMES = { "Paul", "Tom", "Lina", "Leon", "NameWith16Letter" };

    private int rank;

    private final String name;
    private final int kills;
    private final int deaths;
    private final int points;

    private PlayerStatistics(String name, int kills, int deaths, int points) {
        this.name = name;
        this.kills = kills;
        this.deaths = deaths;
        this.points = points;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public int compareTo(@NotNull PlayerStatistics stats) {
        return Integer.compare(stats.points, points);
    }

    public static PlayerStatistics create(int index) {
        Random random = ThreadLocalRandom.current();
        String name = PlayerStatistics.NAMES[random.nextInt(PlayerStatistics.NAMES.length)];
        int kills = random.nextInt(100);
        int deaths = random.nextInt(100);
        int points = index;

        return new PlayerStatistics(name, kills, deaths, points);
    }
}