import javax.swing.table.AbstractTableModel;

public class ImmutableBasicTable extends AbstractTableModel {
    private final int rows;
    private final int cols;

    public ImmutableBasicTable(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public int getRowCount() {
        return rows;
    }

    @Override
    public int getColumnCount() {
        return cols;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return "r" + String.valueOf(rowIndex) + " : c" + String.valueOf(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
