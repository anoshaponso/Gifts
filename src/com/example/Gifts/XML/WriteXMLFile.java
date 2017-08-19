package com.example.Gifts.XML;

import android.os.Environment;

import com.example.Gifts.Classes.Gift_Class;
import com.example.Gifts.Classes.Group_Class;
import com.example.Gifts.Classes.Person_Class;
import com.example.Gifts.Classes.Store_Class;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Anosh on 11/20/2014.
 */
public class WriteXMLFile {
    public WriteXMLFile(ArrayList<Person_Class> person, ArrayList<Gift_Class> gift, ArrayList<Group_Class> group, ArrayList<Store_Class> store) {
        writePerson(person);
        writeGift(gift);
        writeGroup(group);
        writeStore(store);
    }

    public void writePerson(ArrayList<Person_Class> person) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);
            for (int p = 0; p < person.size(); p++) {
                Element per = doc.createElement("Person");
                rootElement.appendChild(per);


                Attr attr = doc.createAttribute("id");
                attr.setValue("" + person.get(p).getId());
                per.setAttributeNode(attr);

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode("" + person.get(p).getName()));
                per.appendChild(name);

                Element image = doc.createElement("image");
                image.appendChild(doc.createTextNode("" + person.get(p).getImage()));
                per.appendChild(image);

                Element budget = doc.createElement("budget");
                budget.appendChild(doc.createTextNode("" + person.get(p).getBudget()));
                per.appendChild(budget);

                Element groupId = doc.createElement("group");
                groupId.appendChild(doc.createTextNode("" + person.get(p).getGroup()));
                per.appendChild(groupId);

                Element contact = doc.createElement("contact");
                contact.appendChild(doc.createTextNode("" + person.get(p).getContact()));
                per.appendChild(contact);
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(Environment.getDataDirectory() + "/data/com.example.Gifts/person.xml"));


            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }

    public void writeGift(ArrayList<Gift_Class> gift) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);
            for (int i = 0; i < gift.size(); i++) {
                Element gif = doc.createElement("Gift");
                rootElement.appendChild(gif);

                Attr giftId = doc.createAttribute("id");
                giftId.setValue("" + gift.get(i).getId());
                gif.setAttributeNode(giftId);

                Element giftName = doc.createElement("name");
                giftName.appendChild(doc.createTextNode("" + gift.get(i).getName()));
                gif.appendChild(giftName);

                Element amount = doc.createElement("amount");
                amount.appendChild(doc.createTextNode("" + gift.get(i).getAmount()));
                gif.appendChild(amount);

                Element perso = doc.createElement("person");
                perso.appendChild(doc.createTextNode("" + gift.get(i).getPersonName()));
                gif.appendChild(perso);

                Element purchased = doc.createElement("status");
                purchased.appendChild(doc.createTextNode("" + gift.get(i).getStatus()));
                gif.appendChild(purchased);

                Element giftImage = doc.createElement("image");
                giftImage.appendChild(doc.createTextNode("" + gift.get(i).getImage()));
                gif.appendChild(giftImage);

                Element giftStore = doc.createElement("store");
                giftStore.appendChild(doc.createTextNode("" + gift.get(i).getStore()));
                gif.appendChild(giftStore);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(Environment.getDataDirectory() + "/data/com.example.Gifts/gift.xml"));


            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }

    public void writeGroup(ArrayList<Group_Class> group) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            for (int i = 0; i < group.size(); i++) {
                Element gro = doc.createElement("Group");
                rootElement.appendChild(gro);


                Attr groupId = doc.createAttribute("id");
                groupId.setValue("" + group.get(i).getId());
                gro.setAttributeNode(groupId);


                Element groupName = doc.createElement("name");
                groupName.appendChild(doc.createTextNode("" + group.get(i).getName()));
                gro.appendChild(groupName);

                Element groupImage = doc.createElement("image");
                groupImage.appendChild(doc.createTextNode("" + group.get(i).getImage()));
                gro.appendChild(groupImage);

                Element groupBudget = doc.createElement("budget");
                groupBudget.appendChild(doc.createTextNode("" + group.get(i).getBudget()));
                gro.appendChild(groupBudget);
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(Environment.getDataDirectory() + "/data/com.example.Gifts/group.xml"));


            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }

    public void writeStore(ArrayList<Store_Class> store) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Class");
            doc.appendChild(rootElement);

            for (int i = 0; i < store.size(); i++) {
                Element gro = doc.createElement("Store");
                rootElement.appendChild(gro);


                Attr groupId = doc.createAttribute("id");
                groupId.setValue("" + store.get(i).getId());
                gro.setAttributeNode(groupId);


                Element groupName = doc.createElement("name");
                groupName.appendChild(doc.createTextNode("" + store.get(i).getName()));
                gro.appendChild(groupName);

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(Environment.getDataDirectory() + "/data/com.example.Gifts/store.xml"));


            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }
//http://stackoverflow.com/questions/11687074/create-xml-file-and-save-it-in-internal-storage-android
}
//http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/