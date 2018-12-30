package parking.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import parking.entities.Address;
import parking.entities.Client;
import parking.entities.Vehicle;

public class ServiceHelper {
	private Session session;

	public ServiceHelper(Session session) {
		this.session = session;
	}

	public Object findById(Class<?> type, Integer id) {
		session.beginTransaction();
		Object persistenceInstance = session.load(type, id);
		session.getTransaction().commit();
		if (persistenceInstance != null) {
			return persistenceInstance;
		}
		return null;
	}

	public boolean deleteById(Class<?> type, Integer id) {
		session.beginTransaction();
		Object persistenceInstance = session.load(type, id);
		session.getTransaction().commit();
		if (persistenceInstance != null) {
			session.beginTransaction();
			session.remove(persistenceInstance);
			session.getTransaction().commit();
			return true;
		}
		return false;
	}

	public Integer handleInsertVehilce(Vehicle vehicle, Integer clientId) {
		session.beginTransaction();
		Client foundClient = session.find(Client.class, clientId);
		session.getTransaction().commit();
		if (Objects.isNull(foundClient)) {
			return null;
		} else {
			return insertVehicle(vehicle, foundClient);
		}
	}

	private Integer insertVehicle(Vehicle vehicle, Client foundClient) {
		Integer vehicleId = null;
		foundClient.getVehicles().add(vehicle);
		vehicle.setClient(foundClient);
		session.beginTransaction();
		session.saveOrUpdate(foundClient);
		vehicleId = (Integer) session.save(vehicle);
		session.getTransaction().commit();
		session.close();
		return vehicleId;
	}

	public Integer insertClient(Client client) {
		client = prepareClientToInsert(client);
		Integer clientId = null;
		session.beginTransaction();
		session.saveOrUpdate(client.getAddress());
		clientId = (Integer) session.save(client);
		for (Vehicle vehicle : client.getVehicles()) {
			vehicle.setClient(client);
			session.save(vehicle);
		}
		session.getTransaction().commit();
		session.close();
		return clientId;
	}

	private Client prepareClientToInsert(Client client) {
		Address address = client.getAddress();
		List<Address> addresses = findAddress(address);
		if (addresses.isEmpty()) {
			address.getClients().add(client);
		} else {
			address = addresses.get(0);
		}
		client.setAddress(address);
		return client;
	}

	private List<Address> findAddress(Address address) {
		session.beginTransaction();
		Query query = session.createQuery(
				"FROM parking.entities.Address a WHERE a.city = :city AND a.apartmentNumber = :anum AND a.buildingNumber = :bnum AND a.street = :street");
		query.setParameter("anum", address.getApartmentNumber());
		query.setParameter("bnum", address.getBuildingNumber());
		query.setParameter("city", address.getCity());
		query.setParameter("street", address.getStreet());
		List<Address> list = query.getResultList();
		session.getTransaction().commit();
		return list;
	}

	public List<Vehicle> findVehicle(String parameters) {
		session.beginTransaction();
		NativeQuery<Vehicle> query = session.createNativeQuery("SELECT * FROM VEHICLE " + parameters, Vehicle.class);
		session.getTransaction().commit();
		return query.list();
	}

	public static String generateHTMLFromVehicleQueryResult(List<Vehicle> foundVehicles) {
		String res = "";
		for (Vehicle v : foundVehicles) {
			res += "<div class=\"carInfo\">" + "<b>id samochodu: </b>" + v.getId() + "<p>" + "<b>producent: </b>"
					+ v.getManufacturer() + "<p><b>model: </b>" + v.getModel() + "<p>" + "<b>rok produkcji: </b>"
					+ v.getProductionYear() + "<p>" + "</div>\n";
		}
		return res;
	}

	public static void throwError(HttpServletRequest request, HttpServletResponse response, String errorType,
			String errorMsg) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("<html><head>" + "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/main.css\">"
				+ "<title>Blad!</title></head><body>");
		out.write("<h1>" + errorType + "</h1>" + "<div id=\"options\">" + errorMsg + "</div>");
		out.write(
				"<footer><form action=\"http://localhost:8080/1111CarStorage/\"> <input type=\"submit\" value=\"Powrot\" /></footer>");
		out.write("</body></html>");
		return;
	}

	public static void successfullRequest(HttpServletRequest request, HttpServletResponse response, String msg)
			throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("<html><head>" + "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/main.css\">"
				+ "<title>Sukces!</title></head><body>");
		out.write("<h1>" + "SUKCES" + "</h1>" + "<div id=\"options\">" + msg + "</div>");
		out.write(
				"<footer><form action=\"http://localhost:8080/1111CarStorage/\"> <input type=\"submit\" value=\"Powrot\" /></footer>");
		out.write("</body></html>");
	}
}