/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cvut.MMN.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author sirljan
 */

public class Serializer implements ISerializer {

    @Override
    public IMetamodel loadMetamodel(String path) {

        try {
            JAXBContext context = JAXBContext.newInstance("org.cvut.MMN.model");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            IMetamodel localmm = (IMetamodel) unmarshaller.unmarshal(new FileReader(path));
            print(localmm);
            return localmm;
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        } catch (JAXBException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        }
    }

    @Override
    public void saveMetamodel(IMetamodel mm, String path) {
        try {
            JAXBContext context = JAXBContext.newInstance(Metamodel.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(mm, new FileWriter(path));
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public void print(IMetamodel localmm){
        for (ConnectionCategory conCa : localmm.getConCategories()) {
            System.out.println("ConnectionCategory = " + conCa.getCategoryId());
            for (Connection con : conCa.getConnection()) {
                System.out.println("    Connection = " + con.getConId());
                for(ConnectionRule rule:con.getRules()){
                    System.out.println("        ruleSourceM = " + rule.getSourcM());
                    System.out.println("        ruleTargetM = " + rule.getTargetM());
                }
            }
        }

        for (ShapeCategory shc : localmm.getShapeCategories()) {
            System.out.println("ShapeCategory = " + shc.getCategoryId());
            for (Shape sh : shc.getShapes()) {
                System.out.println("    Shape = " + sh.getShapeId());
            }
        }
    }
}
