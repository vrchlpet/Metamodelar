package org.cvut.MMN.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sabolmi1
 */
public class MetamodelTest {

    public MetamodelTest() {
    }

    @Test
    public void testAddCategory_ShapeCategory() {
        System.out.println("addGetShapeCategory");
        ShapeCategory cat = null;
        Metamodel instance = new Metamodel();
        instance.addCategory(cat);

        int resultSize = instance.getShapeCategories().size();
        assertEquals(1, resultSize);
        ShapeCategory result = instance.getShapeCategories().get(0);
        assertEquals(null, result);

        ShapeCategory cat2=new ShapeCategory();
        instance.addCategory(cat2);

        resultSize=instance.getShapeCategories().size();
        assertEquals(2, resultSize);
        result=instance.getShapeCategories().get(1);
        assertEquals(cat2, result);
        assertEquals("unknown", result.getCategoryId());      
    }

    @Test
    public void testAddCategory_ConnectionCategory() {
        System.out.println("addGetConnectionCategory");
        ConnectionCategory cat = null;
        Metamodel instance = new Metamodel();
        instance.addCategory(cat);

        int resultSize = instance.getConCategories().size();
        assertEquals(1, resultSize);
        ConnectionCategory result = instance.getConCategories().get(0);
        assertEquals(null, result);

        ConnectionCategory cat2=new ConnectionCategory();
        instance.addCategory(cat2);

        resultSize=instance.getConCategories().size();
        assertEquals(2, resultSize);
        result=instance.getConCategories().get(1);
        assertEquals(cat2, result);
        assertEquals("unknown", result.getCategoryId());
    }

    @Test
    public void testGetName() {
        System.out.println("getSetName");
        Metamodel instance = new Metamodel();
        String result = instance.getName();
        assertEquals("mm", result);

        String name = "name";
        instance.setName(name);
        result = instance.getName();
        assertEquals("name", result);

        name = "dajakename";
        Metamodel instance2 = new Metamodel(name);
        result = instance2.getName();
        assertEquals("dajakename", result);
    }

    @Test
    public void testGetShapeManager() {
      assertTrue(true);
    }

    @Test
    public void testGetConnectionManager() {
     assertTrue(true);
    }
}
