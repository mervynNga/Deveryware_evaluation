package com.mervyn.deveryware.evaluation_exo;

import java.io.File;

import junit.framework.TestCase;

public class YamlFileParserTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testParseYamlFile() {
		String testFilePath = "res"+File.separator+"info.yaml";
		Yaml yaml = YamlFileParser.parseYamlFile(testFilePath);
		assertEquals(yaml.getName(), "dakar");
		assertEquals(yaml.getDependencies().getArgs(), "any");
		assertEquals(yaml.getDependencies().getGoogle_maps(), "^3.1.0");
		if(yaml.getTransformers().getValues().size()==3) {
			fail("bad transformers extraction");
		}
	}

	public void testExtractValueLinkedToProperty() {
		String textForTest = "google_maps: ^3.1.0";
		String regexPart = "google_maps";
		String rsltValue = "^3.1.0";
		String propertyValue = YamlFileParser.extractValueLinkedToProperty(regexPart, textForTest);
		if(!propertyValue.equals(rsltValue)) {
			fail("bad property extraction");
		}
		regexPart = "dependencies";
		propertyValue = YamlFileParser.extractValueLinkedToProperty(regexPart, textForTest);
		if(propertyValue!=null) {
			fail("bad property extraction");
		}
		
	}

}
