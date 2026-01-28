package api;
//This method belongs inside your UserXMLRepository class, because that class is responsible for:

//reading XML,  parsing elements, constructing User objects
//controller and service should never touch XML directly — the repository handles that.

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

	private final String filePath;
	private Document document;

	public UserRepository(String filePath) {
		this.filePath = filePath;
		loadOrCreateDocument();
	}

	private void loadOrCreateDocument() {
		try {
			File file = new File(filePath);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			if (file.exists()) {
				document = builder.parse(file);
			} else {
				document = builder.newDocument();
				Element root = document.createElement("users");
				document.appendChild(root);
				saveDocument();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveDocument() {
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(document), new StreamResult(new File(filePath)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		NodeList list = document.getDocumentElement().getElementsByTagName("user");

		for (int i = 0; i < list.getLength(); i++) {
			Element el = (Element) list.item(i);
			users.add(parseUser(el));
		}
		return users;
	}

	public User findById(long id) {
		NodeList list = document.getDocumentElement().getElementsByTagName("user");

		for (int i = 0; i < list.getLength(); i++) {
			Element el = (Element) list.item(i);
			if (Long.parseLong(el.getAttribute("id")) == id) {
				return parseUser(el);
			}
		}
		return null;
	}

	public void save(User user) {
		Element root = document.getDocumentElement();

		Element userEl = document.createElement("user");
		userEl.setAttribute("id", String.valueOf(user.getId()));

		appendElement(userEl, "firstName", user.getFirstName());
		appendElement(userEl, "lastName", user.getLastName());
		appendElement(userEl, "city", user.getCity());
		appendElement(userEl, "phone", user.getPhone());
		appendElement(userEl, "ageGroup", user.getAgeGroup());
		appendElement(userEl, "status", user.getStatus().name());// Add name for enum

		root.appendChild(userEl);
		saveDocument();
	}

	public boolean deleteById(long id) {
		NodeList list = document.getDocumentElement().getElementsByTagName("user");

		for (int i = 0; i < list.getLength(); i++) {
			Element el = (Element) list.item(i);
			if (Long.parseLong(el.getAttribute("id")) == id) {
				document.getDocumentElement().removeChild(el);
				saveDocument();
				return true;
			}
		}
		return false;
	}

	private User parseUser(Element el) {
		String id = el.getElementsByTagName("Id").item(0).getTextContent();// ????????
		String firstName = el.getElementsByTagName("firstName").item(0).getTextContent();
		String lastName = el.getElementsByTagName("lastName").item(0).getTextContent();
		String city = el.getElementsByTagName("city").item(0).getTextContent();
		String phone = el.getElementsByTagName("phone").item(0).getTextContent();
		String ageGroup = el.getElementsByTagName("ageGroup").item(0).getTextContent();
		// THIS is where you convert XML text → Status enum
		Status status = Status.valueOf(el.getElementsByTagName("status").item(0).getTextContent());

		return new User(id, firstName, lastName, city, phone, ageGroup, status);
	}

	private void appendElement(Element parent, String tag, String value) {
		Element el = document.createElement(tag);
		el.setTextContent(value);
		parent.appendChild(el);
	}
}
