<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" >
    
    <xsl:output method="html"/>
    
    <xsl:template match="/">
        <html>
            <head>
                <title>Artistas</title>
                <meta charset="UTF-8"/>
                <style>
                    body {
                    
                    background-color:rgb(221, 173, 173);
                    
                    }
                    table {
                    width: 100%;
                    
                    }
                    th, td {
                    padding: 10px;
                    text-align: left;
                    }
                </style>
            </head>
            <body>
                <h1>Artistas</h1>
                <table>
                    <tr>
                        <th>Codigo</th>
                        <th>Nombre</th>
                        <th>Nacimiento</th>
                        <th>Muerte</th>
                        <th>Pais</th>
                        <th>Pagina Web</th>
                    </tr>
                    
                    <xsl:for-each select="artistas/artista[nacimiento > 1500]">
                        <xsl:sort select="nacimiento" data-type="number" order="ascending"/>
                        
                        <tr>
                            <td><xsl:value-of select="@cod"/></td>
                            <td><xsl:value-of select="nombreCompleto"/></td>
                            <td><xsl:value-of select="nacimiento"/></td>
                            <td>
                                <xsl:choose>
                                    <xsl:when test="fallecimiento">
                                        <xsl:value-of select="fallecimiento"/>
                                    </xsl:when>
                                    <xsl:otherwise>Desconocido</xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td><xsl:value-of select="pais"/></td>
                            <td>
                                <a href="{fichaCompleta}">
                                    <img>
                                        <xsl:attribute name="src">
                                            <xsl:choose>
                                                <xsl:when test="nombreCompleto='Diego Velázquez'">Autorretrato_de_Velázquez_en_las_Meninas.jpg</xsl:when>
                                                <xsl:when test="nombreCompleto='Michelangelo Caravaggio'">Bild-Ottavio_Leoni,_Caravaggio.jpg</xsl:when>
                                                <xsl:when test="nombreCompleto='Herrada de Landsberg'">Herrad_von_Landsberg_-_Head.jpg</xsl:when>
                                                <xsl:when test="nombreCompleto='Francisco de Goya'" >Vicente_López_Portaña_-_el_pintor_Francisco_de_Goya.jpg</xsl:when>
                                            </xsl:choose>
                                        </xsl:attribute>
                                        <xsl:attribute name="title">Saber más</xsl:attribute>
                                        <xsl:attribute name="width">60</xsl:attribute>
                                    </img>
                                </a>
                            </td>
                        </tr>
                        
                    </xsl:for-each>
                    
                </table>
            </body>
        </html>
    </xsl:template>
    
    
</xsl:stylesheet>
