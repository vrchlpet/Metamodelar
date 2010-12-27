

package org.cvut.MMN.model;

import java.util.ArrayList;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;

/**
 *
 * @author Vrchli
 */
public interface IConnectionManager {
    public Connection createConnection(String conId, String category) throws MMNException;
    public void removeConnection(String conId);
    public Connection getConnection(String conId);
    public ConnectionWidget getConnectionWidget(Scene scene, String conId);

    public ConnectionCategory createConnectionCategory(String name) throws MMNException;
    public ConnectionCategory getConnectionCategoryByCatName(String name);
    public ConnectionCategory getConnectionCategoryByConId(String conId);

    public ConnectionRule createRule(String conId, String source, int sM, String target, int tM) throws MMNException;
    public void removeRule(String conId, String source, String target);
    public ArrayList<ConnectionRule> getRules(String conId);
}
