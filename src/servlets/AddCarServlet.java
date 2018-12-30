package servlets;

import java.io.IOException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import parking.entities.Vehicle;
import parking.service.implementation.Service;
import parking.service.interfaces.IService;
import parking.utils.ServiceHelper;

@WebServlet("/AddCarServlet")
public class AddCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddCarServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("addCar.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IService service = Service.getInstance();
		Vehicle vehicle = new Vehicle();
		vehicle.setManufacturer((String) request.getParameter("producent"));
		vehicle.setModel(request.getParameter("model"));
		try {
			vehicle.setProductionYear(Integer.valueOf(request.getParameter("productionYear")));
		} catch (NumberFormatException e) {
			ServiceHelper.throwError(request, response, e.getClass().toString(),
					"Wprowadzone dane (" + request.getParameter("productionYear") + ") nie sa formatu liczbowego.");
			return;
		}
		try {
			Integer id = service.addEntity(vehicle, Integer.valueOf((String) request.getParameter("clientId")));
			if (id == null) {
				ServiceHelper.throwError(request, response, new EntityNotFoundException().getClass().toString(),
						"W bazie danych nie ma klienta o podanym id. (" + request.getParameter("clientId") + ")");
				return;
			} else {
				ServiceHelper.successfullRequest(request, response, "Dodano nowy samochod do bazy danych");
				return;
			}
		} catch (NumberFormatException e) {
			ServiceHelper.throwError(request, response, e.getClass().toString(),
					"Wprowadzone dane (" + request.getParameter("clientId") + ") nie sa formatu liczbowego.");
			return;
		}
	}
}