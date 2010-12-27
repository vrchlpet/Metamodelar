

package org.cvut.MMN.model.attributes;



import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.netbeans.api.visual.anchor.AnchorShapeFactory;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.openide.util.Utilities;

/**
 *
 * @author Vrchli
 */
public class ConnectionTargetShape implements ConnectionAttribute{

    private String pathToImage;

    public ConnectionTargetShape(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    @Override
    public void apply(ConnectionWidget cw) {

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(pathToImage));
            cw.setTargetAnchorShape(AnchorShapeFactory.
                createImageAnchorShape(image, true));
        } catch (IOException e) {
            Image image2 = Utilities.loadImage(pathToImage);
            cw.setTargetAnchorShape(AnchorShapeFactory.
                createImageAnchorShape(image2, true));
        }
    }

    public String getPath() {
        return this.pathToImage;
    }

}
