<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<!-- xmlns:xs="jttp://www.w3.org" -->

<xsl:output method="html" indent="yes" version="5.0"/>

<xsl:template match="/">
	<html>
	<body>
		<h2>Hallgatok choose</h2>
		<table border="1">
			<tr bgcolor="#c8f542">
        		<th>ID</th>
        		<th>Vezeteknev</th>
        		<th>Keresztnev</th>
        		<th>Becenev</th>
        		<th>Fizetes</th>
        		<th>Fokozat</th>
      		</tr>
      		<xsl:for-each select="class/student">
        		<tr>
          			<td><xsl:value-of select="./@id"/></td>
          			<td><xsl:value-of select="vezeteknev"/></td>
          			<td><xsl:value-of select="keresztnev"/></td>
          			<td><xsl:value-of select="kor"/></td>
          			<td><xsl:value-of select="fizetes"/></td>
          			<xsl:choose>
          				<xsl:when test="fizetes > 500000">
          					<td>High</td>
          				</xsl:when>
          				<xsl:when test="fizetes > 400000">
          					<td>Medium</td>
          				</xsl:when>
          				<xsl:otherwise>
          					<td>Low</td>
          				</xsl:otherwise>
          			</xsl:choose>
        		</tr>
      		</xsl:for-each>
		</table>
	</body>
	</html>
</xsl:template>
</xsl:stylesheet>