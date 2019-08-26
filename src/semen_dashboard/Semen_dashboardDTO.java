package semen_dashboard;
import java.util.*; 


public class Semen_dashboardDTO {

	public String[] top_5_progeny_bulls = new String[5];
	public int[] top_5_progeny_bulls_calfs = new int[5];
	
	public String[] top_5_progeny_centers =  new String[5];
	public int[] top_5_progeny_centers_calfs =  new int[5];
	
	public int apa_semen_collection = 1;
	public int actual_semen_collection = 0;
	public double semen_collection_percentage;
	
	public int apa_ai = 1;
	public int actual_ai = 0;
	public double ai_percentage = 0;
	
	public int apa_progeny = 1;
	public int actual_progeny = 0;
	public double progeny_percentage = 0;
	
	public int apa_cb = 1;
	public int actual_cb = 0;
	public double cb_percentage = 0;
	
	
	public int d_Text_1_0_0 = 0;
	public int d_Text_2_0_1 = 0;
	public int d_Text_3_0_2 = 0;

	public int d_Text_1_1_0 = 0;
	public int d_Text_2_1_1 = 0;

	public int d_Text_1_2_0 = 0;
	public int d_Text_2_2_1 = 0;


    @Override
	public String toString() {
            return "$Semen_dashboardDTO[" +
            " apa_semen_collection = " + apa_semen_collection +
            " apa_ai = " + apa_ai +
            " apa_progeny = " + apa_progeny +
            " apa_cb = " + apa_cb +
            " actual_semen_collection = " + actual_semen_collection +
            " actual_ai = " + actual_ai +
            " actual_progeny = " + actual_progeny +
            " actual_cb = " + actual_cb +
            "]";
    }
	

}