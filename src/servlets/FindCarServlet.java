package servlets;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import parking.entities.Vehicle;
import parking.service.implementation.Service;
import parking.service.interfaces.IService;
import parking.utils.ServiceHelper;

@WebServlet("/FindCarServlet")
public class FindCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FindCarServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("findCar.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IService service = Service.getInstance();
		String paramString = "";
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String key = params.nextElement();
			String val = request.getParameter(key);
			if (!val.isEmpty()) {
				paramString += key + "=\"" + val + "\" AND ";
			}
		}
		if (!paramString.isEmpty()) {
			paramString = "WHERE " + paramString.substring(0, paramString.length() - 4);
		}
		
		List<Vehicle> foundVehicles = ((Service) service).findVehicle(paramString);
		
		if(foundVehicles.size() > 0) {
			ServiceHelper.successfullRequest(request, response, ServiceHelper.generateHTMLFromVehicleQueryResult(foundVehicles));
		} else {
			ServiceHelper.throwError(request, response, "Brak wynikow", "W bazie danych nie odnaleziono rekordow odpowiadajacych twoim kryteriom");
		}
	}
}