package helper;

import org.bytedeco.javacpp.opencv_core.IplImage;

import com.google.gson.JsonArray;
import com.omr.exceptions.CellsWrongDetection;

import config.Config;

public class Column extends Config {
	private int average_radius;
	private UserIdQuizIdRectangle zero,one,two,three,four,five,six,seven,eight,nine;
	IplImage image;
	JsonArray cells,rows;
	int column_number;
	public Column(int number,IplImage image, JsonArray cells, JsonArray rows,int average_radius ) throws CellsWrongDetection {
		super();
		this.average_radius = average_radius;

		this.image = image;
		this.cells = cells;
		this.rows = rows;
		this.column_number = number;

		zero=new UserIdQuizIdRectangle(image);
		one=new UserIdQuizIdRectangle(image);
		two=new UserIdQuizIdRectangle(image);
		three=new UserIdQuizIdRectangle(image);
		four=new UserIdQuizIdRectangle(image);
		five=new UserIdQuizIdRectangle(image);
		six=new UserIdQuizIdRectangle(image);
		seven=new UserIdQuizIdRectangle(image);
		eight=new UserIdQuizIdRectangle(image);
		nine=new UserIdQuizIdRectangle(image);
		SetOptionsCorners();
	}
	public void SetOptionsCorners() throws CellsWrongDetection
	{
		zero.setCorn(cell(column_number,0,"x")-average_radius,row(column_number,0,"y")-average_radius,cell(column_number,0,"x")+average_radius,row(column_number,0,"y")+average_radius);
		one.setCorn(cell(column_number,1,"x")-average_radius,row(column_number,1,"y")-average_radius,cell(column_number,1,"x")+average_radius,row(column_number,1,"y")+average_radius);
		two.setCorn(cell(column_number,2,"x")-average_radius,row(column_number,2,"y")-average_radius,cell(column_number,2,"x")+average_radius,row(column_number,2,"y")+average_radius);
		three.setCorn(cell(column_number,3,"x")-average_radius,row(column_number,3,"y")-average_radius,cell(column_number,3,"x")+average_radius,row(column_number,3,"y")+average_radius);
		four.setCorn(cell(column_number,4,"x")-average_radius,row(column_number,4,"y")-average_radius,cell(column_number,4,"x")+average_radius,row(column_number,4,"y")+average_radius);
		five.setCorn(cell(column_number,5,"x")-average_radius,row(column_number,5,"y")-average_radius,cell(column_number,5,"x")+average_radius,row(column_number,5,"y")+average_radius);
		six.setCorn(cell(column_number,6,"x")-average_radius,row(column_number,6,"y")-average_radius,cell(column_number,6,"x")+average_radius,row(column_number,6,"y")+average_radius);
		seven.setCorn(cell(column_number,7,"x")-average_radius,row(column_number,7,"y")-average_radius,cell(column_number,7,"x")+average_radius,row(column_number,7,"y")+average_radius);
		eight.setCorn(cell(column_number,8,"x")-average_radius,row(column_number,8,"y")-average_radius,cell(column_number,8,"x")+average_radius,row(column_number,8,"y")+average_radius);
		nine.setCorn(cell(column_number,9,"x")-average_radius,row(column_number,9,"y")-average_radius,cell(column_number,9,"x")+average_radius,row(column_number,9,"y")+average_radius);
	}

	public int cell(int nu,int opt,String axis) throws CellsWrongDetection{
		//System.out.println("Cells "+cells.size()+" getting Value "+getind(nu,opt,axis));
		if(cells.size() <= getind(nu,opt,axis)) throw new CellsWrongDetection(cells.size(),getind(nu,opt,axis));
		return cells.get(getind(nu,opt,axis)).getAsInt();
	}
	public int row(int nu,int opt,String axis){
		//System.out.println(cells.size()+" getting Value "+getind(nu,opt,axis));
		return rows.get(getind(nu,opt,axis)).getAsInt();
	}

	public boolean[] viewFilled()
	{
		return new boolean[] {zero.isBlack(),one.isBlack(),two.isBlack(),three.isBlack(),four.isBlack(),five.isBlack(),six.isBlack(),seven.isBlack(),eight.isBlack(),nine.isBlack()};
	}
	/*public String getResult(){
		boolean[] allinfo = viewFilled();
		for (int i = 0; i < allinfo.length; i++) {
			if(allinfo[i]){
				return ((i+1) == 1)? "1":((i+1) == 2)? "2":((i+1) == 3)? "3":((i+1) == 4)? "4":((i+1) == 5)? "5":((i+1) == 6)?"6":((i+1) == 7)? "7":((i+1) == 8)?"8":((i+9) == 9)?"9":"skip";
			}
		}
		return "skip";
	}*/

	public String getResult(){
		boolean[] allinfo = viewFilled();
		for (int i = 0; i < allinfo.length; i++) {
			if(allinfo[i]){			
					return ((i) == 0)? "0":((i) == 1)? "1":((i) == 2)? "2":((i) == 3)? "3":((i) == 4)? "4":((i) == 5)? "5":((i) == 6)?"6":((i) == 7)? "7":((i) == 8)?"8":((i) == 9)?"9":"skip";
			}
		}
		return "skip";
	}



}
