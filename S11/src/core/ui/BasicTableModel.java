package core.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.table.AbstractTableModel;

public class    BasicTableModel extends AbstractTableModel{
    
    private final List<String> colNames;
    private final List<List<String>> data;

    public BasicTableModel() {
        colNames = new ArrayList<>();
        data = new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.size();
    }

    @Override
    public String getColumnName(int i) {
        return colNames.get(i);
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int i, int j) {
        return true;
    }

    @Override
    public Object getValueAt(int i, int j) {
        List<String> row = data.get(i);
        
        if (j < row.size()) {
            return row.get(j);
        }
        
        return null;
    }

    @Override
    public void setValueAt(Object o, int i, int j) {
        List<String> row = data.get(i);
        
        if (j >= row.size()) {
            while (j >= row.size()) {
                row.add(null);
            }
        }
        
        row.set(j, (String) o);
        
        fireTableCellUpdated(i, j);
    }

    public void addColumn(String colName) {
        colNames.add(colName);
        fireTableStructureChanged();
    }
    
    public int addRow(List<String> row) {
        data.add(row);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
        return data.size() - 1;
    }
    
    public int addRow() {
        List<String> row = new ArrayList<>();
        return addRow(row);
    }
    
    public void deleteRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex > data.size() - 1) {
            return;
        }
        
        if (data.isEmpty()) {
            return;
        }
        
        data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public void loadFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // read the column names
            colNames.clear();
            colNames.addAll(stringToList(reader.readLine()));

            // each line makes a row in the data model
            data.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(stringToList(line));
            }
            
            fireTableStructureChanged();
        } catch (IOException ex) {
            System.out.println(ex + " " + ex.getMessage());
        }
    }
    
    public void saveToFile(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writeStrings(writer, colNames);
            
            for (List<String> list : data) {
                writeStrings(writer, list);
            }
        } catch (IOException ex) {
            System.out.println(ex + " " + ex.getMessage());
        }
    }
    
    private List<String> stringToList(String str) {
        List<String> row = new ArrayList<>();
        
        StringTokenizer tokenizer = new StringTokenizer(str, "\t", true);
        
        String elem = "", last;
        while (tokenizer.hasMoreTokens()) {
            last = elem;
            elem = tokenizer.nextToken();
            
            if (!elem.equals("\t")) {
                row.add(elem);
            } else if (last.equals("\t")) {
                row.add("");
            }
        }
        
        if (elem.equals("\t")) {
            row.add(""); // tricky: notice final element
        }
        
        return row;
    }
    
    private void writeStrings(BufferedWriter writer, Collection<String> strings) throws IOException {
        Iterator<String> iter = strings.iterator();
        
        while (iter.hasNext()) {
            String string = iter.next();
            if (string != null) {
                writer.write(string);
            }
            if (iter.hasNext()) {
                writer.write('\t');
            }
        }
        
        writer.newLine();
    }
}