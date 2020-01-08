package quiz.rest.authentication;

import com.google.gson.Gson;

public class RESTResponseHelper {

    private static final Gson gson = new Gson();

    static String getErrorResponseString(){
        AccountResponse response = new AccountResponse();
        response.setSuccess(false);
        return gson.toJson(response);
    }

    static String getSuccesResponse(){
        AccountResponse response = new AccountResponse();
        response.setSuccess(true);
        return gson.toJson(response);
    }

    static String getSingleAccountResponse(){
        //TODO
        return null;
    }
}

