package com.mervyn.deveryware.evaluation_exo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class YamlFileParser {
	
	public static Yaml parseYamlFile(String filePath) {
		Yaml rslt = null;
		String name = null;
		String description = null;
		String version = null;
		boolean isInNamePart = false;
		boolean isInDescriptionPart = false;
		boolean isInVersionPart = false;
		boolean isInDependenciesPart = false;
		Dependencies dependencies = new Dependencies();
		Dev_dependencies dev_dependencies = new Dev_dependencies();
		boolean isInDev_DependenciesPart = false;
		boolean isInTransformersPart = false;
		Transformers transformers = new Transformers();
		List<String> lines = readFile(filePath);
		for(String line : lines ) {
			String lineTrimmed = line.trim();
			
			isInNamePart =  lineTrimmed.startsWith("name");
			if(isInNamePart && name==null) {
				name = lineTrimmed.replaceFirst("name:", "").trim();
			}
			
			isInDescriptionPart =  lineTrimmed.startsWith("description");
			if(isInDescriptionPart && description==null) {
				description = lineTrimmed.replaceFirst("description:", "").trim();
			}
			
			isInVersionPart =  lineTrimmed.startsWith("version");
			if(isInVersionPart && version==null) {
				version = lineTrimmed.replaceFirst("version:", "").trim();
			}
			
			isInTransformersPart =  isInTransformersPart || lineTrimmed.startsWith("transformers");
			if(isInTransformersPart) {
				isInDependenciesPart = false; 
				isInDev_DependenciesPart = false;
				if(!lineTrimmed.startsWith("transformers")) {
				transformers.addValue(lineTrimmed);
				}
			}
			
			isInDev_DependenciesPart =  isInDev_DependenciesPart || lineTrimmed.startsWith("dev_dependencies");
			if(isInDev_DependenciesPart) {
				isInDependenciesPart = false; 
				isInTransformersPart = false;
				dev_dependencies = fillDev_Dependencies(dev_dependencies, lineTrimmed);
			}
			
			isInDependenciesPart =  isInDependenciesPart || lineTrimmed.startsWith("dependencies");
			if(isInDependenciesPart) {
				isInDev_DependenciesPart = false; 
				isInTransformersPart = false;
				dependencies = fillDependencies(dependencies, lineTrimmed);
			}
			
		}
		rslt = new Yaml(name, version, description);
		rslt.setDependencies(dependencies);
		rslt.setDev_dependencies(dev_dependencies);
		rslt.setTransformers(transformers);
		return rslt;
	}
	
	private static Dev_dependencies fillDev_Dependencies(Dev_dependencies dev_dependencies, String lineTrimmed) {
		if(lineTrimmed.endsWith("any")) {
			//rien faire
		}else {
			String regexPart = "test";
			String property = extractValueLinkedToProperty(regexPart, lineTrimmed);
			if(property!=null) {
				dev_dependencies.setTest(property);
			}			
		}
		return dev_dependencies;
	}
	
	private static Dependencies fillDependencies(Dependencies dependencies, String lineTrimmed) {
		if(lineTrimmed.endsWith("any")) {
			//rien faire
		}else {
			String regexPart = "args";
			String property = extractValueLinkedToProperty(regexPart, lineTrimmed);
			if(property!=null) {
				dependencies.setArgs(property);
			}
			
			regexPart = "browser";
			property = extractValueLinkedToProperty(regexPart, lineTrimmed);
			if(property!=null) {
				dependencies.setBrowser(property);
			}
			
			regexPart = "geo";
			property = extractValueLinkedToProperty(regexPart, lineTrimmed);
			if(property!=null) {
				dependencies.setGeo(property);
			}
			
			regexPart = "shelf";
			property = extractValueLinkedToProperty(regexPart, lineTrimmed);
			if(property!=null) {
				dependencies.setShelf(property);
			}
			
			regexPart = "shelf_web_socket";
			property = extractValueLinkedToProperty(regexPart, lineTrimmed);
			if(property!=null) {
				dependencies.setShelf_web_socket(property);
			}
			
			regexPart = "shelf_static";
			property = extractValueLinkedToProperty(regexPart, lineTrimmed);
			if(property!=null) {
				dependencies.setShelf_static(property);
			}
			
			regexPart = "xml_rpc";
			property = extractValueLinkedToProperty(regexPart, lineTrimmed);
			if(property!=null) {
				dependencies.setXml_rpc(property);
			}
			
			regexPart = "google_maps";
			property = extractValueLinkedToProperty(regexPart, lineTrimmed);
			if(property!=null) {
				dependencies.setGoogle_maps(property);
			}
			
			regexPart = "dart_to_js_script_rewriter";
			property = extractValueLinkedToProperty(regexPart, lineTrimmed);
			if(property!=null) {
				dependencies.setDart_to_js_script_rewriter(property);
			}
			
		}
		return dependencies;
	}
	
	public static String extractValueLinkedToProperty(String regexPart, String lineTrimmed) {
		if(lineTrimmed.startsWith(regexPart)) {
			String property = lineTrimmed.replaceFirst(regexPart+":", "").trim();
			return property;
		}else {
			return null;
		}
	}
	
	
	private static List<String> readFile(String filePath) {
		List<String> rslt = new ArrayList<>();
		try {
			InputStream fis = new FileInputStream(filePath);
			InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(isr);
			String line;
			while (( line = br.readLine()) != null) {
				rslt.add(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rslt;
	}
}
