package paint;

import java.awt.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 02.10.13
 * Time: 14:03
 */
public class WindowMain {

    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(() -> {
            try {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                FormAssets fa = new FormAssets("SimplePaint");
                fa.setVisible(true);
                fa.setResizable(false);
                fa.setPreferredSize(new Dimension(506, 525));
                Dimension frameSize = fa.getPreferredSize();
                fa.setSize(frameSize.width, frameSize.height);
                fa.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }


}