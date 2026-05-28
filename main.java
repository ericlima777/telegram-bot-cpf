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

    private static final String TOKEN = "8964182455:AAFrkmEMMZnNqBuX7PPd_nf6KzSAGzTpx4w";

    private static final String API_URL =
            "https://api.telegram.org/bot" + TOKEN + "/";

    private static int updateId = 0;

    public static void main(String[] args) {

        System.out.println("Bot iniciado...");

        while (true) {

            try {

                String response = getUpdates();

                Pattern pattern = Pattern.compile(
                        "\"update_id\":(\\d+).*?\"chat\":\\{\"id\":(-?\\d+).*?\"text\":\"(.*?)\"",
                        Pattern.DOTALL
                );

                Matcher matcher = pattern.matcher(response);

                while (matcher.find()) {

                    updateId = Integer.parseInt(matcher.group(1)) + 1;

                    String chatId = matcher.group(2);
                    String message = matcher.group(3);

                    message = message.replace("\\/", "/");

                    System.out.println("Mensagem recebida: " + message);

                    String resposta;

                    if (message.equals("/start")) {

                        resposta =
                                "🤖 Olá! Envie um CPF para validação.";

                    } else {

                        if (validarCPF(message)) {
                            resposta = "✅ CPF válido!";
                        } else {
                            resposta = "❌ CPF inválido!";
                        }
                    }

                    sendMessage(chatId, resposta);
                }

                Thread.sleep(2000);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getUpdates() throws Exception {

        URL url = new URL(API_URL + "getUpdates?offset=" + updateId);

        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );

        StringBuilder response = new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        return response.toString();
    }

    public static void sendMessage(String chatId, String text)
            throws Exception {

        URL url = new URL(API_URL + "sendMessage");

        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        String body =
                "chat_id=" + chatId +
                "&text=" + URLEncoder.encode(text, StandardCharsets.UTF_8);

        OutputStream os = connection.getOutputStream();

        os.write(body.getBytes());

        os.flush();
        os.close();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );

        while (reader.readLine() != null) {}

        reader.close();
    }

    public static boolean validarCPF(String cpf) {

        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11)
            return false;

        if (cpf.matches("(\\d)\\1{10}"))
            return false;

        try {

            int soma = 0;

            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * (10 - i);
            }

            int dig1 = 11 - (soma % 11);

            if (dig1 >= 10)
                dig1 = 0;

            soma = 0;

            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * (11 - i);
            }

            int dig2 = 11 - (soma % 11);

            if (dig2 >= 10)
                dig2 = 0;

            return dig1 == (cpf.charAt(9) - '0')
                    && dig2 == (cpf.charAt(10) - '0');

        } catch (Exception e) {

            return false;
        }
    }
}