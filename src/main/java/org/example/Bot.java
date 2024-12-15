package org.example;

//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.TelegramBotsApi;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
//
//public class Bot extends TelegramLongPollingBot {
//
//    @Override
//    public String getBotUsername() {
//        return "YourBotUsername"; // Botingizning foydalanuvchi nomi
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7877821768:AAHkMKmzixa_X8GUylEsHqMgg1Qzk6cFVj0"; // Telegram'dan olgan bot tokeningiz
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasMessage()) {
//            // Agar video bo'lsa, File ID ni olish
//            if (update.getMessage().hasVideo()) {
//                String fileId = update.getMessage().getVideo().getFileId(); // Video File ID
//                System.out.println("File ID: " + fileId); // Konsolga chiqarish
//
//                // File ID ni foydalanuvchiga yuborish
//                try {
//                    execute(new SendMessage(update.getMessage().getChatId().toString(),
//                            "Video File ID: " + fileId));
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            } else if (update.getMessage().hasText()) {
//                // Matn xabarlarini qayta ishlash
//                String userMessage = update.getMessage().getText();
//                String chatId = update.getMessage().getChatId().toString();
//
//                if (userMessage.equalsIgnoreCase("BAACAgIAAxkBAAIBjmdes_cU7uPjg7kx5sce5MKvKUUlAALoVQACyXKwSnrBm6b84WtqNgQ")) {
//                    sendHelpMessage(chatId, "Videoni botga yuboring, File ID ni ko'rsatay.");
//                } else {
//                    sendHelpMessage(chatId, "Salom! Videoni botga yuboring, File ID ni qaytarib beraman.");
//                }
//            }
//        }
//    }
//
//    // Foydalanuvchiga yordam xabari yuborish
//    private void sendHelpMessage(String chatId, String helpText) {
//        try {
//            execute(new SendMessage(chatId, helpText));
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//}



//
//
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Bot extends TelegramLongPollingBot {

    // Your bot's username and token
    @Override
    public String getBotUsername() {
        return "YourBotUsername"; // Replace with your bot's username
    }

    @Override
    public String getBotToken() {
        return "7877821768:AAHkMKmzixa_X8GUylEsHqMgg1Qzk6cFVj0"; // Replace with your bot's token
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            String chatId = update.getMessage().getChatId().toString();

            // Check if the message is a "/start" command
            if (update.getMessage().hasText() && update.getMessage().getText().equalsIgnoreCase("/start")) {
                sendVideoByFileId(chatId, "BAACAgIAAxkBAAIBvmdewBEf6_zHNBKyzedta8DWXChIAAJvWQACkHCISVpn1pfNaydZNgQ");
            } else {
                sendHelpMessage(chatId, "Send /start to receive a video.");
            }
        }
    }

    // Method to send a video by File ID
    private void sendVideoByFileId(String chatId, String fileId) {
        SendVideo sendVideo = new SendVideo();
        sendVideo.setChatId(chatId); // Set the chat ID
        sendVideo.setVideo(new InputFile(fileId));  // Use InputFile for the file ID
        sendVideo.setCaption("Here is the video you requested!"); // Set a caption for the video

        try {
            execute(sendVideo); // Send the video to the user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private void sendHelpMessage(String chatId, String helpText) {
        try {
            execute(new SendMessage(chatId, helpText)); // Send the help text to the user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
