package Browser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebViewer extends JFrame {
    private JTextField urlField;
    private JEditorPane editorPane;
    private JButton fetchButton;

    public WebViewer() {
        setTitle("Web Viewer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo giao diện người dùng
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        urlField = new JTextField("https://www.goole.com"); // Đặt URL mặc định
        fetchButton = new JButton("Fetch URL");
        editorPane = new JEditorPane();
        editorPane.setEditable(false); // Không cho phép chỉnh sửa nội dung

        // Thêm sự kiện cho nút
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchWebPage(urlField.getText());
            }
        });

        panel.add(urlField, BorderLayout.NORTH);
        panel.add(fetchButton, BorderLayout.CENTER);
        panel.add(new JScrollPane(editorPane), BorderLayout.SOUTH);
        add(panel);

        setVisible(true);
    }

    private void fetchWebPage(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // Kiểm tra mã phản hồi HTTP
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder htmlContent = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    htmlContent.append(inputLine).append("\n");
                }
                in.close();

                // Hiển thị HTML trong JEditorPane
                editorPane.setContentType("text/html");
                editorPane.setText(htmlContent.toString());
            } else {
                editorPane.setText("Error fetching data: HTTP response code " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            editorPane.setText("Error fetching data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WebViewer::new);
    }
}
