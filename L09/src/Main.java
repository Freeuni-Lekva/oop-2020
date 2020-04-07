import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    void foo() {}

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        JTextArea textArea = new JTextArea("my text");
        JScrollPane textAreaPane = new JScrollPane(textArea);
        textAreaPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textAreaPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JButton button = new MyButton("my button");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("Button clicked");
            }
        });

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem openFile = new JMenuItem("Open");
        menu.add(newFile);
        menu.add(openFile);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("New file");
            }
        });

        openFile.addActionListener(new OpenListener(frame, textArea));


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());


        panel.add(textAreaPane);
        panel.add(button);

        frame.setContentPane(panel);
        frame.setTitle("My sample app");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 300);
    }

    private static class MyButton extends JButton {
        public MyButton(String text) {
            super(text);
            setForeground(Color.CYAN);
            setBackground(Color.BLACK);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawOval(5, 5, this.getWidth() - 10, this.getHeight() - 10);
        }
    }

    private static class OpenListener implements ActionListener {
        private JFrame frame;
        private JTextArea textArea;

        public OpenListener(JFrame frame, JTextArea textArea) {
            this.frame = frame;
            this.textArea = textArea;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int ret = fileChooser.showOpenDialog(frame);
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    textArea.setText(new String(Files.readAllBytes(file.toPath())));
                } catch (IOException ex) {
                    textArea.setText("Could not open file");
                }
            }
        }
    }
}
