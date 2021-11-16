<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
	<html>
	<body>
		<h2>Simon Marcell órarend – 2021/22. I. félév.</h2>
		<table border="1">
			<tr bgcolor="#c8f542">
				<th>Nap</th>
        		<th>Kezdés</th>
        		<th>Vége</th>
        		<th>Tárgy</th>
        		<th>Típus</th>
        		<th>Helyszín</th>
        		<th>Oktató</th>
        		<th>Szak</th>
      		</tr>
      		<xsl:for-each select="orarend/ora">
        		<tr>
          			<td><xsl:value-of select="idopont/nap"/></td>
          			<td><xsl:value-of select="idopont/tol"/></td>
          			<td><xsl:value-of select="idopont/ig"/></td>
          			<td><xsl:value-of select="targy"/></td>
          			<td><xsl:value-of select="./@tipus"/></td>
          			<td><xsl:value-of select="helyszin"/></td>
          			<td><xsl:value-of select="oktato"/></td>
          			<td><xsl:value-of select="szak"/></td>
        		</tr>
      		</xsl:for-each>
		</table>
	</body>
	</html>
</xsl:template>
</xsl:stylesheet>