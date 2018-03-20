import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.time.LocalDateTime;
import javax.servlet.annotation.WebServlet;
import java.util.Random;

@WebServlet(urlPatterns = {"/CancellationPage"})
public class CancellationPage extends HttpServlet {

	Random random = new Random();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int seatIndex = Integer.parseInt(request.getParameter("SeatIndex"));
		MainPage.Seat seat = MainPage.seats[seatIndex/10][seatIndex%10];
		String letter = "" + (seat.toString().charAt(0));
		String number = "" + (seat.toString().charAt(1));
		String securityCode = HtmlGen.getSecurityCode();

		out.println(HtmlGen.doctype());
		out.println(HtmlGen.title("Cancellation Form"));
		out.println(HtmlGen.h1("Cancellation Form or seat: " + letter + number));
		out.println(HtmlGen.p("Your security code is: " + securityCode));
		out.println(HtmlGen.form("Display form", "get", "return true"));
		out.println(HtmlGen.lable("UserID", "UserID"));
		out.println(HtmlGen.input("text", "UserID")+"<br>");
		switch (random.nextInt(2)){
			case 0:
				out.println(HtmlGen.lable("Phone", "Phone"));
				out.println(HtmlGen.input("text", "Phone")+"<br>");
				break;
			case 1:
				out.println(HtmlGen.lable("Address", "Address"));
				out.println(HtmlGen.input("text", "Address")+"<br>");
				break;
			case 2:
				out.println(HtmlGen.lable("Email", "Email"));
				out.println(HtmlGen.input("text", "Email")+"<br>");
				break;
			default:
				break;
		}

		out.println(HtmlGen.lable("Security code", "Security code"));
		out.println(HtmlGen.input("text", "Security code")+"<br>");
		out.println(HtmlGen.input("clear", "Clear"));
		out.println(HtmlGen.input("submit", "Submit"));
		// 
		out.println("</html>");
	}
}	