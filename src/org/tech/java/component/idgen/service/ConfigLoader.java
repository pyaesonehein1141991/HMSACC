package org.tech.java.component.idgen.service;

import java.io.CharArrayWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.tech.hms.process.interfaces.IUserProcessService;
import org.tech.java.component.idgen.service.interfaces.IDConfigLoader;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

@Service("IDConfigLoader")
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class ConfigLoader extends DefaultHandler implements IDConfigLoader {

	@javax.annotation.Resource(name = "IDConfigLoader")
	private IDConfigLoader idConfigLoader;
	
	@javax.annotation.Resource(name = "UserProcessService")
	private IUserProcessService userProcessService;
	
	private CharArrayWriter text = new CharArrayWriter();
	private Map<String, String> idConfigMap = new HashMap<String, String>();
	private boolean centralized;

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("class")) {
			idConfigMap.put(attributes.getValue("name"), attributes.getValue("prefix"));
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {

	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		text.write(ch, start, length);
	}

	@PostConstruct
	public void load() {
		try {
			Resource configFile = new ClassPathResource("id-format.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(configFile.getFile(), this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getFormat(String className) {
		return idConfigMap.get(className);
	}



	public boolean isCentralizedSystem() {
		return centralized;
	}
}
