package com.clouddev.androidgenerator.reader;

import java.io.File;

import javax.xml.bind.JAXB;

import com.clouddev.androidgenerator.model.Root;

public class ModelReader {
	public static Root readFile(String filePath) {
		return JAXB.unmarshal(new File(filePath), Root.class);
	}
}
