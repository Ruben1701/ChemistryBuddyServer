package quiz.server.logic;

import quiz.server.interfaces.iAchievementManager;
import quiz.server.model.Player;
import quiz.server.model.Quiz;

import javax.websocket.Session;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AchievementManager implements iAchievementManager {

    @Override
    public void achievementCriteriaCheck(Player player){

        //composite unique index in database so no need to check if player already has the achievement

               //play a game
                if (player != null){
                    setPlayerAchievement(player, "1");
                }

                //win a game
                //TODO
//                if (){
//
//                    Form form = new Form();
//                    form.param("UserId", player.getUserid());
//                    form.param("AchievementId", "2");
//                    webTarget.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
//
//                }




                //score 300 points

                //score 300 points
                if (player.getPoints() >= 300){
                    setPlayerAchievement(player, "3");
                }

                //get a question right
                if (player.getPoints() >= 100){

                    setPlayerAchievement(player, "4");
                }
    }

    @Override
    public void achievement(Quiz finishedQuiz){
        for (Player player : finishedQuiz.getPlayers()){
            achievementCriteriaCheck(player);
        }
    }

    @Override
    public void setPlayerAchievement(Player player, String achievementid){
        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target("http://localhost:8091/achievement/setplayerachievement");

        Form form = new Form();
        form.param("UserId", player.getUserid());
        form.param("AchievementId", achievementid);
        Response response = webTarget.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
        System.out.println(response.getStatus());
    }
}
