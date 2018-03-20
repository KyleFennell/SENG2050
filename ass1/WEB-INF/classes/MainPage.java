import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.time.LocalDateTime;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/MainPage"})
public class MainPage extends HttpServlet {

	LocalDateTime now = LocalDateTime.now();
	static MainPage.Seat seats[][];

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.println(HtmlGen.doctype());
		out.println(HtmlGen.title("Seat Layout"));
		out.println(HtmlGen.h1("Welcome to Theodore's Theater"));
		out.println(HtmlGen.table(makeTable()));
		out.println(HtmlGen.p(dateTime()));
		out.println("</html>");
	}

	private String dateTime(){
		return now.getDayOfMonth() + "-" + now.getMonthValue() + "-" + (now.getYear()/100) + " " +
		  now.getSecond() + "-" + now.getMinute() + "-" + now.getHour();
	}

	private String[][] makeTable(){
		String letters[] = {"", "A", "B", "C", "D", "E", "F", "G", "H"};
		String numbers[] = {"", "1", "2", "3", "4", "5", "6", "7", "8"};
		String out[][] = new String[9][9];
		seats = new MainPage.Seat[8][8];
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				if (i != 0 && j != 0){
					
					String url;
					
					if (seats[j-1][i-1] == null){
						seats[j-1][i-1] = new MainPage.Seat(letters[j], numbers[i]);
					}
					
					if (seats[j-1][i-1].isBooked()){
						url = "CancellationPage";
					}
					else {
						url = "BookingPage";
					}
					
					String link = url + "?SeatIndex=" + (j*10+i);
					String text = letters[j] + numbers[i];
					out[j][i] = HtmlGen.link(link, text);
				}
				else {
					out[j][i] = letters[j] + numbers[i];
				}
			}
		}
		return out;
	}

	class Seat{

		String letter, number;
		String userID, phone, address, email;
		boolean booked = false;

		private Seat(String letter, String number){
			this.letter = letter;
			this.number = number;
		}

		private boolean book(String userID, String phone, String address, String email){
			this.userID = userID;
			this.phone = phone;
			this.address = address;
			this.email = email;
			booked = true;
			return true;
		}

		public String getLetter(){
			return letter;
		}

		public String getNumber(){
			return number;
		}

		public String getUserID(){
			return userID;
		} 

		public String getPhone(){
			return phone;
		} 

		public String getAddress(){
			return address;
		} 

		public String getEmail(){
			return email;
		}

		public boolean isBooked(){
			return booked;
		}

		public String toString(){
			return "Seat: " + this.letter + this.number + "\n has been booked by :" + this.userID;
		}

	}

}	
