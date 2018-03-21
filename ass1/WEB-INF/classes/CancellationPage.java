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
		String letter = "" + (seat.getLetter());
		String number = "" + (seat.getNumber());
		String securityCode = HtmlGen.getSecurityCode();

		out.println(HtmlGen.doctype());
		out.println(HtmlGen.title("Cancellation Form"));
		out.println(HtmlGen.script("js/validateCancellation.js"));
		out.println(HtmlGen.h1("Cancellation Form or seat: " + letter + number));
		out.println(HtmlGen.p("Your security code is: " + securityCode));
		int verificationField = random.nextInt(2);
		String fieldName = "";
		String seatFieldName = "";
		
		switch (verificationField){
			case 0:
				fieldName = "Phone";
				seatFieldName = seat.getPhone();
				break;
			case 1:
				fieldName = "Address";
				seatFieldName = seat.getAddress();
				break;
			case 2:
				fieldName = "Email";
				seatFieldName = seat.getEmail();
				break;
			default:
				break;
		}

		out.println(HtmlGen.form("Cancellation", "MainPage", "post", "return validateCancellation(\'" + securityCode + "\', \'"+ seat.getUserID() + "\', \'" + seatFieldName + "\');"));
		out.println(HtmlGen.label("UserID", "UserID"));
		out.println(HtmlGen.input("text", "UserID")+"<br>");
		switch (verificationField){
			case 0:
				out.println(HtmlGen.label("Phone", "Phone"));
				out.println(HtmlGen.input("text", "varificationField")+"<br>");
				break;
			case 1:
				out.println(HtmlGen.label("Address", "Address"));
				out.println(HtmlGen.input("text", "verificationField")+"<br>");
				break;
			case 2:
				out.println(HtmlGen.label("Email", "Email"));
				out.println(HtmlGen.input("text", "varificationField")+"<br>");
				break;
			default:
				break;
		}

		out.println(HtmlGen.label("Security code", "Security code"));
		out.println(HtmlGen.input("text", "SecurityCode")+"<br>");
		out.println(HtmlGen.hiddeninput("lastForm", "cancellation"));
		out.println(HtmlGen.hiddeninput("lastSeat", "" + seatIndex));
		out.println(HtmlGen.input("reset", "Clear"));
		out.println(HtmlGen.input("submit", "Submit"));
		// 
		out.println("</html>");
	}
}	