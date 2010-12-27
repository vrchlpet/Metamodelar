package org.cvut.MMN.model;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Vrchli
 */
public class ConnectionCategory {

    private ArrayList<Connection> connections;
    private String categoryId;

    public ConnectionCategory() {
        this("unknown");
    }

    public ConnectionCategory(String id) {
        this.categoryId = id;
        connections = new ArrayList<Connection>();
    }

    public void addConnection(Connection con) {
        connections.add(con);
    }

    @XmlElement(name = "Connection")
    public ArrayList<Connection> getConnection() {
        if (connections == null) {
            connections = new ArrayList<Connection>();
        }
        return this.connections;
    }

    public ArrayList<Connection> getConnections() {
        return this.connections;
    }

    @XmlAttribute()
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
