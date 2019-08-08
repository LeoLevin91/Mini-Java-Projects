package VerySimpleChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleChatClient {
    /*
    * Набирается сообщение, затем нажимается кнопка отправки, что бы передать информацию на сервер
    * Тут мы не будем получать ответа от сервера.
    * Поэтому тут и нет области под ответ сервера.
    * */

    /*________________________________________________________________________*/
    /*
    * ЭТАПЫ
    * +1) Создание GUI и подключение слушателя для события к кнопке отправки
    * 2) Создание сокета и PrintWriter. Присвоение PrintWriter переменной writer
    * 3) Получаем текст из текстового поля и отправляем его на сервер с помощью переменной
    *   writer
    * */

    JTextField outgoing;
    PrintWriter writer;
    Socket sock;

    public static void main(String[] args) {
        new SimpleChatClient().go();
    }

    public void go(){
        JFrame frame = new JFrame("Client");
        JPanel panel = new JPanel();
        outgoing = new JTextField(20);
        JButton button = new JButton("Send");
        button.addActionListener(new SendButtonListener());
        panel.add(outgoing);
        panel.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        setUpNetworking();
        frame.setSize(400, 500);
        frame.setVisible(true);
    }

    private void setUpNetworking(){
        try {
            sock = new Socket("127.0.0.1", 4242);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("networking true");
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                writer.println(outgoing.getText());
                writer.flush();
            } catch (Exception ev){
                ev.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }
}
