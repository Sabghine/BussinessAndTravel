<%@ page language="java" contentType="text/html; charset=windows-1256"
 pageEncoding="windows-1256"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1256">
<title>Modifier une Invitation</title>
</head>
<body>
<form action="updateInvitation" method="post">
<pre>
id : <input type="text" name="idInvitation" value="${invitation.idInvitation}">
Date Expiration :
 <fmt:formatDate pattern="yyyy-MM-dd" value="${invitation.date_expiration}"
var="formatDate" />
 <input type="date" name="date" value="${formatDate}"></input>
Message :<input type="text" name="message" value="${invitation.message}">
Token :<input type="text" name="token" value="${invitation.token}">

<input type="submit" value="Modifier">
</pre>
</form>
<br/>
<br/>
<a href="ListeInvitations">Liste invitations</a>
</body>
</html>
