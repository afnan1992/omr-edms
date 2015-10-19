package com.omr.exceptions;

public class FolderNotThere extends Exception {

	public FolderNotThere() {
		// TODO Auto-generated constructor stub
	}

	public FolderNotThere(String arg0) {
		super("Error Code"+arg0+":Folder possibly deleted");
		// TODO Auto-generated constructor stub
	}

	public FolderNotThere(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public FolderNotThere(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public FolderNotThere(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
