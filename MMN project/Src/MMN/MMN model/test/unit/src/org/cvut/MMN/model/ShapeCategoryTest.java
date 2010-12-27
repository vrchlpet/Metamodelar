package org.cvut.MMN.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sabolmi1
 */
public class ShapeCategoryTest {

    public ShapeCategoryTest() {
    }

    @Test
    public void testAddShape() {
        System.out.println("addShape");
        Shape shape = null;
        ShapeCategory instance = new ShapeCategory();
        instance.addShape(shape);

        int resultSize = instance.getShapes().size();
        assertEquals(1, resultSize);

        Shape shapeResult = instance.getShapes().get(0);
        assertEquals(null, shapeResult);

        Shape shape2 = new Shape();
        instance.addShape(shape2);

        resultSize = instance.getShapes().size();
        assertEquals(2, resultSize);
        shapeResult = instance.getShapes().get(1);
        assertEquals(shape2, shapeResult);

        Shape shape3 = new Shape("abc");
        instance.addShape(shape3);

        resultSize = instance.getShapes().size();
        assertEquals(3, resultSize);
        shapeResult = instance.getShapes().get(2);
        assertEquals(shape3, shapeResult);
        assertEquals("abc", shapeResult.getShapeId());
    }

    @Test
    public void testSetGetCategoryId() {
        System.out.println("setGetCategoryId");
        ShapeCategory instance = new ShapeCategory();

        String result = instance.getCategoryId();
        assertEquals("unknown", result);

        String categoryId = "1";
        instance.setCategoryId(categoryId);
        result = instance.getCategoryId();
        assertEquals("1", result);

        categoryId = "394898439dfsfcss";
        instance.setCategoryId(categoryId);
        result = instance.getCategoryId();
        assertEquals("394898439dfsfcss", result);
    }
}
