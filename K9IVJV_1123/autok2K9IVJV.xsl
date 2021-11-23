<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<h2>30000-nél drágább autók száma</h2>
			<xsl:value-of select="count(autok/auto[ar/text()>'30000'])"/>
		</html>
	</xsl:template>
</xsl:stylesheet>