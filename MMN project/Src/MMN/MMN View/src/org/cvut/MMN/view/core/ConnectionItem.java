

package org.cvut.MMN.view.core;

/**
 *
 * @author Vrchli
 */
public class ConnectionItem {

    private Shape source;
    private Shape target;
    private int targetMul;
    private int sourceMul;


    public ConnectionItem(Shape source, Shape target, int tM, int sM) {
        this.source = source;
        this.target = target;
        this.targetMul = tM;
        this.sourceMul = sM;
    }

    /**
     * @return the source
     */
    public Shape getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(Shape source) {
        this.source = source;
    }

    /**
     * @return the target
     */
    public Shape getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(Shape target) {
        this.target = target;
    }

    /**
     * @return the targetMul
     */
    public int getTargetMul() {
        return targetMul;
    }

    /**
     * @param targetMul the targetMul to set
     */
    public void setTargetMul(int targetMul) {
        this.targetMul = targetMul;
    }

    /**
     * @return the sourceMul
     */
    public int getSourceMul() {
        return sourceMul;
    }

    /**
     * @param sourceMul the sourceMul to set
     */
    public void setSourceMul(int sourceMul) {
        this.sourceMul = sourceMul;
    }
}
