package net.teammagic.taskmanager.api;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebApi {

    String urlI;

    public WebApi(String urlI) {
        this.urlI = urlI;
    }

    public String postRequest(PostArgument... postArguments) {
        try {
            URL url = new URL(urlI);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            PrintStream ps = new PrintStream(connection.getOutputStream());
            for (PostArgument argue : postArguments)
                ps.print(argue.getKey() + "=" + argue.getValue() + "&");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = reader.readLine();

            ps.close();
            reader.close();
            connection.disconnect();

            return result;
        } catch (IOException e) {
            System.out.println("postRequestError: " + e.getMessage());
        }
        return null;
    }

    public String textToMD5(String input) {
        throw new NotImplementedException();
    }
}
