package Browser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URLEncoder;

public class HtmlFromUrlExample {

    public static void main(String[] args) {
        // Tạo JFrame
        JFrame frame = new JFrame("HTML Viewer");
        JPanel panel = new JPanel(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);

        JTextField searchField = new JTextField("laptrinhmang");

        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                try {

//                    String url = "https://www.google.com/search?q=" + URLEncoder.encode(query, "UTF-8");
                    String url = "";
                    if (query.startsWith("http")) {
                        url = query;
                    } else {
                        url = "https://www.google.com/search?q=" + URLEncoder.encode(query, "UTF-8");
                    }
                    System.out.println("Search URL: " + url);
                    editorPane.setPage(url);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    editorPane.setText("Không thể tải nội dung từ URL.");
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(editorPane);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);

        frame.setVisible(true);
    }
}
