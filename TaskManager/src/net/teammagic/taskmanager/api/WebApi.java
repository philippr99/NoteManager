package net.teammagic.taskmanager.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by s3now on 12/3/16.
 */
public class WebApi {

    private String username,password;
    private String sessionID;

    public WebApi(){

    }

    public void postRequest(String urlI, String ...keyValues){

        if(keyValues.length % 2 != 0) try {
            throw new Exception("Not enough arguments!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            URL url = new URL(urlI);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            PrintStream ps = new PrintStream(con.getOutputStream());
            for(int i = 0; i < keyValues.length; i+=2)
            {
                ps.print(keyValues[i]+"="+keyValues[i+1]);
            }

            InputStream answer = con.getInputStream();

            ps.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
