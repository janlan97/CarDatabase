package servlets;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import parking.entities.Address;
import parking.entities.Client;
import parking.service.implementation.Service;
import parking.service.interfaces.IService;
import parking.utils.ServiceHelper;

@WebServlet("/AddNewClientServlet")
public class AddNewClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddNewClientServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("addClient.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IService service = Service.getInstance();
		Address a = new Address();
		a.setCity(request.getParameter("city"));
		a.setStreet(request.getParameter("street"));
		try {
			a.setBuildingNumber(Integer.valueOf(request.getParameter("bnum")));
			a.setApartmentNumber(Integer.valueOf(request.getParameter("hnum")));
		} catch (NumberFormatException e) {
			ServiceHelper.throwError(request, response, e.getClass().toString(),
					"Numer budynku/mieszkania nie powinien zawierac zadnych liter");
			return;
		}
		Client c = new Client();
		c.setName(request.getParameter("name"));
		c.setLastName(request.getParameter("lastName"));
		try {
			new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("born"));
			c.setBorn(request.getParameter("born"));
		} catch (java.text.ParseException e) {
			System.out.println(request.getParameter("born"));
			ServiceHelper.throwError(request, response, e.getClass().toString(), "Nieprawidlowy format daty.");
			return;
		}
		c.setAddress(a);
		service.addEntity(c, null);
		ServiceHelper.successfullRequest(request, response, "Dodano klienta do bazy danych");
	}
}