package net.teammagic.taskmanager.api;

import java.io.*;
import java.net.*;
import java.rmi.UnexpectedException;

/**
 * Created by s3now on 12/3/16.
 */
public class WebApi {

    private String username,password;
    private String sessionID;

    public WebApi(){
       //example:  String result = postRequest("http://localhost/backend/register_login.php",new PostArgument<Integer>("isLogin",1),new PostArgument<String>("username","test"),new PostArgument<String>("password","hase"));
    }

    public String postRequest(String urlI, PostArgument ...postArguments){
        try {
            URL url = new URL(urlI);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");

            PrintStream ps = new PrintStream(con.getOutputStream());
            for(PostArgument argue : postArguments)
                ps.print(argue.getKey()+"="+argue.getValue()+"&");
            ps.flush();

            InputStream answer = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(answer));
            String result = reader.readLine();

            ps.close();
            reader.close();
            answer.close();
            con.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            throw new UnexpectedException("No answer, maybe server down!");
        } catch (UnexpectedException e) {
            e.printStackTrace();
        }

        return null;
    }


}
