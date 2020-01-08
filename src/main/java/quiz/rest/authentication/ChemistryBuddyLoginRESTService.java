package quiz.rest.authentication;

import quiz.rest.DTO.LoginDTO;
import quiz.rest.DTO.RegisterDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class ChemistryBuddyLoginRESTService {

    private ChemistryBuddyLogin chemistryBuddyLogin;

public ChemistryBuddyLoginRESTService() {
        chemistryBuddyLogin = new ChemistryBuddyLogin();
    }

//    @POST
//    @Path("/login")
//    public Response login(@QueryParam("email") String email, @QueryParam("password") String password){
//        if(chemistryBuddyLogin.login(email, password)){
//            return Response.status(200).entity(RESTResponseHelper.getSuccesResponse()).build();
//        }
//        return Response.status(400).entity(RESTResponseHelper.getErrorResponseString()).build();
//    }
//
//    @POST
//    @Path("/register")
//    public Response register(@QueryParam("email") String email, @QueryParam("password") String password){
//        if(chemistryBuddyLogin.register(email, password)){
//            return Response.status(200).entity(RESTResponseHelper.getSuccesResponse()).build();
//        }else
//            return Response.status(500).entity(RESTResponseHelper.getErrorResponseString()).build();
//    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //TODO wat is beter hele DTO mee geven of al naar String omzetten
    public Response login(LoginDTO loginDTO){
        if(chemistryBuddyLogin.login(loginDTO)){
            return Response.status(200).entity(RESTResponseHelper.getSuccesResponse()).build();
        }
        return Response.status(400).entity(RESTResponseHelper.getErrorResponseString()).build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(RegisterDTO registerDTO){
        if(chemistryBuddyLogin.register(registerDTO)){
            return Response.status(200).entity(RESTResponseHelper.getSuccesResponse()).build();
        }else
            return Response.status(500).entity(RESTResponseHelper.getErrorResponseString()).build();
    }
}
