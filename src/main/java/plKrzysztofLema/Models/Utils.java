package plKrzysztofLema.Models;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Utils {
    public static String makeHttpRequest(String url){
        try {
            URLConnection urlConnection = (HttpURLConnection) new URL(
                    url)
                    .openConnection();

            StringBuilder builder = new StringBuilder();
            int read = 0;
            while ((read = urlConnection.getInputStream().read())!=-1){
                builder.append((char)read);
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
