

package org.cvut.MMN.model.attributes;

import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Vrchli
 */
public class LabelAttribute implements ShapeAttribute{
    private String label;

    public LabelAttribute(String l) {
        this.label = l;
    }

    @Override
    public void apply(Widget widget) {
        widget.addChild(new LabelWidget(widget.getScene(),label));
    }

    public String getLabelText() {
        return this.label;
    }
}
