import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TextManipulationApp extends JFrame {
    private JTextArea textArea;
    private JTextField replaceField;
    private JTextField replaceWithField;
    private JButton replaceButton;
    
    public TextManipulationApp() {
        setTitle("Text Manipulation App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
    }
    
    private void initComponents() {
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        
        JLabel replaceLabel = new JLabel("Słowo do zamiany:");
        replaceField = new JTextField(15);
        
        JLabel replaceWithLabel = new JLabel("Nowe słowo:");
        replaceWithField = new JTextField(15);
        
        replaceButton = new JButton("Zamień");
        replaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                replaceText();
            }
        });
        
        controlPanel.add(replaceLabel);
        controlPanel.add(replaceField);
        controlPanel.add(replaceWithLabel);
        controlPanel.add(replaceWithField);
        controlPanel.add(replaceButton);
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(controlPanel, BorderLayout.SOUTH);
    }
    
    private void replaceText() {
        String wordToReplace = replaceField.getText();
        String newWord = replaceWithField.getText();
        String text = textArea.getText();
        
        text = text.replaceAll(wordToReplace, newWord);
        textArea.setText(text);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TextManipulationApp app = new TextManipulationApp();
                app.setVisible(true);
            }
        });
    }
}
