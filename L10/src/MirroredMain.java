import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class MirroredMain extends JFrame {

    public MirroredMain(String title, TableModel model) {
        super(title);

        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(1, 2);
        layout.setHgap(5);
        panel.setLayout(layout);
        panel.add(createPanelForTable(model));
        panel.add(createPanelForTable(model));

        add(panel);
        pack();
    }

    private static Container createPanelForTable(TableModel model) {
        JTable table = new JTable();
        table.setModel(model);
        return new JScrollPane(
                table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    public static void main(String[] args) {
        MirroredMain main = new MirroredMain("Mirrored table view", new MutableBasicTable(50, 5));
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //main.setPreferredSize(new Dimension(1500, 1800));
        main.setVisible(true);
    }
}
