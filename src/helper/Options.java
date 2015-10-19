package helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.omr.exceptions.UnableToDetectOptions;

public class Options {
	private List<JsonObject> options,
	grabedopts,ysorted,xsorted;
	private int[] arx,ary;
	private JsonArray rows,cols;
	private List<String> columnsList;
	public List<String> getColumnsList()
	{
		return columnsList;
	}
	public JsonArray getrows(){
		return rows;
	}
	public JsonArray getcols(){
		return cols;
	}
	public Options(List<JsonObject> gotopts,int[] arx,int[] ary,int ymaxoff,int yminoff,int xmaxoff,int xminoff,int xmaxgap,int xmingap ){
		grabedopts = new ArrayList<JsonObject>();
		ysorted = new ArrayList<JsonObject>();
		xsorted = new ArrayList<JsonObject>();
		options = new ArrayList<JsonObject>();
		this.arx = arx.clone();
		this.ary = ary.clone();
		grabedopts.addAll(gotopts);
	}
	public void organise(int xindex) throws UnableToDetectOptions
	{
		ysorted = sortby(grabedopts,removeDuplicates(ary),"y");
		xsorted = sortby(grabedopts,removeDuplicates(arx),"x");
		rows = indexes(ysorted, "y",18);
		cols = indexes(xsorted, "x",18);
		

		System.out.println("incRows ind "+rows.size()+" data "+rows.toString());
		System.out.println("incCols ind "+cols.size()+" data "+cols.toString());
		
		if(rows.size() < 10 ) throw new UnableToDetectOptions();
		if(cols.size() !=19 ) throw new UnableToDetectOptions();
	}
	
	public void organise(int xindex,int questions) throws UnableToDetectOptions{

		ysorted = sortby(grabedopts,removeDuplicates(ary),"y");
		xsorted = sortby(grabedopts,removeDuplicates(arx),"x");
		rows = indexes(ysorted, "y",questions);
		cols = indexes(xsorted, "x",questions);
		

		System.out.println("incRows ind "+rows.size()+" data "+rows.toString());
		System.out.println("incCols ind "+cols.size()+" data "+cols.toString());
		
		if(rows.size() < 34 ) throw new UnableToDetectOptions();
		if(cols.size() !=15 ) throw new UnableToDetectOptions();
	

		/*if(cols.size() != 15){
			System.out.println("column size was not 15");
			cols = hardfill(xindex);
		}*/
		/*if(cols.size() !=11 && questions==42)
		{
			cols=hardfill(xindex,questions);
		}
		if(cols.size() !=13 && questions==57) 
		{
			cols=hardfill(xindex,questions);
		}*/

		
	}
	private JsonArray hardfill(int xindex,int questions){
		int offset = xindex + 105;
		JsonObject dummy = new JsonObject();
		JsonArray l = new JsonArray();
		if(questions==57)
		{
			dummy.addProperty("0", offset);
			dummy.addProperty("1", dummy.get("0").getAsInt() + 44);
			dummy.addProperty("2", dummy.get("1").getAsInt() + 44);
			dummy.addProperty("3", dummy.get("2").getAsInt() + 48);
			dummy.addProperty("4", dummy.get("3").getAsInt() + 200);
			dummy.addProperty("5", dummy.get("4").getAsInt() + 44);
			dummy.addProperty("6", dummy.get("5").getAsInt() + 44);
			dummy.addProperty("7", dummy.get("6").getAsInt() + 48);
			dummy.addProperty("8", dummy.get("7").getAsInt() + 200);
			dummy.addProperty("9", dummy.get("8").getAsInt() + 44);
			dummy.addProperty("10", dummy.get("9").getAsInt() + 44);
			dummy.addProperty("11", dummy.get("10").getAsInt() + 48);
			dummy.addProperty("12", dummy.get("11").getAsInt() + 46);

			for (int i = 0; i < 14; i++) {
				l.add(dummy.get(""+i));
			}
		}
		else
		{
			dummy.addProperty("0", offset);
			dummy.addProperty("1", dummy.get("0").getAsInt() + 44);
			dummy.addProperty("2", dummy.get("1").getAsInt() + 44);
			dummy.addProperty("3", dummy.get("2").getAsInt() + 248);
			dummy.addProperty("4", dummy.get("3").getAsInt() + 44);
			dummy.addProperty("5", dummy.get("4").getAsInt() + 44);
			dummy.addProperty("6", dummy.get("5").getAsInt() + 248);
			dummy.addProperty("7", dummy.get("6").getAsInt() + 44);
			dummy.addProperty("8", dummy.get("7").getAsInt() + 44);
			
			for (int i = 0; i < 9; i++) {
				l.add(dummy.get(""+i));
			}

		}

		return l;
	}

	private JsonArray hardfill(int xindex){
		int offset = xindex + 105;
		JsonObject dummy = new JsonObject();
		dummy.addProperty("0", offset);
		dummy.addProperty("1", dummy.get("0").getAsInt() + 44);
		dummy.addProperty("2", dummy.get("1").getAsInt() + 44);
		dummy.addProperty("3", dummy.get("2").getAsInt() + 48);
		dummy.addProperty("4", dummy.get("3").getAsInt() + 46);
		dummy.addProperty("5", dummy.get("4").getAsInt() + 154);
		dummy.addProperty("6", dummy.get("5").getAsInt() + 44);
		dummy.addProperty("7", dummy.get("6").getAsInt() + 44);
		dummy.addProperty("8", dummy.get("7").getAsInt() + 48);
		dummy.addProperty("9", dummy.get("8").getAsInt() + 46);
		dummy.addProperty("10", dummy.get("9").getAsInt() + 154);
		dummy.addProperty("11", dummy.get("10").getAsInt() + 44);
		dummy.addProperty("12", dummy.get("11").getAsInt() + 44);
		dummy.addProperty("13", dummy.get("12").getAsInt() + 48);
		dummy.addProperty("14", dummy.get("13").getAsInt() + 46);
		JsonArray l = new JsonArray();
		for (int i = 0; i < 15; i++) {
			l.add(dummy.get(""+i));
		}

		return l;
	}
	private JsonArray indexes(List<JsonObject> in,String axis,int Questions) {
		//System.out.println("Indexes With axis as "+axis);
		columnsList=new ArrayList<String>();
		JsonArray l = new JsonArray();
		
		for (int i = 0; i < in.size(); i++) {
			if(i+1 != in.size()){
				int sub = in.get(i+1).get(axis).getAsInt() -in.get(i).get(axis).getAsInt();
				//				System.out.println(in.get(i+1).get(axis).getAsInt() +"-"+in.get(i).get(axis).getAsInt()+" = "+sub);
				if(sub >=12){
					//System.out.println("ADDED"+sub);
					if(l.size() == 0){
						l.add(in.get(i).get(axis));
						l.add(in.get(i+1).get(axis));

					}else{
						l.add(in.get(i+1).get(axis));
						if(l.size()==21 && axis.contains("y"))
						{
							JsonObject object=new JsonObject();
							object.addProperty("0",in.get(i+1).get(axis).getAsInt()+42);
							object.addProperty("1",in.get(i+1).get(axis).getAsInt()+84);
							object.addProperty("2",in.get(i+1).get(axis).getAsInt()+126);
							object.addProperty("3",in.get(i+1).get(axis).getAsInt()+168);
							object.addProperty("4",in.get(i+1).get(axis).getAsInt()+210);
							object.addProperty("5",in.get(i+1).get(axis).getAsInt()+252);
							object.addProperty("6",in.get(i+1).get(axis).getAsInt()+294);
							object.addProperty("7",in.get(i+1).get(axis).getAsInt()+336);
							object.addProperty("8",in.get(i+1).get(axis).getAsInt()+378);
							object.addProperty("9",in.get(i+1).get(axis).getAsInt()+410);
							object.addProperty("10",in.get(i+1).get(axis).getAsInt()+452);
							object.addProperty("11",in.get(i+1).get(axis).getAsInt()+494);
							object.addProperty("12",in.get(i+1).get(axis).getAsInt()+536);
							
							l.add(object.get("0"));
							l.add(object.get("1"));
							l.add(object.get("2"));
							l.add(object.get("3"));
							l.add(object.get("4"));
							l.add(object.get("5"));
							l.add(object.get("6"));
							l.add(object.get("7"));
							l.add(object.get("8"));
							l.add(object.get("9"));
							l.add(object.get("10"));
							l.add(object.get("11"));
							l.add(object.get("12"));
						}
					}

				}
			}
		}
		return l;
	}
	public void addoption(JsonObject opt){
		options.add(opt);
	}
	private List<JsonObject> sortby(List<JsonObject> points, int[] ar,String index) {
		List<JsonObject> ret = new ArrayList<JsonObject>();
		for (int i = 0; i < ar.length; i++) {
			if(ar[i]!=0){
				for (int j = 0; j < points.size(); j++) {
					if(points.get(j).get(index).getAsInt() == ar[i]){
						ret.add(points.get(j));
					}
				}
			}
		}
		return ret;
	}

	public static int[] removeDuplicates(int[] arr) {
		Set<Integer> alreadyPresent = new HashSet<Integer>();
		int[] whitelist = new int[0];
		for (int nextElem : arr) {
			if (!alreadyPresent.contains(nextElem)) {
				whitelist = Arrays.copyOf(whitelist, whitelist.length + 1);
				whitelist[whitelist.length - 1] = nextElem;
				alreadyPresent.add(nextElem);
			}
		}
		Arrays.sort(whitelist);
		return whitelist;
	}
}
