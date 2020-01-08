package quiz.rest.DTO;

public class LinkAchievementDTO {
    private int userid;
    private int achievementid;

    public LinkAchievementDTO(int UserId, int AchievementId){
        this.userid= UserId;
        this.achievementid = AchievementId;
    }

    public int getUserid() {
        return userid;
    }

    public int getAchievementid() {
        return achievementid;
    }
}
