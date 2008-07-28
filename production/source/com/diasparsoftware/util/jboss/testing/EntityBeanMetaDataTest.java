package com.diasparsoftware.util.jboss.testing;

import java.io.FileReader;

import javax.xml.transform.TransformerException;

import org.custommonkey.xmlunit.*;
import org.custommonkey.xmlunit.exceptions.XpathException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public abstract class EntityBeanMetaDataTest extends XMLTestCase {
    private String metaDataFilename;
    private Document metaDataDocument;
    private String entityBeanName;

    protected void setUp() throws Exception {
        parseMetaData();
    }

    protected void setMetaDataFilename(String metaDataFilename) {
        this.metaDataFilename = metaDataFilename;
    }

    protected void setEntityBeanUnderTest(String entityBeanName) {
        this.entityBeanName = entityBeanName;
    }

    protected void parseMetaData() throws Exception {
        XMLUnit.setIgnoreWhitespace(true);

        metaDataDocument =
            XMLUnit.buildTestDocument(
                new InputSource(new FileReader(metaDataFilename)));
    }

    protected void assertBeanMappedToTable(String expectedTableName)
        throws TransformerException, XpathException {

        assertXpathEvaluatesTo(
            expectedTableName,
            getXpathRelativeToEntityBean(entityBeanName, "table-name"),
            metaDataDocument);
    }

    protected void assertFieldMappedToColumn(
        String fieldName,
        String expectedColumnName)
        throws TransformerException, XpathException {

        assertXpathEvaluatesTo(
            expectedColumnName,
            getColumnMappingForField(entityBeanName, fieldName),
            metaDataDocument);
    }

    protected void assertDefaultDataSource(String expectedDataSourceJndiName)
        throws TransformerException, XpathException {

        assertXpathEvaluatesTo(
            expectedDataSourceJndiName,
            "/jbosscmp-jdbc/defaults/datasource",
            metaDataDocument);
    }

    private String getColumnMappingForField(
        String entityBeanName,
        String fieldName) {

        return getXpathRelativeToEntityBean(
            entityBeanName,
            "cmp-field[field-name='" + fieldName + "']/column-name");
    }

    private String getXpathRelativeToEntityBean(
        String entityBeanName,
        String relativeXpath) {

        return getEntityBeanXpath(entityBeanName) + relativeXpath;
    }

    private String getEntityBeanXpath(String entityBeanName) {
        return "/jbosscmp-jdbc/enterprise-beans/"
            + "entity[ejb-name='"
            + entityBeanName
            + "']/";
    }
}
