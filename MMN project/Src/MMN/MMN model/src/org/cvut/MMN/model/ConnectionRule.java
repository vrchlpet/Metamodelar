

package org.cvut.MMN.model;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Vrchli
 */
public class ConnectionRule {
    private Shape source;
    private Shape target;
    private int sourcM;
    private int targetM;

    public ConnectionRule(){
        this(new Shape("unknown"),0,new Shape("unknown"),0);
    }

    public ConnectionRule(Shape source, int sourceM, Shape target, int targetM) {
        this.source = source;
        this.target = target;
        this.sourcM = sourceM;
        this.targetM = targetM;
    }

    @XmlElement(type=Shape.class)
    public Shape getSource() {
        return source;
    }

    public void setSource(Shape source) {
        this.source = source;
    }

    @XmlElement(type=Shape.class)
    public Shape getTarget() {
        return target;
    }

    public void setTarget(Shape target) {
        this.target = target;
    }

    @XmlElement()
    public int getSourcM() {
        return sourcM;
    }

    public void setSourcM(int sourcM) {
        this.sourcM = sourcM;
    }

    @XmlElement()
    public int getTargetM() {
        return targetM;
    }

    public void setTargetM(int targetM) {
        this.targetM = targetM;
    }
}
