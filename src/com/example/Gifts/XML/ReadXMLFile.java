package com.example.Gifts.XML;

import android.content.Context;
import android.os.Environment;

import com.example.Gifts.Classes.Gift_Class;
import com.example.Gifts.Classes.Group_Class;
import com.example.Gifts.Classes.Person_Class;
import com.example.Gifts.Classes.Store_Class;
import com.example.Gifts.MyActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Anosh on 11/20/2014.
 */
public class ReadXMLFile {
    public ArrayList<Person_Class> person;
    public ArrayList<Gift_Class> gift;
    public ArrayList<Group_Class> group;
    public ArrayList<Store_Class> store;
    MyActivity context;

    public ReadXMLFile(Context context) {
        this.context = (MyActivity) context;
        this.person = MyActivity.person;
        this.gift = MyActivity.gift;
        this.group = MyActivity.group;
        this.store = MyActivity.store;

        this.person.clear();
        this.gift.clear();
        this.group.clear();
        this.store.clear();

        readPerson();
        readGift();
        readGroup();
        readStore();

    }

    public void readPerson() {
        try {
            File fXmlFile = new File(Environment.getDataDirectory() + "/data/com.example.Gifts/person.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nListPerson = doc.getElementsByTagName("Person");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nListPerson.getLength(); temp++) {

                Node nNode = nListPerson.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());


                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    person.add(new Person_Class(eElement.getAttribute("id"), eElement.getElementsByTagName("name").item(0).getTextContent(), eElement.getElementsByTagName("image").item(0).getTextContent(),
                            eElement.getElementsByTagName("budget").item(0).getTextContent(), eElement.getElementsByTagName("group").item(0).getTextContent(), eElement.getElementsByTagName("contact").item(0).getTextContent()));
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readGift() {
        try {
            File fXmlFile = new File(Environment.getDataDirectory() + "/data/com.example.Gifts/gift.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();


            NodeList nListGift = doc.getElementsByTagName("Gift");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nListGift.getLength(); temp++) {

                Node nNode = nListGift.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());


                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    gift.add(new Gift_Class(eElement.getAttribute("id"), eElement.getElementsByTagName("name").item(0).getTextContent(), eElement.getElementsByTagName("amount").item(0).getTextContent(),
                            eElement.getElementsByTagName("person").item(0).getTextContent(), eElement.getElementsByTagName("status").item(0).getTextContent(),
                            eElement.getElementsByTagName("image").item(0).getTextContent(), eElement.getElementsByTagName("store").item(0).getTextContent()));
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readGroup() {
        try {
            File fXmlFile = new File(Environment.getDataDirectory() + "/data/com.example.Gifts/group.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nListGroup = doc.getElementsByTagName("Group");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nListGroup.getLength(); temp++) {

                Node nNode = nListGroup.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                outter:
                {
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;

                        group.add(new Group_Class(eElement.getAttribute("id"), eElement.getElementsByTagName("name").item(0).getTextContent(), eElement.getElementsByTagName("image").item(0).getTextContent(), eElement.getElementsByTagName("budget").item(0).getTextContent()));
                        break outter;
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readStore() {
        try {
            File fXmlFile = new File(Environment.getDataDirectory() + "/data/com.example.Gifts/store.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nListStore = doc.getElementsByTagName("Store");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nListStore.getLength(); temp++) {

                Node nNode = nListStore.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());


                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    store.add(new Store_Class(eElement.getAttribute("id"), eElement.getElementsByTagName("name").item(0).getTextContent()));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
