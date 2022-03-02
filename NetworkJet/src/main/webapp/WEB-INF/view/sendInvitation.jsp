<%@ page language="java" contentType="text/html; charset=windows-1256"
 pageEncoding="windows-1256"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1256">
<title>Send invitation</title>
</head>
<body>
<form action="saveInvitation" method="post">
<pre>
Date d'expiration : <input type="date" name="date">
Message : <input type="text" name="message">
Email : <input type="text" name="email">
<input type="submit" value="envoyer">
</pre>
</form>
${msg}
<br/>
<br/>
<a href="ListeInvitations">Liste Invitations</a>
</body>
</html>