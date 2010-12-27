/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class ConnectionSourceShape implements ConnectionAttribute{

    private String pathToImage;

    public ConnectionSourceShape(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    @Override
    public void apply(ConnectionWidget cw) {

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(pathToImage));
            cw.setSourceAnchorShape(AnchorShapeFactory.
                createImageAnchorShape(image, true));
        } catch (IOException e) {
            Image image2 = Utilities.loadImage(pathToImage);
            cw.setSourceAnchorShape(AnchorShapeFactory.
                createImageAnchorShape(image2, true));
        }
    }

    public String getPath() {
        return this.pathToImage;
    }

}
