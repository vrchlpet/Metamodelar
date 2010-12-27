package org.cvut.MMN.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sabolmi1
 */
public class ConnectionCategoryTest {

    public ConnectionCategoryTest() {
    }

    @Test
    public void testAddConnection() {
        System.out.println("addConnection");
        Connection con = null;
        ConnectionCategory instance = new ConnectionCategory();
        instance.addConnection(con);

        int resultSize = instance.getConnection().size();
        assertEquals(1, resultSize);

        Connection conResult = instance.getConnection().get(0);
        assertEquals(null, conResult);

        Connection con2 = new Connection();
        instance.addConnection(con2);

        resultSize = instance.getConnection().size();
        assertEquals(2, resultSize);
        conResult = instance.getConnection().get(1);
        assertEquals(con2, conResult);

        Connection con3 = new Connection("abc");
        instance.addConnection(con3);

        resultSize = instance.getConnection().size();
        assertEquals(3, resultSize);
        conResult = instance.getConnection().get(2);
        assertEquals(con3, conResult);
        assertEquals("abc", conResult.getConId());
    }

    @Test
    public void testSetGetCategoryId() {
        System.out.println("setCategoryId");
        ConnectionCategory instance = new ConnectionCategory();

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
