<%@ page language="java" contentType="text/html; charset=windows-1256"
 pageEncoding="windows-1256"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1256">
<title>Create planning</title>
</head>
<body>
<form action="savePlanning" method="post">
<pre>
Nombre de jours : <input type="text" name="nb_jours">
id du vol : <input type="text" name="idVol">
Fichier pdf : <input type="text" name="fichierPlanning">
<input type="submit" value="Ajouter">
</pre>
</form>
${msg}
<br/>
<br/>
<a href="ListePlannings">Liste plannings</a>
</body>
</html>