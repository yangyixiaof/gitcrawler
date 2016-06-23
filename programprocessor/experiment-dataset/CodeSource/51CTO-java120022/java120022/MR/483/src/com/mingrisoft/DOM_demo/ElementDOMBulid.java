package com.mingrisoft.DOM_demo;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author bwm
 * 
 */
public class ElementDOMBulid {

	/**
	 * ����XML�ļ�
	 * 
	 * @param node
	 *            DOM�ڵ�
	 * @param url
	 *            XML�ļ�·��
	 */
	public void writeFile(Node node, String url) {
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		DOMSource domSource = new DOMSource(node);
		StreamResult streamResult = new StreamResult(new File(url));
		try {
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(domSource, streamResult);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Document bulid() {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder dombuilder = null;
		try {
			dombuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Document document = dombuilder.newDocument();
		Element books = document.createElement("book:books");
		books.setAttribute("xmlns:book", "http://www.mingrisoft.com");

		Element book = document.createElement("book:book");
		Element name = document.createElement("book:name");
		Element publisher = document.createElement("book:publisher");
		Element company = document.createElement("book:company");
		Element author = document.createElement("book:author");
		Element isbn = document.createElement("book:ISBN");
		Element price = document.createElement("book:price");
		Element url = document.createElement("book:url");

		name.setTextContent("��C#�����ŵ���ͨ(��2��)��");
		publisher.setTextContent("�廪��ѧ������");
		company.setTextContent("���տƼ�");
		author.setTextContent("��С��");
		isbn.setTextContent("9787302226628");
		price.setTextContent("69.80");
		url.setTextContent("http://www.mingribook.com/bookinfo.php?id=227");


		book.appendChild(name);
		book.appendChild(publisher);
		book.appendChild(company);
		book.appendChild(author);
		book.appendChild(isbn);
		book.appendChild(price);
		book.appendChild(url);
		books.appendChild(book);
		document.appendChild(books);

		return document;
	}

	public static void main(String[] arg) {

		String pathname = "xmldemo/ElementDOMBulid.xml";
		ElementDOMBulid bulid = new ElementDOMBulid();
		bulid.writeFile(bulid.bulid(), pathname);

	}

}
