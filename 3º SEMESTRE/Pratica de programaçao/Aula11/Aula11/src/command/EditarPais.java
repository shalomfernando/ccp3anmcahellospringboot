package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pacote.Pais;
import service.PaisService;

public class EditarPais implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pId = request.getParameter("id");
		String pNome = request.getParameter("nome");
		String pPopulacao = request.getParameter("populacao");
		String pArea = request.getParameter("area");
		
		int id = -1;
		long populacao=-1;
		double area=-1.0;
		try {
			id = Integer.parseInt(pId);
			populacao = Long.parseLong(pPopulacao);
			area = Double.parseDouble((pArea));
		} catch (NumberFormatException e) {

		}

		Pais pais = new Pais();
		pais.setId(id);
		pais.setNome(pNome);
		pais.setPopulacao(populacao);
		pais.setArea(area);
		
		PaisService cs = new PaisService();
		RequestDispatcher view = null;
		HttpSession session = request.getSession();
		
		pais = cs.carregar(pais.getId());
		request.setAttribute("pais", pais);
		view = request.getRequestDispatcher("AlterarPais.jsp");
		view.forward(request, response);

	
	}
	public int busca(Pais pais, ArrayList<Pais> lista) {
		Pais to;
		for (int i = 0; i < lista.size(); i++) {
			to = lista.get(i);
			if (to.getId() == pais.getId()) {
				return i;
			}
		}
		return -1;
	}

}
