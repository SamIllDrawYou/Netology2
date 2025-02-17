import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;

public class Utils {
    public static String getImage(String nasaUrl){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        try {
            HttpGet request = new HttpGet(nasaUrl); //Запрос
            CloseableHttpResponse response = httpclient.execute(request);   //Ответ
            NasaAnswer answer = mapper.readValue(response.getEntity().getContent(),NasaAnswer.class);
            String imageUrl = answer.url;
            return imageUrl;
        } catch (IOException e) {
            return "";
        }
    }
}
