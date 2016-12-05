package net.teammagic.taskmanager.api;

import java.io.*;
import java.net.*;
import java.rmi.UnexpectedException;

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
