package Browser;

import javax.swing.*;
import java.io.IOException;

public class HtmlDisplayExample {
    public static void main(String[] args) {
        // Tạo JFrame
        JFrame frame = new JFrame("HTML Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Tạo JEditorPane
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false); // Không cho phép chỉnh sửa nội dung
        
        // Thiết lập nội dung HTML
        String htmlContent = "<html>" +
                "<body>" +
                "<h1>Chào mừng đến với Java Swing</h1>" +
                "<p>Đây là một ví dụ về hiển thị HTML trong JEditorPane.</p>" +
                "<ul>" +
                "<li>Item 1</li>" +
                "<li>Item 2</li>" +
                "<li>Item 3</li>" +
                "</ul>" +
                "</body>" +
                "</html>";

        editorPane.setContentType("text/html"); // Đặt loại nội dung là HTML
        editorPane.setText(htmlContent);

        // Đặt JEditorPane vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(editorPane);

        // Thêm JScrollPane vào JFrame
        frame.add(scrollPane);
        frame.setVisible(true);
    }
}
