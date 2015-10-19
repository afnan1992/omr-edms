package helper;

import java.util.ArrayList;

import org.bytedeco.javacpp.opencv_core.IplImage;

import com.google.gson.JsonArray;
import com.omr.exceptions.CellsWrongDetection;

public class UserIdQuizIdGrid {
	private ArrayList<Column> columns;
	private JsonArray rows,cells;
	IplImage image;
	int average_radius;
	
	
	
	public UserIdQuizIdGrid(JsonArray rows,
			JsonArray cells, IplImage image, int average_radius) {
		super();
		this.columns =new ArrayList<>();
		this.rows = rows;
		this.cells = cells;
		this.image = image;
		this.average_radius = average_radius;
	}
	public void addColumns() throws CellsWrongDetection
	{
		for(int i=0;i<19;i++)
		{
			Column column=new Column(i, image, cells, rows, average_radius);
			columns.add(column);
		}
			
	}
	public String[] getAllOptions()
	{
		int listsize = columns.size();
		String[] ret = new String[listsize];
		for(int i=0; i<listsize; i++){
			
			    ret[i]=columns.get(i).getResult();
		}
		return ret;
	}

}
