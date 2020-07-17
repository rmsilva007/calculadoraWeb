package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.Date;
import dao.HistoricoDAO;
import model.Historico;



@WebServlet("/calcular")
public class CalculadoraController extends HttpServlet{
    
    @Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) {
		try{
			req.getRequestDispatcher("/view/calcular.jsp").forward(req, res);
		} catch (Exception e){
			System.out.println(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String a = req.getParameter("a");
			String b = req.getParameter("b");
			String operacao = req.getParameter("operacao");

			String nome = "";
			HttpSession session = req.getSession(false);  
            if((session!=null) && ((String)session.getAttribute("username") != null)){   
				nome = (String)session.getAttribute("username");
			}
			
			b = b.substring(1, b.length());
			Historico historico = new Historico();
			historico.setNome(nome);
			historico.setNum1(a);
			historico.setNum2(b);
			historico.setOperacao(operacao);
			Date d = new Date();
			String data = DateFormat.getDateTimeInstance().format(d);
			historico.setHorario(data);
			

			Double num1 = Double.parseDouble(a);
			Double num2 = Double.parseDouble(b);
			Double resultado = 0.0;
			String msg = null;

			if(operacao.equals("+")){
				resultado = num1 + num2;
			}
			else if(operacao.equals("-")){
				resultado = num1 - num2;
			}
			else if(operacao.equals("x")){
				resultado = num1 * num2;
			}
			else if(operacao.equals("/")){
				if( operacao.equals("/") && num2 == 0 ){
					msg = "Nao e permitido divisao por 0";
				} 
				else {
					resultado = num1/num2;
				}
			}
			else if(operacao.equals("raiz")){
				if(operacao.equals("raiz") && (num2 < 0)){
					msg = "Nao e permitido obter a raiz de um numero negativo";
				}
				else{
					historico.setNum1("2");
					resultado = Math.sqrt(num2);
				}
			}
			else if(operacao.equals("^")){
				resultado = Math.pow(num1, num2);
			}

		
			if(msg != null){
				HistoricoDAO historicoDao = new HistoricoDAO();
				historico.setResultado(msg);
				historicoDao.saveHistory(historico);

				resp.setContentType("text/html;charset=UTF-8");
				resp.getWriter().write(msg);
			}
			else {
				HistoricoDAO historicoDao = new HistoricoDAO();
				historico.setResultado(resultado.toString());
				historicoDao.saveHistory(historico);

				resp.setContentType("text/html;charset=UTF-8");
				resp.getWriter().write(resultado.toString());
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
			


			
