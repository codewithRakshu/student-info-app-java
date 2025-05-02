import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentInfoSwingApp {

    private JFrame frame;
    private DefaultListModel<Student> studentListModel;
    private JList<Student> studentJList;
    private JTextField nameField, ageField, idField;

    private java.util.List<Student> students = new ArrayList<>();

    public StudentInfoSwingApp() {
        frame = new JFrame("Student Information System (Swing)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        studentListModel = new DefaultListModel<>();
        studentJList = new JList<>(studentListModel);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));

        nameField = new JTextField();
        ageField = new JTextField();
        idField = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Student ID:"));
        panel.add(idField);

        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        addButton.addActionListener(e -> addStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());

        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(studentJList));
        frame.setVisible(true);
    }

    private void addStudent() {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String id = idField.getText();
            Student s = new Student(name, age, id);
            studentListModel.addElement(s);
            clearFields();
        } catch (Exception e) {
            showMessage("Please enter valid data.");
        }
    }

    private void updateStudent() {
        int index = studentJList.getSelectedIndex();
        if (index != -1) {
            try {
                Student selected = studentListModel.get(index);
                selected.setName(nameField.getText());
                selected.setAge(Integer.parseInt(ageField.getText()));
                selected.setId(idField.getText());
                studentListModel.set(index, selected); // refresh display
                clearFields();
            } catch (Exception e) {
                showMessage("Invalid update data.");
            }
        } else {
            showMessage("Please select a student to update.");
        }
    }

    private void deleteStudent() {
        int index = studentJList.getSelectedIndex();
        if (index != -1) {
            studentListModel.remove(index);
            clearFields();
        } else {
            showMessage("Select a student to delete.");
        }
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        idField.setText("");
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentInfoSwingApp::new);
    }
}
