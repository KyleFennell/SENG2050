import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.time.LocalDateTime;
import javax.servlet.annotation.WebServlet;
import java.util.Random;

@WebServlet(urlPatterns = {"/BookingPage"})
public class BookingPage extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int seatIndex = Integer.parseInt(request.getParameter("SeatIndex"));
		MainPage.Seat seat = MainPage.seats[seatIndex/10][seatIndex%10];
		String letter = "" + (seat.getLetter());
		String number = "" + (seat.getNumber());
		String securityCode = HtmlGen.getSecurityCode();
		
		out.println(HtmlGen.doctype());
		out.println(HtmlGen.title("Booking Form"));
		out.println(HtmlGen.h1("Booking Form or seat: " + letter + number));
		out.println(HtmlGen.p("Your security code is: " + securityCode));
		out.println(HtmlGen.form("Display form", "get", "return true"));
		out.println(HtmlGen.lable("UserID", "UserID"));
		out.println(HtmlGen.input("text", "UserID")+"<br>");
		out.println(HtmlGen.lable("Phone", "Phone"));
		out.println(HtmlGen.input("text", "Phone")+"<br>");
		out.println(HtmlGen.lable("Address", "Address"));
		out.println(HtmlGen.input("text", "Address")+"<br>");
		out.println(HtmlGen.lable("Email", "Email"));
		out.println(HtmlGen.input("text", "Email")+"<br>");
		out.println(HtmlGen.lable("Security code", "Security code"));
		out.println(HtmlGen.input("text", "Security code")+"<br>");
		out.println(HtmlGen.input("clear", "Clear"));
		out.println(HtmlGen.input("submit", "Submit"));
		// 
		out.println("</html>");
	}
}	