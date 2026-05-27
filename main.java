import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final String TOKEN = "TOKEN";

    private static final String API_URL =
            "https://api.telegram.org/bot" + TOKEN + "/";

    private static int updateId = 0;
    public static void main(String[] args) {

    System.out.println("Bot iniciado...");

    while (true) {

        try {

            String response = getUpdates();
            
 #Parte 4
     Pattern pattern = Pattern.compile(
        "\"update_id\":(\\d+).?\"chat\":\\{\"id\":(-?\\d+).?\"text\":\"(.*?)\"",
        Pattern.DOTALL
);

Matcher matcher = pattern.matcher(response);

while (matcher.find()) {

    updateId = Integer.parseInt(matcher.group(1)) + 1;

    String chatId = matcher.group(2);
    String message = matcher.group(3);

    message = message.replace("\\/", "/");
