package Adviser;
import java.io.*;
import java.net.*;
/*Программа советчик
 * 1] Клиент подключается к серверу и принимает от него входящий поток
 * 2] Клиент считывает сообщение полученное от сервера
 * */


public class AdviserClient {
    public static void main(String[] args) {
        AdviserClient client = new AdviserClient();
        client.go();
    }

    public void go(){
        try {
            // Создаем сокет к приложению на порту 4242
            Socket s = new Socket("127.0.0.1", 4242);
            // Подключаем потоки
            InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            // Получаем символы
            String advice = reader.readLine();
            System.out.println("Сегодня вы должны: " + advice);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}


