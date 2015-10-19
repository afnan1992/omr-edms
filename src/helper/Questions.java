package helper;
import java.util.ArrayList;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_core.Point;

import com.google.gson.JsonArray;
import com.omr.exceptions.CellsWrongDetection;

import config.Config;

public class Questions extends Config{
	public ArrayList<Question> questions;
	public int total,unit;
	private JsonArray cells,rows;
	IplImage image;
	Point orig;
	private int avgr;
	/*
	 * Constructors
	 */
	public Questions(int total,int unit,IplImage image,Point orig, JsonArray tcols, JsonArray trows,int avgr){
		this.total=total;
		this.unit=unit;
		this.image=image.clone();
		this.orig = orig;
		this.cells = tcols;
		this.rows = trows;
		this.avgr = avgr;
		questions = new ArrayList<Question>(); 
	}
	/*
	 * Methods
	 */
	public void addQuestion(int number,int options,String imgname) throws CellsWrongDetection{
		Question q = new Question(number, options, image,imgname, unit,orig,cells,rows,avgr);
		questions.add(q);
	}
	public void addAllQuestions(int[] options,String imgname) throws CellsWrongDetection{	
		//total=58;
		int [] temp_options=new int [63];
		for(int k=0;k<options.length;k++)
			temp_options[k]=options[k];
		
			
		for(int j=42;j<63;j++)
		{
			temp_options[j]=5;
		}
		for (int i = 0; i < total; i++) {
           // options[i]=5;
			Question q=new Question(i, temp_options[i], image, imgname, unit, orig, cells, rows, avgr);
			//new Question(i, options[i], image, imgname, unit, orig,cells,rows,avgr)
			questions.add(q);		
		}
	}
	public void ClearQuestionList()
	{
		questions.clear();
	}
	
	public Question getQuestion(int q){
		return questions.get(q);
	}
	public String[] getAllOptions(){
		int listsize = questions.size();
		String[] ret = new String[listsize];
		for(int i=0; i<listsize; i++){
			/*if(i==20 || i==21 || i==22 || i==23 || i==24 || i==25 || i==26 || i==27 || i==28|| i==29 || i==30 || i==31 || i==32 || i==33 || i==34 || i==54 || i==55 || i==56 || i==57)
				continue;
			else*/	
			    ret[i]=questions.get(i).getResult();
		}
		return ret;
	}
	public IplImage drawQgrid(){
		int listsize = questions.size();
		for(int i=0; i<listsize; i++){
			questions.get(i).drawmaps();
		}
		return image;
	}
	
	public String[] getGrades(){
		return getAllOptions();
	}
	public IplImage getQgiven(){
		return image;
	}
}
