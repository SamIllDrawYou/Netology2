
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {


        String url = "https://api.nasa.gov/planetary/apod?api_key=lIzxjNPfySNhDZjxuCgH0XoEpa6CNu3o86BvY3uf";    //Сюда направляем запрос

        ObjectMapper mapper = new ObjectMapper();

        CloseableHttpClient httpclient = HttpClients.createDefault();   //Создали переменную
        HttpGet request = new HttpGet(url); //Запрос
        CloseableHttpResponse response = httpclient.execute(request);   //Ответ

        NasaAnswer answer = mapper.readValue(response.getEntity().getContent(),NasaAnswer.class);

        String imageUrl = answer.url;
        String[] splittedImageUrl = imageUrl.split("/");
        String filename = splittedImageUrl[splittedImageUrl.length - 1]; //Обращаемся к последнему элементу

        HttpGet imageRequest = new HttpGet(imageUrl);
        CloseableHttpResponse image = httpclient.execute(imageRequest);

        FileOutputStream fos = new FileOutputStream("Images/"+filename);
        image.getEntity().writeTo(fos);


    }
}
