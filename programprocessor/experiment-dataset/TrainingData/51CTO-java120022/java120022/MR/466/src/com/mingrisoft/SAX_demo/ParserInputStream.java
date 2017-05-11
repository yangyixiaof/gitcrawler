/**
 * @jdk�汾:1.6
 * @����ʱ��:2010-3-20
 */
package com.mingrisoft.SAX_demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author bwm
 * 
 */
public class ParserInputStream {

	/**
	 * ����������ȡXML
	 * 
	 * @param inputStream
	 *            ������
	 */
	public void parseInputStream(InputStream inputStream) {
		SAXParser parser;
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			parser = factory.newSAXParser();
			parser.parse(inputStream, new DefaultHandler());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] arg) {

		String pathname = "xmldemo/books.xml";
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(pathname));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		new ParserInputStream().parseInputStream(inputStream);

		try {
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
