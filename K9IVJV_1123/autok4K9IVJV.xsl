<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<h2>Miskolci tulajdonosok rendszámainak listája</h2>
			<ul>
				<xsl:for-each select="autok/auto">
					<xsl:if test="varos='Miskolc'">
						<li><xsl:value-of select="@rsz"/></li>
					</xsl:if>
				</xsl:for-each>
			</ul>
		</html>
	</xsl:template>
</xsl:stylesheet>