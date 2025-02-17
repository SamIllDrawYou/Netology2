
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, TelegramApiException {
        new NASA_Bot("weatherkek_bot", "7832755220:AAFMxfRmuLqPMsEg1qu61sfXzhbf_cNizhI");
        new  NASA_Bot("SaymurBot", "7344109715:AAFbBaDMxhtd9OwfJYaK3LULUo5UurSVEnY");
    }
}