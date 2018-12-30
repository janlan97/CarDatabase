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

@WebServlet("/DeleteCarServlet")
public class DeleteCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteCarServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("deleteCar.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IService service = Service.getInstance();
		try {
			service.deleteEntity(Vehicle.class, Integer.valueOf(request.getParameter("carId")));
		} catch (EntityNotFoundException e) {
			ServiceHelper.throwError(request, response, e.getClass().toString(), "Brak samochodu o podanym ID.");
			return;
		}
		ServiceHelper.successfullRequest(request, response, "Usunieto samochod z bazy danych");
	}
}