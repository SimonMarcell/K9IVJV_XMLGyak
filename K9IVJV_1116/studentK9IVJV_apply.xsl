<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Simon Marcell apple- template</h2>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="student">
        <p>
            <xsl:apply-templates select="./@id"/>
            <xsl:apply-templates select="vezeteknev"/>
            <xsl:apply-templates select="keresztnev"/>
            <xsl:apply-templates select="becenev"/>
            <xsl:apply-templates select="kor"/>
            <xsl:apply-templates select="fizetes"/>
        </p>
    </xsl:template>

    <xsl:template match="@id">
        ID:
        <xsl:value-of select="."/>
        <br/>
    </xsl:template>

    <xsl:template match="vezeteknev">
        Vezeteknev:
        <span style="color:green">
            <xsl:value-of select="."/>
        </span>
        <br/>
    </xsl:template>

    <xsl:template match="keresztnev">
        Keresztnev:
        <span style="color:brown">
            <xsl:value-of select="."/>
        </span>
        <br/>
    </xsl:template>

    <xsl:template match="becenev">
        <xsl:value-of select="."/> Kor:
    </xsl:template>

    <xsl:template match="kor">
        <span style="color:blue">
            <xsl:value-of select="."/>
        </span>
        <br/>
    </xsl:template>

    <xsl:template match="fizetes">
        Fizetes:
        <span style="color:red">
            <xsl:value-of select="."/>
        </span>
        <br/>
    </xsl:template>
</xsl:stylesheet>