import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ToDoApp {
    private JFrame frame;
    private JTable table;
    private JTextField textField;
    private List<Task> tasks;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ToDoApp window = new ToDoApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ToDoApp() {
        tasks = new ArrayList<>();
        initialize();
        loadTasks();
    }

    private void initialize() {
        frame = new JFrame("To-Do App");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        textField = new JTextField(20);
        panel.add(textField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskText = textField.getText();
                if (!taskText.isEmpty()) {
                    Task task = new Task(taskText, false);
                    tasks.add(task);
                    saveTasks();
                    updateTable();
                    textField.setText("");
                }
            }
        });
        panel.add(addButton);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Tasks", "Completed"}));
        frame.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Task task = tasks.get(selectedRow);
                    tasks.remove(selectedRow);
                    saveTasks();
                    updateTable();
                }
            }
        });
        frame.getContentPane().add(deleteButton, BorderLayout.SOUTH);
    }

    private void loadTasks() {
        // Placeholder for loading tasks from a file or database
    }

    private void saveTasks() {
        // Placeholder for saving tasks to a file or database
    }

    private void updateTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Task task : tasks) {
            Object[] row = {task.getText(), task.isCompleted()};
            model.addRow(row);
        }
    }

    private static class Task {
        private String text;
        private boolean completed;

        public Task(String text, boolean completed) {
            this.text = text;
            this.completed = completed;
        }

        public String getText() {
            return text;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }
    }
}