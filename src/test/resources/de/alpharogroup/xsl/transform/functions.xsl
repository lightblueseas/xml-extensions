<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="xml" indent="yes"/>

  <xsl:template match="*">
      <xsl:copy><xsl:apply-templates /></xsl:copy>
  </xsl:template>

  <xsl:template match="date">
     <xsl:copy>
         <xsl:call-template name="formatdate">
             <xsl:with-param name="date" select="."/>
        </xsl:call-template>
     </xsl:copy>
  </xsl:template>


    <xsl:template name="formatdate">
       <xsl:param name="date" />

       <xsl:variable name="timestr">
           <xsl:value-of select="substring-after($date,'T')" />
       </xsl:variable>

       <xsl:variable name="HH">
           <xsl:value-of select="substring($timestr,1,2)" />
       </xsl:variable>

       <xsl:variable name="mm">
          <xsl:value-of select="substring($timestr,4,2)" />
       </xsl:variable>

       <xsl:value-of select="concat($HH,':',$mm)" />
    </xsl:template>

</xsl:stylesheet>
