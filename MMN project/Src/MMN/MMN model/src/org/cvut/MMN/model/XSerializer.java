

package org.cvut.MMN.model;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.cvut.MMN.model.attributes.ConnectionAttribute;
import org.cvut.MMN.model.attributes.ConnectionSourceShape;
import org.cvut.MMN.model.attributes.ConnectionTargetShape;
import org.cvut.MMN.model.attributes.ImageAttribute;
import org.cvut.MMN.model.attributes.LabelAttribute;
import org.cvut.MMN.model.attributes.ShapeAttribute;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author Vrchli
 */
@ServiceProvider(service = ISerializer.class)
public class XSerializer implements ISerializer{


    /*  === Example of metamodel xml ===
     *
     *  <metamodel mmName="...">
     *      <shapes>
     *          <shapeCategory shapeCatName="...">
     *              <shape shapeName="...">
     *                  <shapeAtt shapeAttName="...">
     *                      <shapeAttValue valueName="...">...</shapeAttValue>
     *                      <shapeAttValue ...>...</shapeAttValue>
     *                              ...
     *                  </shapeAtt>
     *                  <shapeAtt ...>
     *                      ...
     *                  </shapeAtt>
     *                      ...
     *              </shape>
     *          </shapeCategory>
     *          <shapeCategory ...>
     *              ...
     *          </shapeCategory>
     *              ...
     *      </shapes>
     *
     *
     *      <connections>
     *          <conCategory conCatName="...">
     *              <connection conName="...">
     *                  <conAtt conAttName="...">
     *                      <conAttValue valueName="...">...</conAttValue>
     *                      <conAttValue ...>...</conAttValue>
     *                          ...
     *                  </conAtt>
     *                  <conAtt ...>
     *                      ...
     *                  </conAtt>
     *                      ...
     *
     *                  <rules>
     *                      <rule>
     *                          <source>...</source>
     *                          <sourceMultiplicity>...</sourceMultipliity>
     *                          <target>...</target>
     *                          <targetMultiplicity>...</targetMultiplicity>
     *                      </rule>
     *                      <rule>
     *                          ...
     *                      </rule>
     *                          ...
     *                  </rules>
     *              </connection>
     *          </conCategory>
     *          <conCategry ...>
     *              ...
     *          </conCategory>
     *              ...
     *      </connections>
     *  </metamodel>
     */

    public static final String MM_ROOT = "metamodel";
    public static final String MM_NAME_ATT = "mmName";
    public static final String SHAPES = "shapes";
    public static final String SHAPE_CATEGORY = "shapeCategory";
    public static final String SHAPE_CATEGORY_NAME_ATT = "shapeCatName";
    public static final String SHAPE = "shape";
    public static final String SHAPE_NAME_ATT = "shapeName";
    public static final String SHAPE_ATT = "shapeAtt";
    public static final String SHAPE_ATT_NAME_ATT = "shapeAttName";
    public static final String SHAPE_ATT_VALUE = "shapeAttValue";
    public static final String SHAPE_ATT_VALUE_NAME_ATT = "valueName";

    public static final String CONNECTIONS = "connections";
    public static final String CON_CATEGORY = "conCategory";
    public static final String CON_CATEGORY_NAME_ATT = "conCatName";
    public static final String CONNECTION = "connection";
    public static final String CON_NAME_ATT = "conName";
    public static final String CON_ATT = "conAtt";
    public static final String CON_ATT_NAME_ATT = "conAttName";
    public static final String CON_ATT_VALUE = "conAttValue";
    public static final String CON_ATT_VALUE_NAME_ATT = "valueName";
    
    public static final String RULES = "rules";
    public static final String RULE = "rule";
    public static final String SOURCE = "source";
    public static final String TARGET = "target";
    public static final String SOURCE_MULTIPLICITY = "sourceMultiplicity";
    public static final String TARGET_MULTIPLICITY = "targetMultiplicity";


    public static final String SHAPE_IMAGE_ATT = "shapeImageAtt";
        public static final String IMAGE_PATH = "imagePath";
    public static final String SHAPE_LABEL_ATT = "shapeLabelAtt";
        public static final String LABEL_TEXT = "labelText";
    public static final String CON_SOURCE_SHAPE_ATT = "conSourceShapeAtt";
        // IMAGE_PATH is used as a conAttValue.valueName
    public static final String CON_TARGET_SHAPE_ATT = "conTargetShapeAtt";
        // IMAGE_PATH is used as a conAttValue.valueName

    
    private IMetamodel mm;
    public static final String METAMODEL_SUFFIX = "mm";

    @Override
    public IMetamodel loadMetamodel(String path) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(path);

            
            NodeList nodes = doc.getElementsByTagName(MM_ROOT);
            //System.out.println(nodes.item(0).getAttributes().getNamedItem(MM_NAME_ATT).getTextContent());
            this.mm = new Metamodel(nodes.item(0).getAttributes().getNamedItem(MM_NAME_ATT).getTextContent());

            NodeList shapeCategories = doc.getElementsByTagName(SHAPE_CATEGORY);
            for ( int i = 0; i < shapeCategories.getLength(); i++) {
                String catName = shapeCategories.item(i).getAttributes()
                        .getNamedItem(SHAPE_CATEGORY_NAME_ATT).getTextContent();
                mm.getShapeManager().createShapeCategory(catName);
            }

            NodeList shapes = doc.getElementsByTagName(SHAPE);
            for ( int i = 0; i < shapes.getLength(); i++) {
                String shapeName = shapes.item(i).getAttributes()
                        .getNamedItem(SHAPE_NAME_ATT).getTextContent();
                String shapeCat = shapes.item(i).getParentNode().getAttributes()
                        .getNamedItem(SHAPE_CATEGORY_NAME_ATT).getTextContent();
                mm.getShapeManager().createShape(shapeName, shapeCat);

                Shape shape = mm.getShapeManager().getShape(shapeName);
                NodeList shapeAttributes = shapes.item(i).getChildNodes();
                for ( int e = 0; e < shapeAttributes.getLength(); e++) {
                    Node shapeAttribute = shapeAttributes.item(e);
                    shape.addAttribute(getShapeAttribute(shapeAttribute));
                }

            }
 
            NodeList ConCategories = doc.getElementsByTagName(CON_CATEGORY);
            for ( int i = 0; i < ConCategories.getLength(); i++) {
                String catName = ConCategories.item(i).getAttributes()
                        .getNamedItem(CON_CATEGORY_NAME_ATT).getTextContent();
                mm.getConnectionManager().createConnectionCategory(catName);
            }

            NodeList connections = doc.getElementsByTagName(CONNECTION);
            for ( int i = 0; i < connections.getLength(); i++) {
                String conName = connections.item(i).getAttributes()
                        .getNamedItem(CON_NAME_ATT).getTextContent();
                String conCat = connections.item(i).getParentNode().getAttributes()
                        .getNamedItem(CON_CATEGORY_NAME_ATT).getTextContent();
                mm.getConnectionManager().createConnection(conName, conCat);

                Connection con = mm.getConnectionManager().getConnection(conName);
                NodeList conAttributes = connections.item(i).getChildNodes();
                for ( int e = 0; e < conAttributes.getLength(); e++) {
                    if ( conAttributes.item(e).getNodeName().equals(CON_ATT)) {
                        Node conAttribute = conAttributes.item(e);
                        con.addAttribute(getConAttribute(conAttribute));
                    } else if ( conAttributes.item(e).getNodeName().equals(RULES)) {
                        NodeList rules = conAttributes.item(e).getChildNodes();
                        for (int j = 0; j < rules.getLength(); j++) {
                            con.addRule(getConnectionRule(rules.item(j)));
                        }
                    }
                }
            }

        } catch (MMNException ex) {
            Exceptions.printStackTrace(ex);
        } catch (SAXException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (ParserConfigurationException ex) {
            Exceptions.printStackTrace(ex);
        }



        /* provizorni reseni pro testovani */
        //return testLoadMetamodel(path);

        return this.mm;
    }

    @Override
    public void saveMetamodel(IMetamodel mm, String path) {
        this.mm = mm;
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = factory.newDocumentBuilder();
            Document doc = parser.newDocument();


            Element root = doc.createElement(MM_ROOT);
            root.setAttribute(MM_NAME_ATT,this.mm.getName());
            doc.appendChild(root);
            Element shapes = doc.createElement(SHAPES);
            root.appendChild(shapes);

            for ( ShapeCategory sc : mm.getShapeCategories()) {
                Element e = doc.createElement(SHAPE_CATEGORY);
                e.setAttribute(SHAPE_CATEGORY_NAME_ATT, sc.getCategoryId());
                for ( Shape shape : sc.getShapes()) {
                    Element ee = doc.createElement(SHAPE);
                    ee.setAttribute(SHAPE_NAME_ATT, shape.getShapeId());
                    for ( ShapeAttribute sa : shape.getAttributes()) {
                        ee.appendChild(getAttributeElement(sa, doc));
                    }
                    e.appendChild(ee);
                }
                shapes.appendChild(e);
            }


            Element connections = doc.createElement(CONNECTIONS);
            root.appendChild(connections);

            for ( ConnectionCategory cc : mm.getConCategories()) {
                Element e = doc.createElement(CON_CATEGORY);
                e.setAttribute(CON_CATEGORY_NAME_ATT, cc.getCategoryId());
                for ( Connection con : cc.getConnection()) {
                    Element ee = doc.createElement(CONNECTION);
                    ee.setAttribute(CON_NAME_ATT, con.getConId());
                    for ( ConnectionAttribute ca : con.getAttributes()) {
                        ee.appendChild(getAttributeElement(ca, doc));
                    }
                    e.appendChild(ee);
                    
                    Element rules = doc.createElement(RULES);
                    for (ConnectionRule cr : con.getRules()) {
                        Element rule = doc.createElement(RULE);
                        Element source = doc.createElement(SOURCE);
                        Element sourceM = doc.createElement(SOURCE_MULTIPLICITY);
                        Element target = doc.createElement(TARGET);
                        Element targetM = doc.createElement(TARGET_MULTIPLICITY);
                        Text sourceText = doc.createTextNode(cr.getSource().getShapeId());
                        Text sourceMText = doc.createTextNode("" + cr.getSourcM());
                        Text targetText = doc.createTextNode(cr.getTarget().getShapeId());
                        Text targetMText = doc.createTextNode("" + cr.getTargetM());
                        rule.appendChild(source);
                        rule.appendChild(sourceM);
                        rule.appendChild(target);
                        rule.appendChild(targetM);
                        source.appendChild(sourceText);
                        sourceM.appendChild(sourceMText);
                        target.appendChild(targetText);
                        targetM.appendChild(targetMText);
                        rules.appendChild(rule);
                    }
                    ee.appendChild(rules);
                }
                connections.appendChild(e);
            }

            File f = new File(path);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer writer = tf.newTransformer();
            writer.setOutputProperty(OutputKeys.ENCODING, "windows-1250");
            writer.transform(new DOMSource(doc), new StreamResult(f));

        } catch (TransformerException ex) {
            Exceptions.printStackTrace(ex);
        } catch (ParserConfigurationException ex) {
            Exceptions.printStackTrace(ex);
        }


    }

    private Element getAttributeElement(ShapeAttribute sa, Document doc) {
        Element e = null;


        if ( sa instanceof ImageAttribute) {
            e = doc.createElement(SHAPE_ATT);
            e.setAttribute(SHAPE_ATT_NAME_ATT, SHAPE_IMAGE_ATT);
            Element ee = doc.createElement(SHAPE_ATT_VALUE);
            ee.setAttribute(SHAPE_ATT_VALUE_NAME_ATT, IMAGE_PATH);
            e.appendChild(ee);
            Text text = doc.createTextNode(((ImageAttribute)sa).getPath());
            ee.appendChild(text);
        } else if (sa instanceof LabelAttribute) {
            e = doc.createElement(SHAPE_ATT);
            e.setAttribute(SHAPE_ATT_NAME_ATT, SHAPE_LABEL_ATT);
            Element ee = doc.createElement(SHAPE_ATT_VALUE);
            ee.setAttribute(SHAPE_ATT_VALUE_NAME_ATT, LABEL_TEXT);
            e.appendChild(ee);
            Text text = doc.createTextNode(((LabelAttribute)sa).getLabelText());
            ee.appendChild(text);
        }
        return e;
    }

    private Element getAttributeElement(ConnectionAttribute ca, Document doc) {
        Element e = null;

        if ( ca instanceof ConnectionSourceShape) {
            e = doc.createElement(CON_ATT);
            e.setAttribute(CON_ATT_NAME_ATT, CON_SOURCE_SHAPE_ATT);
            Element ee = doc.createElement(CON_ATT_VALUE);
            ee.setAttribute(CON_ATT_VALUE_NAME_ATT, IMAGE_PATH);
            e.appendChild(ee);
            Text text = doc.createTextNode(((ConnectionSourceShape)ca).getPath());
            ee.appendChild(text);
        } else if (ca instanceof ConnectionTargetShape) {
            e = doc.createElement(CON_ATT);
            e.setAttribute(CON_ATT_NAME_ATT, CON_TARGET_SHAPE_ATT);
            Element ee = doc.createElement(CON_ATT_VALUE);
            ee.setAttribute(CON_ATT_VALUE_NAME_ATT, IMAGE_PATH);
            e.appendChild(ee);
            Text text = doc.createTextNode(((ConnectionTargetShape)ca).getPath());
            ee.appendChild(text);
        }


        return e;
    }

    private ConnectionRule getConnectionRule(Node n) {
        ConnectionRule cr = null;
        String source = null;
        String target = null;
        int sourceM = 0;
        int targetM = 0;

        NodeList ruleItems = n.getChildNodes();

        for ( int i = 0; i < ruleItems.getLength(); i++) {
            if ( ruleItems.item(i).getNodeName().equals(SOURCE)) {
                source = ruleItems.item(i).getChildNodes().item(0).getNodeValue();
            } else if ( ruleItems.item(i).getNodeName().equals(SOURCE_MULTIPLICITY)) {
                sourceM = Integer.parseInt(ruleItems.item(i).getChildNodes().item(0).getNodeValue());
            } else if ( ruleItems.item(i).getNodeName().equals(TARGET)) {
                target = ruleItems.item(i).getChildNodes().item(0).getNodeValue();
            } else if ( ruleItems.item(i).getNodeName().equals(TARGET_MULTIPLICITY)) {
                targetM = Integer.parseInt(ruleItems.item(i).getChildNodes().item(0).getNodeValue());
            }
        }

        cr = new ConnectionRule(mm.getShapeManager().getShape(source), sourceM,
                mm.getShapeManager().getShape(target), targetM);



        return cr;
    }

    private ConnectionAttribute getConAttribute(Node n) {
        ConnectionAttribute at = null;

        String conType = n.getAttributes().getNamedItem(CON_ATT_NAME_ATT).getTextContent();
        if ( conType.endsWith(CON_SOURCE_SHAPE_ATT)) {
            String imagePath = n.getChildNodes().item(0).getChildNodes().item(0).getNodeValue();
            at = new ConnectionSourceShape(imagePath);
        } else if (conType.equals(CON_TARGET_SHAPE_ATT)) {
            String imagePath = n.getChildNodes().item(0).getChildNodes().item(0).getNodeValue();
            at = new ConnectionTargetShape(imagePath);
        }

        return at;
    }

    private ShapeAttribute getShapeAttribute(Node n) {
        ShapeAttribute at = null;

        String shapeType = n.getAttributes().getNamedItem(SHAPE_ATT_NAME_ATT).getTextContent();
        if ( shapeType.endsWith(SHAPE_IMAGE_ATT)) {
            String imagePath = n.getChildNodes().item(0).getChildNodes().item(0).getNodeValue();
            at = new ImageAttribute(imagePath);
        } else if ( shapeType.equals(SHAPE_LABEL_ATT)) {
            String labelTest = n.getChildNodes().item(0).getChildNodes().item(0).getNodeValue();
            at = new LabelAttribute(labelTest);
        }

        return at;
    }

    public IMetamodel testLoadMetamodel(String path){
        IMetamodel metamodel = new Metamodel("mm");


        String defaultCat = "Default category";
        String customCat = "Custom category";
        String associationCon = "Association";
        String generalizationCon = "Generalization";

        String shapeA = "eaLogo";
        String shapeB = "java";

        String img1 = "org/cvut/MMN/model/pictures/java.png";
        String img2 = "org/cvut/MMN/model/pictures/ea.jpg";
        try {
            metamodel.getConnectionManager().createConnectionCategory(defaultCat);
            metamodel.getConnectionManager().createConnectionCategory(customCat);

            metamodel.getConnectionManager().createConnection(associationCon, defaultCat);
            metamodel.getConnectionManager().createConnection(generalizationCon, defaultCat);
        } catch (MMNException ex) {
            Exceptions.printStackTrace(ex);
        }
        

        metamodel.getConnectionManager().getConnection(generalizationCon).addAttribute(
                new ConnectionTargetShape("org/cvut/MMN/model/pictures/generalizationArrow.png"));
        try {
            metamodel.getShapeManager().createShapeCategory(defaultCat);
            metamodel.getShapeManager().createShapeCategory(customCat);
            metamodel.getShapeManager().createShape(shapeA, defaultCat);
            metamodel.getShapeManager().createShape(shapeB, defaultCat);
        } catch (MMNException ex) {
            Exceptions.printStackTrace(ex);
        }
        

        metamodel.getShapeManager().getShape(shapeA).addAttribute(
                new ImageAttribute(img2));
        metamodel.getShapeManager().getShape(shapeB).addAttribute(
                new ImageAttribute(img1));
        metamodel.getShapeManager().getShape(shapeA).addAttribute(
                new LabelAttribute("EnterpriseArchitect"));


        return metamodel;
    }
}
