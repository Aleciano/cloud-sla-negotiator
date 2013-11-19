<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.edu.ifpb.mysla.interfaces.Strategy;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Auto-SLA: ativação da instância</title>
<style type="text/css">
p {
	color: blue;
	text-align: center;
}

a {
	color: blue;
}

body {
	background-color: #b0c4ff;
	text-align: center;
}

.center {
	margin: auto;
	width: 40%;
	background-color: #c0c4ff;
	border-width: 150px;
}

input[type=submit] {
	height: 70px;
	width: 200px;
	font-size: 24px;
}

.baixo {
	margin: auto;
	vertical-align: sub;
}
</style>
</head>
<body>
	<div class="center">
	<%
		String conflict = (String) request.getAttribute("conflict");

		if (conflict.equals("yes")) {
			//		if (session.getAttribute("conflict") != null) {
	%>
	<h3><br><font color="MidnightBlue ">:: Escolha a estratégia de negociação ::</font></h3>
	<!-- Um if pra ver se é pra exibir a seleção ou só o nome da ÚNICA estratégia identificada -->
	<%
		ArrayList<Strategy> strategies = (ArrayList<Strategy>) request
					.getAttribute("strategies");
			out.print("<p>Opções:</p>");
			out.print("<form action=\"requestservlet\" method=\"post\">");
			int i = 0;
			for (Strategy aux : strategies) {
				//out.println("<br>" + aux.toString() + "<br>");
				out.print("<input type=\"radio\" name=\"strategy_index\" value=\""
						+ Integer.toString(i++)
						+ "\" checked/> "
						+ aux.getName()
						+ "<br>");
			}
	%>
	<%
		} else {
			out.print("<h3><font color=\"MidnightBlue \"><br><br>Ativar estratégia?</font></h3>");
			out.print("<form action=\"requestservlet\" method=\"post\">");
			ArrayList<Strategy> strategies = (ArrayList<Strategy>) request
					.getAttribute("strategies");
			out.print("<input type=\"radio\" name=\"strategy_index\" value=\"0"
					+ "\" checked/> " + strategies.get(0).getName() + "<br>");

		}
	%>
	<%
		out.print("<br><br><input type=\"submit\" name=\"Ativar\" value=\"Ativar\" />");
		out.print("<input type=\"hidden\" name=\"flag\" value=\"selection\">");
		out.print("</form>");
	%>
	<!-- Exibe o botão de Ativar, é um form também que manda um hidden com o nome de selection -->
	</div>
</body>
</html>