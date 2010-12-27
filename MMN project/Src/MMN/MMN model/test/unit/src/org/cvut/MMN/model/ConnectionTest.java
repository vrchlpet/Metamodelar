package org.cvut.MMN.model;

import java.util.ArrayList;
import org.cvut.MMN.model.attributes.ConnectionAttribute;
import org.cvut.MMN.model.attributes.ConnectionSourceShape;
import org.cvut.MMN.model.attributes.ConnectionTargetShape;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sabolmi1
 */
public class ConnectionTest {

    public ConnectionTest() {
    }

    /**
     * Test of addAttribute method, of class Connection.
     */
    @Test
    public void testAddAttribute() {
        System.out.println("addAttribute");
        ConnectionAttribute at = null;
        Connection instance = new Connection();
        instance.addAttribute(at);
        int resultSize = instance.getAttributes().size();
        assertEquals(1, resultSize);
        ConnectionAttribute resultAt = instance.getAttributes().get(0);
        assertEquals(null, resultAt);

        String image = "ea.jpg";
        ConnectionAttribute at2 = new ConnectionSourceShape(image);
        instance.addAttribute(at2);
        resultSize = instance.getAttributes().size();
        assertEquals(2, resultSize);
        resultAt = instance.getAttributes().get(1);
        assertEquals(at2, resultAt);

        ConnectionAttribute at3 = new ConnectionTargetShape(image);
        instance.addAttribute(at3);
        resultSize = instance.getAttributes().size();
        assertEquals(3, resultSize);
        resultAt = instance.getAttributes().get(2);
        assertEquals(at3, resultAt);
    }

    /**
     * Test of getAttributes method, of class Connection.
     */
    @Test
    public void testGetAttributes() {
        System.out.println("getAttributes");
        Connection instance = new Connection();
        ArrayList expResult = new ArrayList();
        ArrayList result = instance.getAttributes();
        assertEquals(expResult.size(), result.size());
        ConnectionAttribute at = null;
        instance.addAttribute(at);
        result = instance.getAttributes();
        expResult.add(0, at);
        assertEquals(expResult.size(), result.size());
        for (int i = 0; i < expResult.size(); i++) {
            assertEquals(expResult.get(i), result.get(i));
        }
    }

    /**
     * Test of addRule method, of class Connection.
     */
    @Test
    public void testAddRule() {
        System.out.println("addRule");
        ConnectionRule rule = null;
        Connection instance = new Connection();
        instance.addRule(rule);
        int resultSize = instance.getRules().size();
        assertEquals(1, resultSize);
        ConnectionRule resultRule = instance.getRules().get(0);
        assertEquals(null, resultRule);

        Shape srcShape = new Shape("src");
        Shape trgShape = new Shape("trg");
        ConnectionRule rule2 = new ConnectionRule(srcShape, 12, trgShape, 3);
        instance.addRule(rule2);
        resultSize = instance.getRules().size();
        assertEquals(2, resultSize);
        resultRule = instance.getRules().get(1);
        assertEquals(12, resultRule.getSourcM());
        assertEquals(3, resultRule.getTargetM());
        assertEquals("src", resultRule.getSource().getShapeId());
        assertEquals("trg", resultRule.getTarget().getShapeId());
    }

    /**
     * Test of getRules method, of class Connection.
     */
    @Test
    public void testGetRules() {
        System.out.println("getRules");
        Connection instance = new Connection();
        ArrayList expResult = new ArrayList();
        ArrayList result = instance.getRules();
        assertEquals(expResult.size(), result.size());

        Shape srcShape = new Shape("src");
        Shape trgShape = new Shape("trg");
        ConnectionRule rule = new ConnectionRule(srcShape, 12, trgShape, 3);
        instance.addRule(rule);
        result = instance.getRules();
        expResult.add(0, rule);
        assertEquals(expResult.size(), result.size());
        for (int i = 0; i < expResult.size(); i++) {
            assertEquals(12, rule.getSourcM());
            assertEquals(3, rule.getTargetM());
            assertEquals("src", rule.getSource().getShapeId());
            assertEquals("trg", rule.getTarget().getShapeId());
        }
    }

    /**
     * Test of getConId method, of class Connection.
     */
    @Test
    public void testGetConId() {
        System.out.println("getConId");
        Connection instance = new Connection();
        String expResult = "unknown";
        String result = instance.getConId();
        assertEquals(expResult, result);
        instance.setConId("abc");
        expResult = "abc";
        result = instance.getConId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConId method, of class Connection.
     */
    @Test
    public void testSetConId() {
        System.out.println("setConId");
        Connection instance = new Connection();
        String result = instance.getConId();
        assertEquals("unknown", result);
        String conId = "1";
        instance.setConId(conId);
        result = instance.getConId();
        assertEquals("1", result);
        conId = "394898439dfsfcss";
        instance.setConId(conId);
        result = instance.getConId();
        assertEquals("394898439dfsfcss", result);
    }
}
