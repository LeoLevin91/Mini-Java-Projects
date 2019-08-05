import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class QuizCardBuilder {
    /*Создание и сохранение флеш-карт*/

    private JTextArea question;
    private JTextArea answer;
    private ArrayList<QuizCard> cardList;
    private JFrame frame;

    public static void main(String[] args) {
        QuizCardBuilder builder = new QuizCardBuilder();
        builder.go();
    }

    public void go(){
        // Формируем и выводим на экран GUI

        frame = new JFrame("Quiz Card Builder");
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);
        question = new JTextArea(6, 20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(bigFont);

        JScrollPane qScroller = new JScrollPane(question);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        answer = new JTextArea(6, 20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(bigFont);

        JScrollPane aScroller = new JScrollPane(answer);
        aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton button = new JButton("Next Card");

        cardList = new ArrayList<QuizCard>();

        JLabel qLable = new JLabel("Question:");
        JLabel aLable = new JLabel("Answer:");

        mainPanel.add(qLable);
        mainPanel.add(qScroller);
        mainPanel.add(aLable);
        mainPanel.add(aScroller);
        mainPanel.add(button);
        button.addActionListener(new NextCardListener());

        // Создание вертикальной панели
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(new NewMenuListener());
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(new SaveMenuListener());

        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500, 600);
        frame.setVisible(true);
    }

    private class NextCardListener implements ActionListener{
        // Добавляем тукущуую карточку в список и очищаем текстовые области
        /*
        * Запускается при выборе команды Save из File меню
        * Означает что пользователь хочет сохранить все карточки в виде набора
        * */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);
            clearCard();
        }
    }

    private class SaveMenuListener implements ActionListener {
        //Вызываем диалоговое окно позволяющее пользователю сохранить набор
        /*
        * Запускается при выборе команды New из меню File
        * Означает что пользователь хочет создать новый набор
        * (отчистив списки карточек и текстовые области)
        * */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);

            // Дталоговое окно
            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }

    private class NewMenuListener implements ActionListener{
        // Очищаем сисок карточеки текстовые области
        @Override
        public void actionPerformed(ActionEvent actionEvent){
            cardList.clear();
            clearCard();
        }
    }

    private void clearCard(){
        // Отчистка полей
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }

    private void saveFile(File file){
        // Вызывается SaveMenuListener. Непосредственно записывает данные в файл
        /*
        * Проходим по списку карточек и записываем
        * каждый элемент в текстовый файл, который потом можно будет прочитать
        * (с разделителями между частями)
        * */
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for(QuizCard card:cardList){
                writer.write(card.getQuestion() + "/");
                writer.write(card.getAnswer() + "\n");
            }
            writer.close();
        } catch (Exception e){
            System.out.println("Couldn`t write the cardList out.");
            e.printStackTrace();
        }
    }

}
