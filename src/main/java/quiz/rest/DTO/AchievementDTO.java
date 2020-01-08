package quiz.rest.DTO;

public class AchievementDTO {
    private String name;
    private int points;

    public AchievementDTO(String Name, int Points){
        this.name = Name;
        this.points = Points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }
}
