package org.cvut.MMN.model.exceptions;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sabolmi1
 */
public class MMNExceptionTest {

    public MMNExceptionTest() {
    }

    @Test
    public void testGetSetMsg() {
        System.out.println("getMsg");
        MMNException instance = new MMNException(null);
        String result = instance.getMsg();
        assertEquals(null, result);

        instance = new MMNException("abc");
        result = instance.getMsg();
        assertEquals("abc", result);

        instance.setMsg("lolo");
        result = instance.getMsg();
        assertEquals("lolo", result);
    }
}
