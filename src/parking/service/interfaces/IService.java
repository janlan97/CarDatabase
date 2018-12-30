package parking.service.interfaces;

public interface IService {

	public Object findById(Class<?> cls, Integer id);

	public boolean deleteEntity(Class<?> cls, Integer id);

	public Integer addEntity(Object o, Integer id);
}