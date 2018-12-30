package parking.service.implementation;

import parking.entities.Address;
import parking.entities.Client;
import parking.entities.Vehicle;
import parking.service.interfaces.IService;

public class Main2 {

	public static void main(String[] args) {

		// DataHandler.addRecordsToDatabase("C:\\Users\\jan\\Desktop\\jsondata.txt");
		// ParkingService parkingService = ParkingService.getInstance();
		// parkingService.setSessionFactory(HibernateUtil.getSessionFactory());

		// sf = HibernateUtil.getSessionFactory();
		// Session s = sf.getCurrentSession();
		IService service = Service.getInstance();
		
		 Address a = new Address();
		 a.setCity("City");
		 a.setStreet("Street");
		 a.setBuildingNumber(1);
		 a.setApartmentNumber(2);

		Vehicle v = new Vehicle();
		v.setManufacturer("MAZDA");
		v.setModel("RX8");
		v.setProductionYear(2011);

		 Client c = new Client();
		
		 c.setName("Test");
		 c.setLastName("User");
		 c.setBorn("04/10/1997");
		 c.setAddress(a);
		 c.getVehicles().add(v);
		 
		 service.addEntity(c, 1);

	}

}
