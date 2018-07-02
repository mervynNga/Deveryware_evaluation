package com.mervyn.deveryware.evaluation_exo;

public class Yaml {
	private Dependencies dependencies;
	private Dev_dependencies dev_dependencies;
	private Transformers transformers;
	
	private String name, version, description;

	public Yaml(String name, String version, String description) {
		super();
		this.name = name;
		this.version = version;
		this.description = description;
	}

	public Dev_dependencies getDev_dependencies() {
		return dev_dependencies;
	}

	public void setDev_dependencies(Dev_dependencies dev_dependencies) {
		this.dev_dependencies = dev_dependencies;
	}

	public Dependencies getDependencies() {
		return dependencies;
	}

	public void setDependencies(Dependencies dependencies) {
		this.dependencies = dependencies;
	}

	public Transformers getTransformers() {
		return transformers;
	}

	public void setTransformers(Transformers transformers) {
		this.transformers = transformers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
