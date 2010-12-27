package org.cvut.MMN.model;

import org.netbeans.api.visual.widget.Scene;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matej
 */
public class ShapeManagerTest {

    public ShapeManagerTest() {
    }

    @Test
    public void testCreateShape() throws Exception {
        ShapeManager sm = new ShapeManager(new Metamodel());

        sm.createShapeCategory("cat1");
        sm.createShape("shape1", "cat1");
        assertEquals("shape1", sm.getShape("shape1").getShapeId());
        assertEquals("cat1", sm.getShapeCategoryByCatName("cat1").getCategoryId());
    }

    @Test(expected = MMNException.class)
    public void testCreateShapeNullCategory() throws Exception {
        ShapeManager sm = new ShapeManager(new Metamodel());

        sm.createShape("shape2", "cat2");
    }

    @Test(expected = MMNException.class)
    public void testCreateShapeDuplicateShape() throws Exception {
        ShapeManager sm = new ShapeManager(new Metamodel());

        sm.createShapeCategory("cat3");
        sm.createShape("shape3", "cat3");
        sm.createShape("shape3", "cat3");
        assertEquals("shape3", sm.getShape("shape3").getShapeId());
        assertEquals("cat3", sm.getShapeCategoryByCatName("cat3").getCategoryId());
    }

    @Test
    public void testRemoveShape() throws MMNException {
        ShapeManager sm = new ShapeManager(new Metamodel());

        sm.createShapeCategory("cat");
        sm.createShape("shape", "cat");
        sm.removeShape("shape");
        assertEquals(null, sm.getShape("shape"));
    }

    @Test
    public void testRemoveShapeNullShape() {
        ShapeManager sm = new ShapeManager(new Metamodel());

        sm.removeShape("i dont know");
        assertEquals(null, sm.getShape("i dont know"));
    }

    @Test
    public void testGetWidget() throws MMNException {
        ShapeManager sm = new ShapeManager(new Metamodel());

        sm.createShapeCategory("cat");
        sm.createShape("shape", "cat");
        sm.getWidget(new Scene(), "shape");
    }

    @Test
    public void testCreateShapeCategory() throws Exception {
        ShapeManager sm = new ShapeManager(new Metamodel());

        sm.createShapeCategory("new category");
        assertEquals("new category", sm.getShapeCategoryByCatName("new category").getCategoryId());
    }

    @Test(expected = MMNException.class)
    public void testCreateShapeCategoryExistingCategory() throws Exception {
        ShapeManager sm = new ShapeManager(new Metamodel());

        sm.createShapeCategory("existing category");
        sm.createShapeCategory("existing category");
    }

    @Test
    public void testGetShapeCategoryByCatNameNullCategory() throws MMNException {
        ShapeManager sm = new ShapeManager(new Metamodel());

        sm.createShapeCategory("category");

        assertEquals(null, sm.getShapeCategoryByCatName("doesnt exist"));
    }

    @Test
    public void testGetShapeCategoryByShapeId() throws MMNException {
        ShapeManager sm = new ShapeManager(new Metamodel());

        sm.createShapeCategory("shape cat");
        sm.createShape("shape thank you", "shape cat");
        sm.createShape("shape its end", "shape cat");
        assertEquals("shape cat", sm.getShapeCategoryByShapeId("shape thank you").getCategoryId());
        assertEquals("shape cat", sm.getShapeCategoryByShapeId("shape its end").getCategoryId());
    }
}
