package servlets;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Harmonogram")

public class Harmonogram extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		double kwotaPoczatkowa = 0;
		double iloscRat = 0;
		double oprocentowanie = 0;
		double oplataStala = 0;
		
		DecimalFormat df = new DecimalFormat("#.00");
		String kwota = request.getParameter("kwota");
		String raty = request.getParameter("raty");
		String procent = request.getParameter("procent");
		String oplata = request.getParameter("oplata");
		String rodzaj = request.getParameter("rata");
		
		try {
			kwotaPoczatkowa = Double.parseDouble(request.getParameter("kwota"));
			iloscRat = Double.parseDouble(request.getParameter("raty"));
			oprocentowanie = Double.parseDouble(request.getParameter("procent"));
			oplataStala = Double.parseDouble(request.getParameter("oplata"));
		
		if(kwota==null || kwota.equals("") || kwota.equals("0") || raty==null || raty.equals("") || raty.equals("0") || 
				procent==null || procent.equals("") || procent.equals("0") || oplata==null || oplata.equals("") || 
				oplata.equals("0") || rodzaj==null || rodzaj.equals("") || rodzaj.equals("0")){
			response.sendRedirect("/");
		}
				
			response.setContentType("text/html");
			response.getWriter().println("Wyliczenie rat dla kwoty " + kwotaPoczatkowa +".");
			response.getWriter().println("<table border='1'><tr>"
					+ "<td>Nr raty</td>"
					+ "<td>Kwota Kapita³u</td>"
					+ "<td>Kwota Odsetek</td>"
					+ "<td>Op³aty sta³e</td>"
					+ "<td>Ca³kowita kwoty raty</td>"
					+ "</tr>");
	
			if (rodzaj.equals("m")){
				
				double kwotaKapitalu = kwotaPoczatkowa/iloscRat;
				double miesieczneOprocentowanie = (oprocentowanie/12)/100;
				double czescStala = oplataStala/iloscRat;
	
				for (int i = 1; i <= iloscRat; i++){
					double odsetki = (kwotaPoczatkowa*miesieczneOprocentowanie);
					double calkowita_rata = kwotaKapitalu + czescStala + odsetki;
					response.getWriter().println("<tr>"
							+ "<td>"+i+"</td>"
							+ "<td>"+df.format(kwotaKapitalu)+"</td>"
							+ "<td>"+df.format(odsetki)+"</td>"
							+ "<td>"+df.format(czescStala)+"</td>"
							+ "<td>"+df.format(calkowita_rata)+"</td></tr>");
					kwotaPoczatkowa = kwotaPoczatkowa - kwotaKapitalu;
				}
			}else {
				
				double kwotaKapitalu = kwotaPoczatkowa/iloscRat;
				double miesieczneOprocentowanie = (oprocentowanie/12)/100;
				double pomocnicza = 1 + miesieczneOprocentowanie;
				double stala = ((kwotaPoczatkowa*Math.pow(pomocnicza, iloscRat)*(pomocnicza-1))/(Math.pow(pomocnicza, iloscRat)-1));
				
				response.getWriter().println(stala);
				
				for (int i = 1; i <= iloscRat; i++){
					double odsetki = (kwotaPoczatkowa*miesieczneOprocentowanie);
					double rataKapitalowa = stala - odsetki;
					double calkowita_rata = stala + oplataStala/iloscRat;
					response.getWriter().println("<tr>"
							+ "<td>"+i+"</td>"
							+ "<td>"+df.format(rataKapitalowa)+"</td>"
							+ "<td>"+df.format(odsetki)+"</td>"
							+ "<td>"+df.format(oplataStala/iloscRat)+"</td>"
							+ "<td>"+df.format(calkowita_rata)+"</td></tr>");
					kwotaPoczatkowa = kwotaPoczatkowa - kwotaKapitalu;
				}
			response.getWriter().println("</table>");
			}
		} catch (NumberFormatException e) {
			response.sendRedirect("/");
		}
	}
}
