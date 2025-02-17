
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import static java.awt.SystemColor.text;

public class NASA_Bot extends TelegramLongPollingBot {

    private final String url = "https://api.nasa.gov/planetary/apod?api_key=lIzxjNPfySNhDZjxuCgH0XoEpa6CNu3o86BvY3uf";    //Сюда направляем запрос
    String botName;
    String botToken;

    public NASA_Bot(String botName, String botToken) throws TelegramApiException {
        this.botName = botName;
        this.botToken = botToken;
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        // TODO
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();
            String action = update.getMessage().getText();
            String[] splittedAction = action.split(" ");
            String option = splittedAction[0];

            switch (option){
                case "/start":
                    sendMassage(chatId, "Я бот НАСА, присылаю картинку дня");
                    break;
                case "/help":
                    sendMassage(chatId, "Введите /image, чтоб получить картинку дня");
                    break;
                case "/image":
                    String image = Utils.getImage(url);
                    sendMassage(chatId, image);
                    break;
                case "/date":
                    String date = splittedAction[1];
                    image = Utils.getImage(url + "&date=" + date);
                    sendMassage(chatId, image);
                    break;
                default:
                    sendMassage(chatId, "Я не знаю такой команды. Введите /help");
            }
        }
    }

    void sendMassage (long chatId, String text) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(chatId);
            message.setText(text);

            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
    }

    @Override
    public String getBotUsername() {
        // TODO
        return botName;
    }

    @Override
    public String getBotToken() {
        // TODO
        return botToken;
    }
}
