import java.io.*;
import java.util.Scanner;
import javax.servlet.*;
import javax.servlet.http.*;
import java.time.LocalDateTime;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/MainPage"})
public class MainPage extends HttpServlet {

	LocalDateTime now = LocalDateTime.now();
	static MainPage.Seat seats[][] = new MainPage.Seat[8][8];
	boolean seatsLoaded = false;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		if (!seatsLoaded){
			loadSeats();
			seatsLoaded = true;
		}

		out.println(HtmlGen.doctype());
		out.println(HtmlGen.title("Seat Layout"));
		out.println(HtmlGen.h1("Welcome to Theodore's Theater"));
		out.println(HtmlGen.table(makeTable()));
		out.println(HtmlGen.p(dateTime()));
		out.println("</html>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.println(HtmlGen.doctype());
		out.println(HtmlGen.title("Seat Layout"));
		
		int lastSeat = -1;
		String lastForm = null;
		lastSeat = Integer.parseInt(request.getParameter("lastSeat"));
		lastForm = request.getParameter("lastForm");
		switch(lastForm){
			case "booking":
				String userID = request.getParameter("UserID");
				String phone = request.getParameter("Phone");
				String address = request.getParameter("Address");
				String email = request.getParameter("Email");
				seats[lastSeat/10][lastSeat%10].book(userID, phone, address, email);
				request.setAttribute("lastForm", "");
				request.setAttribute("laseSeat", "");
				break;
			case "cancellation":
				seats[lastSeat/10][lastSeat%10].unbook();
				request.setAttribute("lastForm", ""); 
				request.setAttribute("laseSeat", "");
				break;
			default:
				request.setAttribute("lastForm", ""); 
				request.setAttribute("laseSeat", "");
				break;
		}
		out.println(HtmlGen.h1("Welcome to Theodore's Theater"));
		out.println(HtmlGen.table(makeTable()));
		saveSeats();
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
		
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				if (i != 0 && j != 0){
					
					String url, classname;
					
					if (seats[j-1][i-1] == null){
						seats[j-1][i-1] = new MainPage.Seat(letters[j], numbers[i]);
					}
					
					if (seats[j-1][i-1].isBooked()){
						url = "CancellationPage";
						classname = "booked";
					}

					else {
						url = "BookingPage";
						classname = "free";
					}
					
					String link = url + "?SeatIndex=" + (j-1) + "" + (i-1);
					String text = letters[j] + numbers[i];
					out[j][i] = HtmlGen.div(classname, HtmlGen.link(link, text));
				}
				else {
					out[j][i] = letters[j] + numbers[i];
				}
			}
		}
		return out;
	}

	private void saveSeats(){
		try{
			PrintWriter out = new PrintWriter("Seatdata.txt", "UTF-8");
			for (int i = 0; i < seats.length; i++){
				for (int j = 0; j < seats[i].length; j++){

					out.println(""+	seats[i][j].getLetter()+" "+
									seats[i][j].getNumber()+" "+
									seats[i][j].isBooked()+" "+
									seats[i][j].getUserID()+" "+
									seats[i][j].getPhone()+" "+
									seats[i][j].getAddress()+" "+
									seats[i][j].getEmail());
				}
			}
			out.close();
		}
		catch(Exception e){}	
	}

	private boolean loadSeats(){
		try{
			Scanner file = new Scanner(new File("Seatdata.txt"));
			for (int i = 0; i < seats.length; i++){
				for (int j = 0; j < seats[i].length; j++){
					String line = file.nextLine();
					String[] parameters = line.split(" ");
					seats[i][j] = new MainPage.Seat(parameters[0], parameters[1]);
					if (Boolean.parseBoolean(parameters[2])){
						seats[i][j].book(parameters[3], parameters[4], parameters[5], parameters[6]);
					}
				}
			}
		}
		catch(Exception e){
			return false;
		}
		return true;
	}

	public class Seat{

		String letter, number;
		String userID, phone, address, email;
		boolean booked = false;

		private Seat(String letter, String number){
			this.letter = letter;
			this.number = number;
		}

		private void book(String userID, String phone, String address, String email){
			this.userID = userID;
			this.phone = phone;
			this.address = address;
			this.email = email;
			booked = true;
		}

		public void unbook(){
			this.userID = null;
			this.phone = null;
			this.address = null;
			this.email = null;
			booked = false;
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
