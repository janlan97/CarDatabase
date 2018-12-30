package parking.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import parking.entities.Address;
import parking.entities.Client;
import parking.entities.Vehicle;

public class DataHandler {

	public static void addRecordsToDatabase(String pathToData) {
		List<Client> clientsToAdd = createClients(pathToData);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();

		for (Client c : clientsToAdd) {
			s.persist(c.getAddress());
			s.persist(c);
			for (Vehicle v : c.getVehicles()) {
				s.persist(v);
			}
		}

		s.getTransaction().commit();
		s.close();
	}

	private static List<Client> createClients(String pathToData) {
		List<Client> createdClients = new ArrayList<>();
		JSONArray clients = null;
		try {
			clients = (JSONArray) new JSONParser().parse(new FileReader(new File(pathToData)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < clients.size(); i++) {
			JSONObject fetchedClientData = (JSONObject) clients.get(i);
			Client client = fetchClient(fetchedClientData);
			client.setAddress(fetchAddress(fetchedClientData));
			Set<Vehicle> vehicles = new HashSet<>();
			JSONArray vehiclesJSON = (JSONArray) fetchedClientData.get("cars");
			for (int j = 0; j < vehiclesJSON.size(); j++) {
				Vehicle v = fetchVehicle((JSONObject) vehiclesJSON.get(j));
				v.setClient(client);
				vehicles.add(v);
			}
			client.setVehicles(vehicles);
			createdClients.add(client);
		}
		return createdClients;
	}

	private static Address fetchAddress(JSONObject fetchedClientData) {
		Address address = new Address();
		JSONObject addressJSON = (JSONObject) fetchedClientData.get("address");
		address.setCity((String) addressJSON.get("city"));
		address.setStreet((String) addressJSON.get("street"));
		address.setBuildingNumber((Integer) ((Long) addressJSON.get("buildingNum")).intValue());
		address.setApartmentNumber((Integer) ((Long) addressJSON.get("apartmentNum")).intValue());
		return address;
	}

	private static Vehicle fetchVehicle(JSONObject fetchedVehicleData) {
		Vehicle vehicle = new Vehicle();
		vehicle.setManufacturer((String) fetchedVehicleData.get("producer"));
		vehicle.setModel((String) fetchedVehicleData.get("model"));
		vehicle.setProductionYear((Integer) ((Long) fetchedVehicleData.get("year")).intValue());
		return vehicle;
	}

	private static Client fetchClient(JSONObject fetchedClientData) {
		Client client = new Client();
		client.setName((String) fetchedClientData.get("name"));
		client.setLastName((String) fetchedClientData.get("secondName"));
		client.setBorn((String) fetchedClientData.get("born"));
		return client;
	}
}