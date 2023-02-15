import java.util.Scanner;

public class Agent1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] cross;
        
        int zonesz = 1920 / 5;
    	int[] zone = new int[6];
    	int[][] zonem = new int[5][2];
    	int[] nb_zone = new int[5];
    	int[] val_zone= new int[5];
    	for(int i = 0;i < 5;++i) {
    		zone[i] = zonesz * i;
    		System.err.println(zone[i]);
    	}
    	zone[5] = 1920;
        
        while (true) {
        	
        	int x = scanner.nextInt();
        	int y = scanner.nextInt();
        	int nb_cross = scanner.nextInt();
        	//System.err.println("nb " + nb_cross);
        	cross = new int[nb_cross*3];
        	int i = 0;
        	while(i < cross.length) {
        		cross[i] = scanner.nextInt();
        		//System.err.println("x " + cross[i]);
        		cross[i+1] = scanner.nextInt();
        		cross[i+2] = scanner.nextInt();
        		i+=3;
        	}
        	
        	//strategy 1 
        	/*int max_v = -1;
        	int i = 0;
        	while (i < cross.length) {
        		int v = cross[i+2];
        		if(v > max_v) {
        			max_v = v;
        			x = cross[i];
        			y = cross[i+1];
        		}
        		i+=3;
        	}*/
        	
        	for( i = 0;i < 5;++i) {
        		zonem[i][0] = 0;
        		zonem[i][1] = 0;
        		nb_zone[i] = 0;
        		val_zone[i] = 0;
        	
        	}
        	
        	
        	
        	i = 0;
        	while (i < cross.length) {
        		int zn = 0;
        		for(int j = 0;j < 5;++j) {
        			if(cross[i] >= zone[j] && cross[i] < zone[j+1]) {
        				//System.err.println(cross[i] + " " + zone[j] + " "+ zone[j+1]);
        				++nb_zone[j];
        				val_zone[j] += cross[i+2];
        				zonem[j][0] += cross[i];
        				zonem[j][1] += cross[i+1];
        			}
        		}
        		
        		i+=3;
        	}
        	
        	int max_c = -1;
        	int ind = 0;
        	for(int j = 0;j < 5;++j) {
        		zonem[j][0] = (int)((double)zonem[j][0] / (double)nb_zone[j]);
        		zonem[j][1] = (int)((double)zonem[j][1] / (double)nb_zone[j]);
        		
        		if(val_zone[j] > max_c) {
        			max_c = val_zone[j];
        			ind = j;
        		}
        		
        	}
            //System.err.println(String.valueOf((int)zonem[ind][0]) + " " + String.valueOf((int)zonem[ind][1]) + " JUMP");
            
            System.out.println(zonem[ind][0] + " " + zonem[ind][1] + " JUMP");
        }
    }
}
