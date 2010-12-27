package org.cvut.MMN.model;

import org.netbeans.api.visual.widget.Scene;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matej
 */
public class ConnectionManagerTest {

    public ConnectionManagerTest() {
    }

    @Test
    public void testCreateConnection() throws Exception {
        ConnectionManager cm = new ConnectionManager(new Metamodel());

        cm.createConnectionCategory("cat2");
        cm.createConnection("con2", "cat2");
        assertEquals("con2", cm.getConnection("con2").getConId());
        assertEquals("cat2", cm.getConnectionCategoryByCatName("cat2").getCategoryId());
    }

    @Test(expected = MMNException.class)
    public void testCreateConnectionNullCategory() throws Exception {
        ConnectionManager cm = new ConnectionManager(new Metamodel());

        cm.createConnection("con1", "cat1");
    }

    @Test(expected = MMNException.class)
    public void testCreateConnectionDuplicateConnection() throws Exception {
        ConnectionManager cm = new ConnectionManager(new Metamodel());

        cm.createConnectionCategory("cat3");
        cm.createConnection("con3", "cat3");
        cm.createConnection("con3", "cat3");
        assertEquals("con3", cm.getConnection("con3").getConId());
        assertEquals("cat3", cm.getConnectionCategoryByCatName("cat3").getCategoryId());
    }

    @Test
    public void testRemoveConnection() throws MMNException {
        ConnectionManager cm = new ConnectionManager(new Metamodel());

        cm.createConnectionCategory("cat macek");
        cm.createConnection("con macek", "cat macek");
        cm.removeConnection("con macek");
        assertEquals(null, cm.getConnection("con macek"));
    }

    @Test
    public void testRemoveConnectionNullConnection() throws MMNException {
        ConnectionManager cm = new ConnectionManager(new Metamodel());

        cm.removeConnection("con macek kecam");
        assertEquals(null, cm.getConnection("con macek kecam"));
    }

    @Test
    public void testGetConnectionWidget() throws MMNException {
        ConnectionManager cm = new ConnectionManager(new Metamodel());

        cm.createConnectionCategory("cat");
        cm.createConnection("con", "cat");
        cm.getConnectionWidget(new Scene(), "con");
    }

    @Test
    public void testCreateConnectionCategory() throws Exception {
        ConnectionManager cm = new ConnectionManager(new Metamodel());

        cm.createConnectionCategory("code conventions are absolute!");
        assertEquals("code conventions are absolute!", cm.getConnectionCategoryByCatName("code conventions are absolute!").getCategoryId());
    }

    @Test(expected = MMNException.class)
    public void testCreateConnectionCategoryExistingCategory() throws Exception {
        ConnectionManager cm = new ConnectionManager(new Metamodel());

        cm.createConnectionCategory("and you should follow them...");
        cm.createConnectionCategory("and you should follow them...");
    }

    @Test
    public void testGetConnectionCategoryByCatNameNullCategory() throws MMNException {
        ConnectionManager cm = new ConnectionManager(new Metamodel());

        cm.createConnectionCategory("category");

        assertEquals(null, cm.getConnectionCategoryByCatName("doesnt exist"));
    }

    @Test
    public void testGetConnectionCategoryByConId() throws MMNException {
        ConnectionManager cm = new ConnectionManager(new Metamodel());

        cm.createConnectionCategory("if you wont...");
        cm.createConnection("there are consequences", "if you wont...");
        cm.createConnection("you should get ready!", "if you wont...");
        assertEquals("if you wont...", cm.getConnectionCategoryByConId("there are consequences").getCategoryId());
        assertEquals("if you wont...", cm.getConnectionCategoryByConId("you should get ready!").getCategoryId());
    }

    @Test(expected = MMNException.class)
    public void testCreateRuleNullConnection() throws Exception {
        ConnectionManager cm = new ConnectionManager(new Metamodel());

        cm.createRule("becasuse these simple rules", "can make testers life", 112358, "so much easier and", 132134);
    }

    @Test(expected = MMNException.class)
    public void testCreateRuleNullSource() throws Exception {
        IMetamodel mm = new Metamodel();
        ConnectionManager cm = new ConnectionManager(mm);
        ShapeManager sm = new ShapeManager(mm);

        cm.createConnectionCategory("so less painfull...");
        cm.createConnection("to make some conclusion,", "so less painfull...");
        sm.createShapeCategory("if you will some day");
        sm.createShape("write some code", "if you will some day");

        cm.createRule("to make some conclusion,", "please have in mind", 123321, "write some code", 14111);
    }

    @Test(expected = MMNException.class)
    public void testCreateRuleNullTarget() throws Exception {
        IMetamodel mm = new Metamodel();
        ConnectionManager cm = new ConnectionManager(mm);
        ShapeManager sm = new ShapeManager(mm);

        cm.createConnectionCategory("that other people");
        cm.createConnection("like testers", "that other people");
        sm.createShapeCategory("will have to go");
        sm.createShape("through your code", "will have to go");

        cm.createRule("like testers", "through your code", 123321, "thanks from all the future testers!", 14111);
    }

    @Test(expected = MMNException.class)
    public void testCreateRuleExists() throws Exception {
        IMetamodel mm = new Metamodel();
        ConnectionManager cm = new ConnectionManager(mm);
        ShapeManager sm = new ShapeManager(mm);

        cm.createConnectionCategory("conCat");
        cm.createConnection("con1", "conCat");
        sm.createShapeCategory("shapeCat");
        sm.createShape("shape1", "shapeCat");
        sm.createShape("shape2", "shapeCat");

        cm.createRule("con1", "shape1", 1111, "shape2", 2222);
        cm.createRule("con1", "shape1", 233, "shape2", 333);
    }

    @Test
    public void testRemoveRuleNullConnection() {
        ConnectionManager cm = new ConnectionManager(new Metamodel());

        cm.removeRule("doesnt exist", "src", "target");
    }

    @Test
    public void testRemoveRuleDontExist() throws MMNException {
        IMetamodel mm = new Metamodel();
        ConnectionManager cm = new ConnectionManager(mm);
        ShapeManager sm = new ShapeManager(mm);

        cm.createConnectionCategory("conCat");
        cm.createConnection("con1", "conCat");
        sm.createShapeCategory("shapeCat");
        sm.createShape("shape1", "shapeCat");
        sm.createShape("shape2", "shapeCat");
        sm.createShape("shape3", "shapeCat");
        sm.createShape("shape4", "shapeCat");

        cm.createRule("con1", "shape1", 1111, "shape2", 2222);
        cm.createRule("con1", "shape3", 11, "shape4", 22);

        cm.removeRule("con1", "doesnt exist", "doesnt exist2");

        assertEquals(2, cm.getRules("con1").size());
    }

    @Test
    public void testRemoveRule() throws MMNException {
        IMetamodel mm = new Metamodel();
        ConnectionManager cm = new ConnectionManager(mm);
        ShapeManager sm = new ShapeManager(mm);

        cm.createConnectionCategory("conCat");
        cm.createConnection("con1", "conCat");
        sm.createShapeCategory("shapeCat");
        sm.createShape("shape1", "shapeCat");
        sm.createShape("shape2", "shapeCat");
        sm.createShape("shape3", "shapeCat");
        sm.createShape("shape4", "shapeCat");

        cm.createRule("con1", "shape1", 1111, "shape2", 2222);
        cm.createRule("con1", "shape3", 11, "shape4", 22);

        cm.removeRule("con1", "shape1", "shape2");

        assertEquals(1, cm.getRules("con1").size());
        assertEquals(11, cm.getRules("con1").get(0).getSourcM());
        assertEquals("shape3", cm.getRules("con1").get(0).getSource().getShapeId());
        assertEquals(22, cm.getRules("con1").get(0).getTargetM());
        assertEquals("shape4", cm.getRules("con1").get(0).getTarget().getShapeId());
    }
}
