package org.cvut.MMN.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matej
 */
public class ConnectionRuleTest {

    public ConnectionRuleTest() {
    }

    @Test
    public void testConnectionRule() {
        ConnectionRule cr = new ConnectionRule();

        assertEquals("unknown", cr.getSource().getShapeId());
        assertEquals("unknown", cr.getTarget().getShapeId());
        assertEquals(0, cr.getSourcM());
        assertEquals(0, cr.getTargetM());

        Shape src = new Shape("macek");
        Shape trg = new Shape("salek");
        ConnectionRule cr2 = new ConnectionRule();
        cr2.setSourcM(33);
        cr2.setTargetM(6456);
        cr2.setSource(src);
        cr2.setTarget(trg);

        assertEquals("macek", cr2.getSource().getShapeId());
        assertEquals("salek", cr2.getTarget().getShapeId());
        assertEquals(33, cr2.getSourcM());
        assertEquals(6456, cr2.getTargetM());
    }
}
