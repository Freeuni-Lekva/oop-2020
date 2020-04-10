import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class Main extends JFrame {
    TableModel model;

    public Main(String title, TableModel model) {
        super(title);
        this.model = model;

        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        JTable table = new JTable();
        table.setModel(model);

        mainPanel.add(table.getTableHeader(), BorderLayout.NORTH);
        mainPanel.add(table, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Main main = new Main("Table view", new MutableBasicTable(20, 3));
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(500, 800);
        main.setVisible(true);
    }
}
