package thomas.thomas;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by keechengheng on 18/7/15.
 */
public interface LumiApiService {



    @GET("/logins/{login}")
    public void getLoginForID(@Path("login") int ID, Callback<Login> response);

}
