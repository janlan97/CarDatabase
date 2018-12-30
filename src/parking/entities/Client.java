package parking.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "CLIENT")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CLIENT_ID", nullable = false, updatable = false)
	private int id;
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "SURNAME", nullable = false)
	private String lastName;
	@Column(name = "BORN", nullable = false)
	@Type(type = "date")
	private Date born;
	@ManyToOne
	@JoinColumn(name = "ADDRESS", nullable = false)
	private Address address;
	@OneToMany(cascade = CascadeType.REMOVE)
	private Set<Vehicle> vehicles = new HashSet<>();

	public Date getBorn() {
		return born;
	}

	public void setBorn(String born) {
		try {
			this.born = new SimpleDateFormat("yyyy-mm-dd").parse(born);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", lastName=" + lastName + ", born=" + born + ", address="
				+ address + ", vehicles=" + vehicles + "]";
	}
}