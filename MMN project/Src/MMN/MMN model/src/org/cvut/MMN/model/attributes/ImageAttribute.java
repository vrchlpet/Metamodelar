

package org.cvut.MMN.model.attributes;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Utilities;

/**
 *
 * @author Vrchli
 */
public class ImageAttribute implements ShapeAttribute{

    private String path;

    public ImageAttribute(String path) {
        this.path = path;
    }

    @Override
    public void apply(Widget widget) {

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
            widget.addChild(new ImageWidget(widget.getScene(), image));
        } catch (IOException e) {
            Image image2 = Utilities.loadImage(path);
            widget.addChild(new ImageWidget(widget.getScene(), image2));

        }

    }

    public String getPath() {
        return this.path;
    }

}
