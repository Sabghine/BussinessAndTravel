<%@ page language="java" contentType="text/html; charset=windows-1256"
 pageEncoding="windows-1256"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Liste Plannings</title>
</head>
<body>
 <h1 >
 Liste des Plannings
 </h1> 
 <table >
 <tr>
 <th>ID</th><th>nombre de jours</th><th>id du vol</th><th>Fichier PDF</th><th>Suppression<th>Edition</th>
 </tr>
 <c:forEach items="${plannings}" var="p">
 <tr>
 <td>${p.idPlanning }</td>
 <td>${p.nb_jours }</td>
 <td>${p.idVol }</td>
 <td>${p.fichierPlanning }</td>
 <td><a onclick="return confirm('Etes-vous sûr ?')"
href="supprimerProduit?id=${p.idPlanning }">Supprimer</a></td>
 <td><a href="modifierProduit?id=${p.idPlanning }">Edit</a></td>
 </tr>
 </c:forEach> 
 </table>
</body>
</html>