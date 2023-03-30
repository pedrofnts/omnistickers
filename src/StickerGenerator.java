import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;


public class StickerGenerator {

    public void create(InputStream inputStream, String filename) throws Exception {


        BufferedImage original = ImageIO.read(inputStream);

        int width = original.getWidth();
        int height = original.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) newImage.getGraphics();


        graphics.drawImage(original, 0,0, null);

        var font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.yellow);
        graphics.setFont(font);

        graphics.drawString("TOPZERA", 160, newHeight - 100);

        File directory = new File("output");

        if(!directory.exists()) {
            directory.mkdirs();
        }

        ImageIO.write(newImage, "png", new File("output/" + filename));

    }
}
