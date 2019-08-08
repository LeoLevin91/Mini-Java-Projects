package Adviser;
/*
* Создание сервера
* 1] Серверное приложение создает объект ServerSocket, указывая на конкретный порт
* 2] Клиент создает сокет и связывается с сервером
* 3] Сервер создает сокет для общения с клиентом accept() - для блокировки программы
*
*
* */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class AdviserServer {
    private String[] adviceList = {"Ешьте меньшими порциями", "Купите джинсы", "Два слова: не годится",
    "Будьте честны, хотя бы сегодня", "Возможно вам стоит сменить прическу"};

    public static void main(String[] args) {
        AdviserServer server = new AdviserServer();
        server.go();
    }

    public void go(){
        try {
            /*Благодаря ServerSocket приложение отслеживает клиентские запросы
            * на конкретном порту на том же пк где выполняется код*/
            ServerSocket serverSocket = new ServerSocket(4242);

            // Бесконечный цикл для отслеживания подключений клиентов
            while(true){
                // Блокирует приложение а затем возвращает сокет для взаимодействия с клиентом
                Socket sock = serverSocket.accept();

                PrintWriter writer = new PrintWriter(sock.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public String getAdvice(){
        int rand = (int)(Math.random()*adviceList.length);
        return  adviceList[rand];
    }
}
