package core;

import levels.Freshman;
import levels.Level;

public class Player {

    private static float GPA;
    private static float coolness;
    private static int grade;
    private static Level currentLevel;
    private static int stage;

    public Player() {
        GPA = 4;
        coolness = 100;
        grade = 9;
        currentLevel = new Freshman();
        stage = 1;
    }

    public static float getGPA() {
        return GPA;
    }

    public static float getCoolness() {
        return coolness;
    }

    public static int getGrade() {
        return grade;
    }

    public static Level getCurrentLevel() {
        return currentLevel;
    }

    public static int stage() {
        return stage;
    }

}
