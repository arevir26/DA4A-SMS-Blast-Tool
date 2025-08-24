package com.arevir26.smsblast.GUI;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class CSVFileFilter extends FileFilter{

	@Override
	public boolean accept(File f) {
		
		return true;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "CSV Files";
	}



}
