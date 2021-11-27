<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output indent="yes"/>
    <xsl:template match="/">
        <html>
            <h2>Elemek száma a dokumentumban</h2>
            <xsl:value-of select="count(//*)"/>
        </html>
    </xsl:template>
</xsl:stylesheet>