package utfpr.itsone.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class Game {
    private static Long cont = 0l;
    private long id;
    private String name;
    private String plataform;
    private String description;
    private BufferedImage cover;
    private BufferedImage background;

    public Game(String name, String description) {
        id = cont++;
        this.name = name;
        this.description = description;
        setCover();
        setBackground();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BufferedImage getCover() {
        return cover;
    }

    public void setCover() {
        this.cover = setImage("cover");
    }

    public BufferedImage getBackground() {
        return background;
    }

    public void setBackground() {
        this.background = createResizedCopy(setImage("background"),1500,500,true);
    }

    public BufferedImage setImage(String path){
        try {
            if ((getClass().getClassLoader().getResource("utfpr/itsone/resources/"+path+"/"+getId()+".jpg") != null))
                return ImageIO.read(getClass().getClassLoader().getResource("utfpr/itsone/resources/"+path+"/"+getId()+".jpg"));
            else
                return ImageIO.read(getClass().getClassLoader().getResource("utfpr/itsone/resources/blank.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage createResizedCopy(Image originalImage,
                                                  int scaledWidth, int scaledHeight,
                                                  boolean preserveAlpha)
    {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }

    public BufferedImage toFilter(BufferedImage image) {

        int width = image.getWidth();
        int height = image.getHeight();

        for(int i=0; i<height; i++){

            for(int j=0; j<width; j++){

                Color c = new Color(image.getRGB(j, i));
                int red = (int)(c.getRed() * 0.4);
                int green = (int)(c.getGreen() * 0.4);
                int blue = (int)(c.getBlue() * 0.4);
                Color newColor = new Color(red,

                        green,blue);

                image.setRGB(j,i,newColor.getRGB());
            }
        }
        File outputfile = new File(getId()+".jpg");
        try {
            ImageIO.write(image, "JPG", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
