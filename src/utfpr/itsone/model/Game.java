package utfpr.itsone.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class Game {
    private int id;
    private String name;
    private String description;
    private Date date;
    private char rating;
    private String site;
    private String developer;
    private BufferedImage cover;
    private BufferedImage background;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public BufferedImage getBackground() {
        return background;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public char getRating() {
        return rating;
    }

    public void setRating(char rating) {
        this.rating = rating;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setCover(String cover){
        this.cover = setImage("cover", cover);
    }

    public void setBackground(String background) {
        this.background = createResizedCopy(setImage("background", background),1500,500,true);
    }

    public BufferedImage setImage(String type, String path){
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("utfpr/itsone/resources/" + type + "/" + path)));
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
