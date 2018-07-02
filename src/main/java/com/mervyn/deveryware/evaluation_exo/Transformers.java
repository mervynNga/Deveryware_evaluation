package com.mervyn.deveryware.evaluation_exo;

import java.util.ArrayList;
import java.util.List;

public class Transformers {
	private List<String> values = new ArrayList<>();
	
	public void addValue(String transformer) {
		values.add(transformer);	
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
}
