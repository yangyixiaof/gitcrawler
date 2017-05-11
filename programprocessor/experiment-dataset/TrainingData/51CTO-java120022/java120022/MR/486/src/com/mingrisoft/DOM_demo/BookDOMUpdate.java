package com.mingrisoft.DOM_demo;

import java.io.File;
import java.io.IOException;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author bwm
 * 
 */
public class BookDOMUpdate {

	private Document document = null;

	private String path = null;

	public BookDOMUpdate(String path) {
		// TODO Auto-generated constructor stub
		this.path = path;
	}

	/**
	 * ��ȡXML�ļ�
	 * 
	 * @param path
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private void parseReadFile() {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder dombuilder = null;
		try {
			dombuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file = new File(path);
		try {
			document = dombuilder.parse(file);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ����book�ӽڵ�ָ������������Ԫ�ص�����
	 * 
	 * @param nodeName
	 *            ָ������Ԫ������
	 * @param text
	 *            ָ������Ԫ������
	 * @param replNodeName
	 *            ָ�����µĽڵ�
	 * @param repltext
	 *            ���ø��µ�����
	 */
	public void updateBook(String nodeName, String text, String replNodeName,
			String repltext) {
		parseReadFile();

		NodeList list = document.getElementsByTagName("book:book");
		for (int i = 0; i < list.getLength(); i++) {
			boolean flag = false;
			Node bookNode = list.item(i);
			NodeList list1 = bookNode.getChildNodes();
			// ��ѯ����Ҫ�޸ĵ�book�ڵ�
			for (int j = 0; j < list1.getLength(); j++) {
				Node node1 = list1.item(j);
				if (node1.getNodeName().equals(nodeName)
						&& node1.getTextContent().equals(text)) {
					flag = true;
				}
			}
			// ��ѯ����Ҫ��book�ڵ㣬����ָ����Ԫ������
			if (flag) {
				for (int j = 0; j < list1.getLength(); j++) {
					Node node1 = list1.item(j);
					// ��ѯ����Ҫ�޸ĵĽڵ�
					if (node1.getNodeName().equals(replNodeName)) {
						node1.setTextContent(repltext);
					}
				}
				flag = false;
			}

		}

	}

	/**
	 * ����XML�ļ�
	 * 
	 * @param node
	 *            DOM�ڵ�
	 * @param url
	 *            XML�ļ�·��
	 */
	public void writeFile(String out) {
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		DOMSource domSource = new DOMSource(document);
		StreamResult streamResult = new StreamResult(new File(out));
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

	public static void main(String[] arg) {
		String in = "xmldemo/BookDOMBulid.xml";
		String out = "xmldemo/BookDOMUpdate.xml";
		BookDOMUpdate bulid = new BookDOMUpdate(in);
		bulid.updateBook("book:author", "��С��", "book:name", "��C#�����ŵ���ͨ(��1��)��");
		bulid.writeFile(out);
	}
}
