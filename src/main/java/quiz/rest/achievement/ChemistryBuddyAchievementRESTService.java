package quiz.rest.achievement;

import quiz.rest.DTO.AchievementDTO;
import quiz.rest.DTO.LinkAchievementDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/achievement")
public class ChemistryBuddyAchievementRESTService {

    private ChemistryBuddyAchievement chemistryBuddyAchievement;

    public ChemistryBuddyAchievementRESTService() {
        chemistryBuddyAchievement = new ChemistryBuddyAchievement();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //TODO wat is beter hele DTO mee geven of al naar String omzetten
    public Response create(AchievementDTO achievementDTO){
        if(chemistryBuddyAchievement.create(achievementDTO)){
            return Response.status(200).entity(RESTResponseHelper.getSuccesResponse()).build();
        }
        return Response.status(400).entity(RESTResponseHelper.getErrorResponseString()).build();
    }

    @POST
    @Path("/setplayerachievement")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setplayerachievement(LinkAchievementDTO linkAchievementDTO){
        if(chemistryBuddyAchievement.setPlayerAchievement(linkAchievementDTO)){
            return Response.status(200).entity(RESTResponseHelper.getSuccesResponse()).build();
        }
        return Response.status(400).entity(RESTResponseHelper.getErrorResponseString()).build();
    }

    @POST
    @Path("/getachievement")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getachievement(int achievementId){
        if(chemistryBuddyAchievement.getAchievement(achievementId)){
            return Response.status(200).entity(RESTResponseHelper.getSuccesResponse()).build();
        }
        return Response.status(400).entity(RESTResponseHelper.getErrorResponseString()).build();
    }

    @GET
    @Path("/getallachievements")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getallachievements(){
        if(chemistryBuddyAchievement.getAllAchievements() != null){
            //String json = new Gson().toJson(chemistryBuddyAchievement.getAllAchievements());
            return Response.ok(chemistryBuddyAchievement.getAllAchievements()).build();
            //return Response.status(200).entity(RESTResponseHelper.getSuccesResponse()).build();
        }
        return Response.status(400).entity(RESTResponseHelper.getErrorResponseString()).build();
    }

    @POST
    @Path("/getuserachievements")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getuserachievement(int userId){
        if(chemistryBuddyAchievement.getUserAchievement(userId) != null){
            //return Response.status(200).entity(RESTResponseHelper.getSuccesResponse()).build();
            return Response.ok(chemistryBuddyAchievement.getUserAchievement(userId)).build();
        }
        return Response.status(400).entity(RESTResponseHelper.getErrorResponseString()).build();
    }
}
