/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cvut.MMN.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 *
 * @author sirljan
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Source_QNAME = new QName("", "source");
    private final static QName _SourceM_QNAME = new QName("", "sourceM");
    private final static QName _Target_QNAME = new QName("", "target");
    private final static QName _TargetM_QNAME = new QName("", "targetM");

    public ObjectFactory() {
    }

    public Shape createShape() {
        return new Shape();
    }

    public Connection createConnection() {
        return new Connection();
    }

    public ConnectionRule createConnectionRule() {
        return new ConnectionRule();
    }

    public ShapeCategory createShapeCategory() {
        return new ShapeCategory();
    }

    public Metamodel createMetamodel() {
        return new Metamodel();
    }

    public ConnectionCategory createConCategory() {
        return new ConnectionCategory();
    }

    @XmlElementDecl(namespace = "", name = "source")
    public JAXBElement<Object> createSource(Object value) {
        return new JAXBElement<Object>(_Source_QNAME, Object.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "sourceM")
    public JAXBElement<Integer> createSourceM(Integer value) {
        return new JAXBElement<Integer>(_SourceM_QNAME, Integer.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "target")
    public JAXBElement<Object> createTarget(Object value) {
        return new JAXBElement<Object>(_Target_QNAME, Object.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "targetM")
    public JAXBElement<Integer> createTargetM(Integer value) {
        return new JAXBElement<Integer>(_TargetM_QNAME, Integer.class, null, value);
    }
}
