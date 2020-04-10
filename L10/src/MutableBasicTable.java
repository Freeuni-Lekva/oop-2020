import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class MutableBasicTable extends ImmutableBasicTable {
    private Map<AbstractMap.SimpleImmutableEntry<Integer, Integer>, Object> values;

    public MutableBasicTable(int rows, int cols) {
        super(rows, cols);
        values = new HashMap<>();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = values.get(new AbstractMap.SimpleImmutableEntry<>(rowIndex, columnIndex));
        if (value != null) {
            return value;
        } else {
            return super.getValueAt(rowIndex, columnIndex);
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        values.put(new AbstractMap.SimpleImmutableEntry<>(rowIndex, columnIndex), aValue);
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}
