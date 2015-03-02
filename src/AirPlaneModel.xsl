<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:template match="/">
        <html>
            <body>
                <h1>PlaneModels</h1>
                <table border="1">
                    <tr>
                        <th>name</th>
                        <th>flightRange</th>
                        <th>Fuselage</th>
                        <th>LandGear</th>
                        <th>Avionic</th>
                        <th>Engine</th>
                    </tr>
                    <xsl:for-each select="Room/toy">
                        <tr>
                            <td>
                                <xsl:value-of select="price" />
                            </td>
                            <td>
                                <xsl:value-of select="cartype" />
                            </td>
                            <td>
                                <xsl:value-of select="size" />
                            </td>
                            <td>
                                <xsl:value-of select="color" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>