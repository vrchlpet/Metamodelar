package org.cvut.MMN.model;

import org.cvut.MMN.model.attributes.ImageAttribute;
import org.cvut.MMN.model.attributes.LabelAttribute;
import org.cvut.MMN.model.attributes.ShapeAttribute;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sabolmi1
 */
public class ShapeTest {

    public ShapeTest() {
    }

    @Test
    public void testAddAttribute() {
        System.out.println("addAttribute");
        ShapeAttribute at = null;
        Shape instance = new Shape();
        instance.addAttribute(at);
        int resultSize = instance.getAttributes().size();
        assertEquals(1, resultSize);

        ShapeAttribute resultAt = instance.getAttributes().get(0);
        assertEquals(null, resultAt);

        String image = "ea.jpg";
        ShapeAttribute at2 = new ImageAttribute(image);
        instance.addAttribute(at2);

        resultSize = instance.getAttributes().size();
        assertEquals(2, resultSize);

        resultAt = instance.getAttributes().get(1);
        assertEquals(at2, resultAt);

        String label = "description";
        ShapeAttribute at3 = new LabelAttribute(label);
        instance.addAttribute(at3);

        resultSize = instance.getAttributes().size();
        assertEquals(3, resultSize);

        resultAt = instance.getAttributes().get(2);
        assertEquals(at3, resultAt);
    }

    @Test
    public void testShape() {
        System.out.println("Shape");
        Shape shape = new Shape();
        assertEquals("unknown", shape.getShapeId());

        shape.setShapeId("abcbc");
        assertEquals("abcbc", shape.getShapeId());

        Shape shape2 = new Shape("lolofon");
        assertEquals("lolofon", shape2.getShapeId());

    }
}
