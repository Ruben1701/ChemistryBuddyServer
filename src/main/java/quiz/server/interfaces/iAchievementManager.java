package quiz.server.interfaces;

import quiz.server.model.Player;
import quiz.server.model.Quiz;

public interface iAchievementManager {

    void achievementCriteriaCheck(Player player);

    void achievement(Quiz finishedQuiz);

    void setPlayerAchievement(Player player, String achievementid);

}
