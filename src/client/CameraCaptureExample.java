package client;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.highgui.HighGui;

public class CameraCaptureExample {
    static {
        // Load the OpenCV native library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {

        try {
            startVideoCall();
        } catch (Exception e) {
        }
    }
    
    
    public static void startVideoCall()  throws Exception {
        Socket socket = new Socket("localhost", 5000);

        // OpenCV to capture video frames
        VideoCapture camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            System.out.println("Error: Camera not found!");
            return;
        }

        Mat frame = new Mat();
        while (true) {
            // Capture the frame from webcam
            camera.read(frame);
            if (!frame.empty()) {
                // Convert Mat to BufferedImage
                BufferedImage bufferedImage = matToBufferedImage(frame);

                // Send the image to the server
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
                byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
                socket.getOutputStream().write(size);
                socket.getOutputStream().write(byteArrayOutputStream.toByteArray());
                socket.getOutputStream().flush();
            }
        }
    }
    
    private static BufferedImage matToBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(mat.width(), mat.height(), type);
        mat.get(0, 0, ((DataBufferByte) image.getRaster().getDataBuffer()).getData());
        return image;
    }
}
