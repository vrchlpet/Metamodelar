

package org.cvut.MMN.model;

import org.cvut.MMN.model.attributes.ConnectionAttribute;
import java.util.ArrayList;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;

/**
 *
 * @author Vrchli
 */
public class ConnectionManager implements IConnectionManager{
    private IMetamodel mm;

    public ConnectionManager(IMetamodel mm) {
        this.mm = mm;
    }

    @Override
    public Connection createConnection(String conId, String category) throws MMNException {
        ConnectionCategory sc;
        Connection con;
        if ( (sc = getConnectionCategoryByCatName(category)) != null) {
            if ( (con = getConnection(conId)) == null) {
                con = new Connection(conId);
                sc.addConnection(con);
                return con;
            }
        }

        throw new MMNException("error");
    }

    @Override
    public void removeConnection(String conId) {
        Connection con = getConnection(conId);
        ConnectionCategory cat = getConnectionCategoryByConId(conId);

        if (con != null) {
            if (cat != null) {
                cat.getConnection().remove(con);
            }
        }
    }

    @Override
    public ConnectionWidget getConnectionWidget(Scene scene, String conId) {
        ConnectionWidget widget = new ConnectionWidget(scene);

        for ( ConnectionAttribute at: getConnection(conId).getAttributes()) {
            at.apply(widget);
        }

        return widget;
    }

    @Override
    public ConnectionCategory createConnectionCategory(String name) throws MMNException{
        if ( getConnectionCategoryByCatName(name) == null) {
            ConnectionCategory cc = new ConnectionCategory(name);
            mm.getConCategories().add(cc);
            return cc;
        }

        throw new MMNException("error");
    }

    @Override
    public Connection getConnection(String conId){

        for (ConnectionCategory ccat : mm.getConCategories()) {
            for ( Connection c : ccat.getConnection()) {
                if ( c.getConId().equals(conId)) {
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public ConnectionCategory getConnectionCategoryByCatName(String name) {
        for (ConnectionCategory ccat : mm.getConCategories()) {
            if ( ccat.getCategoryId().equals(name))
                return ccat;
        }
        return null;
    }

    @Override
    public ConnectionCategory getConnectionCategoryByConId(String conId) {
        for (ConnectionCategory ccat : mm.getConCategories()) {
            for ( Connection c : ccat.getConnection()) {
                if ( c.getConId().equals(conId))
                    return ccat;
            }
        }
        return null;
    }

    @Override
    public ConnectionRule createRule(String conId, String source, int sM, String target, int tM) throws MMNException {
        Connection con;
        ConnectionRule cr;

        if ((con = getConnection(conId)) != null) {
            for ( ConnectionRule conR : con.getRules()) {
                if ( conR.getTarget().getShapeId().equals(target)
                    && conR.getSource().getShapeId().equals(source))
                    throw new MMNException("error");
            }
            Shape s = mm.getShapeManager().getShape(source);
            Shape t = mm.getShapeManager().getShape(target);
            if ( s != null && t != null) {
                cr = new ConnectionRule(s, sM, t, tM);
                con.addRule(cr);
                return cr;
            }
        }
        throw new MMNException("error");
    }

    @Override
    public void removeRule(String conId, String source, String target){
        Connection con;
        if ( (con = getConnection(conId)) != null) {
            for ( ConnectionRule cr : con.getRules()) {
                if ( cr.getSource().getShapeId().equals(source)
                    && cr.getTarget().getShapeId().equals(target)) {
                    con.getRules().remove(cr);
                    return;
                }
            }
        }
    }

    @Override
    public ArrayList<ConnectionRule> getRules(String conId){
        return getConnection(conId).getRules();
    }
}
