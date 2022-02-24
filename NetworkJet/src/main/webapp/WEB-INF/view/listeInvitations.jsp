<%@ page language="java" contentType="text/html; charset=windows-1256"
 pageEncoding="windows-1256"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Liste Invitations</title>
</head>
<body>
 <h1 >
 Liste des Invitations
 </h1>
 <table >
 <tr>
 <th>ID</th><th>Token</th><th>Message</th><th>Date Expiration</th><th>Suppression<th>Edition</th>
 </tr>
 <c:forEach items="${invitations}" var="i">
 <tr>
 <td>${i.idInvitation}</td>
  <td>${i.token}</td>
 <td>${i.message}</td>
 <td>${i.date_expiration}</td>
 <td><fmt:formatDate pattern="dd/MM/yyyy" value="${i.date_expiration}" /></td>
 <td><a onclick="return confirm('Etes-vous sûr ?')" href="supprimerInvitation?id=${i.idInvitation }">Supprimer</a></td>
 <td><a href="modifierInvitation?id=${i.idInvitation }">Edit</a></td>
 </tr>
 </c:forEach>
 </table>
</body>
</html>