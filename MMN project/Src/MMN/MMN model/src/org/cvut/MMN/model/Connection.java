

package org.cvut.MMN.model;

import org.cvut.MMN.model.attributes.ConnectionAttribute;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Vrchli
 */
public class Connection {
    private ArrayList<ConnectionRule> rules;
    private ArrayList<ConnectionAttribute> attributes;
    private String conId;

    public Connection() {
        this("unknown");
    }

    public Connection(String id) {
        this.conId = id;
        rules = new ArrayList<ConnectionRule>();
        attributes = new ArrayList<ConnectionAttribute>();
    }


    public void addAttribute(ConnectionAttribute at) {
        attributes.add(at);
    }

    public ArrayList<ConnectionAttribute> getAttributes() {
        return attributes;
    }

    public void addRule(ConnectionRule rule) {
        rules.add(rule);
    }

    @XmlElement(name = "ConnectionRule")
    public ArrayList<ConnectionRule> getRules() {
        if (rules == null) {
            rules = new ArrayList<ConnectionRule>();
        }
        return rules;
    }

    @XmlAttribute()
    public String getConId() {
        return conId;
    }

    public void setConId(String conId) {
        this.conId = conId;
    }


}
