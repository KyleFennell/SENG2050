import java.util.Random;

public class HtmlGen {

	private static Random random = new Random();
	
	public static String doctype(){
		return"<!DOCTYPE html>\n<html lang=\"en\">";
	}

	public static String title(String title){
		return"<head><title>" + title + "</title><link rel=\"stylesheet\" href=\"css/styles.css\"></head>";
	}

	static String h1(String headding){
		return "<h1>" + headding + "</h1>";
	}

	static String h2(String headding){
		return "<h2>" + headding + "</h2>";
	}
	
	static String p(String paragraph){
		return "<p>" + paragraph + "</p>";
	}

	static String form(String name, String action, String method, String onsubmit){
		return "<form name=\"" + name + "\" action=\"" + action + "\" method=\"" + method + "\" onsubmit=\"" + onsubmit +"\">";
	}

	static String label(String name, String text){
		return "<label for=\"" + name + "\">" + text + "</label>";
	}

	static String input(String type, String value){
		String out = "<input type=\"" + type + "\" ";
		if (type == "text"){
			return out + "name=\"" + value + "\" id=\"" + value + "\"/>";
		}	
		else {
			return out + "value=\"" + value + "\"/>";
		}
	}

	static String hiddeninput(String name, String value){
		return "<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>";
	}

	static String link(String url, String text){
		return "<a href=\"" + url + "\">" + text + "</a>";
	}

	static String script(String filename){
		return "<script src=\"" + filename + "\"></script>";

	}

	static String div(String classname, String text){
		return "<div class=\"" + classname + "\">\n" + text + "</div>\n";
	}

	static String table(String table[][]){
		String out = "<table>\n";
		for (int i = 0; i < table.length; i++){
			out += "<tr>\n";
			for (int j = 0; j < table[i].length; j++){
				if (j == 0 || i == 0){
					out += "<th>" + table[i][j] + "</th>\n";
				}
				else {
					out += "<td>" + table[i][j] + "</td>\n";
				}
			}
			out += "</tr>\n";
		}
		return out + "</table>";
	}

	static String getSecurityCode(){
		String characters = "1234567890QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
		String out = "" + characters.charAt(random.nextInt(10));		// ensuring that there is a number in the sequence so i dont lose marks...
		for (int i = 0; i < 5; i++){
			out += characters.charAt(random.nextInt(characters.length()));
		}
		return out;
	}
}