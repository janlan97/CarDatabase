package parking.service.implementation;

import java.util.List;
import parking.entities.Client;
import parking.entities.Vehicle;
import parking.service.interfaces.IService;
import parking.utils.ServiceHelper;
import parking.utils.SessionHandler;

public class Service implements IService {
	private static Service instance = null;
	private SessionHandler sessionHandler;

	private Service() {
		sessionHandler = SessionHandler.getInstance();
	}

	public static Service getInstance() {
		return instance == null ? new Service() : instance;
	}

	@Override
	public Object findById(Class<?> cls, Integer id) {
		return new ServiceHelper(sessionHandler.handle("open")).findById(cls, id);
	}

	@Override
	public boolean deleteEntity(Class<?> cls, Integer id) {
		return new ServiceHelper(sessionHandler.handle("open")).deleteById(cls, id);
	}

	@Override
	public Integer addEntity(Object obj, Integer id) {
		if (obj instanceof Client) {
			return new ServiceHelper(sessionHandler.handle("open")).insertClient((Client) obj);
		} else if (obj instanceof Vehicle && id != null) {
			return new ServiceHelper(sessionHandler.handle("open")).handleInsertVehilce((Vehicle) obj, id);
		}
		return null;
	}

	public List<Vehicle> findVehicle(String parameters) {
		return new ServiceHelper(sessionHandler.handle("open")).findVehicle(parameters);
	}
}