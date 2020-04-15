package core.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableFrame extends JFrame {
    
    public TableFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BasicTableModel model = new BasicTableModel();
        
        // create table panel
        JPanel tablePanel = new JPanel();
        GridLayout tablePanelGrid = new GridLayout(1, 2);
        tablePanelGrid.setHgap(5);
        tablePanel.setLayout(tablePanelGrid);
        
        JTable srcTable = new JTable(model);
        JScrollPane srcTableScroll = new JScrollPane(srcTable, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        JTable destTable = new JTable(model);
        destTable.setEnabled(false);
        JScrollPane destTableScroll = new JScrollPane(destTable, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        tablePanel.add(srcTableScroll);
        tablePanel.add(destTableScroll);
        
        // create button panel
        JPanel buttonPanel = new JPanel();
        GridLayout grid = new GridLayout(1, 5);
        grid.setHgap(5);
        buttonPanel.setLayout(grid);
        
        JButton addColumn, addRow, deleteRow, loadFile, saveFile;
        addColumn = new JButton("Add Column");
        addRow = new JButton("Add Row");
        deleteRow = new JButton("Delete Row"); 
        loadFile = new JButton("Load File");
        saveFile = new JButton("Save File");
        
        addColumn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String colName = JOptionPane.showInputDialog("Enter column name");
                if (colName != null) {
                    model.addColumn(colName);
                }
            }
        });
        
        addRow.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int i = model.addRow();
                srcTable.clearSelection();
                srcTable.addRowSelectionInterval(i, i);
            }
        });
        
        deleteRow.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int row = srcTable.getSelectedRow();
                model.deleteRow(row);
                if (model.getRowCount() > 0) {
                    srcTable.addRowSelectionInterval(model.getRowCount() - 1, model.getRowCount() - 1);
                }
            }
        });
        
        loadFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int rv = fc.showOpenDialog(fc);
                if (rv == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    model.loadFile(file);
                }
            }
        });
        
        saveFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int rv = fc.showSaveDialog(fc);
                if (rv == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    model.saveToFile(file);
                }
            }
        });
        
        buttonPanel.add(addColumn);
        buttonPanel.add(addRow);
        buttonPanel.add(deleteRow);
        buttonPanel.add(loadFile);
        buttonPanel.add(saveFile);
        
        // add panels top frame
        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        pack();
    }
}