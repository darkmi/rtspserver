package com.darkmi.apm.xstream;

import java.util.ArrayList;
import java.util.List;

public class Blog {
	@SuppressWarnings("unused")
	private Author writer;
	@SuppressWarnings("rawtypes")
	private List entries = new ArrayList();

	public Blog(Author writer) {
		this.writer = writer;
	}

	@SuppressWarnings("unchecked")
	public void add(Entry entry) {
		entries.add(entry);
	}

	@SuppressWarnings("rawtypes")
	public List getContent() {
		return entries;
	}

}
