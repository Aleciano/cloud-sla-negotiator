<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="br.edu.ifpb.aleciano.interfaces.Strategy;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Auto-SLA: Negociação em andamento</title>
<style type="text/css">
p {
	color: red;
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
		Strategy strategy = (Strategy) request.getAttribute("strategy");
		
		if(strategy!=null){
		out.print("<h3><font color=\"MidnightBlue \"><br><br>Estratégia '" + strategy.getName() + "' ativa!</font></h3><br><br>");

		out.print("<p>Negociação em andamento...</p><br><br>");
		}
		else{
			
			out.print("<br><br><p>Não houve estratégias aplicáveis<br>	Por favor, refine a pesquisa.</p><br><br>");
		}
	%>
	</div>
</body>
</html>