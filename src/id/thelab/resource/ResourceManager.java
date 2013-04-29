package id.thelab.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.newdawn.slick.SlickException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ResourceManager {
	private final static ResourceManager _instance = new ResourceManager();
	private ResourceLoader resourceLoader = new ResourceLoader();
	private Map<String, Resource> resources;

	private ResourceManager() {
		resources = new HashMap<String, Resource>();
	}

	public static final ResourceManager getInstance() {
		return _instance;
	}

	public void loadResources(final InputStream is) throws SlickException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new SlickException("Could not create new document builder.");
		}
		Document doc = null;
		try {
			doc = docBuilder.parse(is);
		} catch (SAXException e) {
			throw new SlickException("Could not parse input stream.");
		} catch (IOException e) {
			throw new SlickException("Could not read file.");
		}

		System.out.println("Resources opened successfully.");

		doc.getDocumentElement().normalize();

		NodeList resourcesList = doc.getElementsByTagName("resource");
		int totalResources = resourcesList.getLength();
		System.out.println("Total resources: " + totalResources);

		for (int resourceId = 0; resourceId < totalResources; resourceId++) {
			Node resourceNode = resourcesList.item(resourceId);

			if (resourceNode.getNodeType() == Node.ELEMENT_NODE) {
				Element resourceElement = (Element) resourceNode;

				String id = resourceElement.getAttribute("id");
				// NamedNodeMap attrs = resourceElement.getAttributes();
				Resource res = null;
				try {
					res = resourceLoader.loadResource(resourceElement);
				} catch (SlickException e) {
					e.printStackTrace();
					System.out.println("Fail to load resource " + "id");
				}
				resources.put(id, res);
			}
		}
		System.out.println("All resources loaded completely");

	}

	public Resource getResource(final String id) throws RuntimeException {
		if (!resources.containsKey(id)) {
			throw new RuntimeException("Resource manager not contain " + id);
		}
		return resources.get(id);
	}
}
