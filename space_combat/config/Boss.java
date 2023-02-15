import java.util.Random;
import java.util.Scanner;

class Player {
	
	public static double distance(int x, int y, int x2, int y2) {
		return Math.sqrt((x-x2)*(x-x2) + (y-y2)*(y-y2));
	}
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        	
        int[] cross;
        
        while (true) {
        	int x = scanner.nextInt();
        	int y = scanner.nextInt();
        	int nb_cross = scanner.nextInt();
        	cross = new int[nb_cross*3];
        	for(int i = 0;i < nb_cross;++i) {
        		cross[i] = scanner.nextInt();
        		cross[i+1] = scanner.nextInt();
        		cross[i+2] = scanner.nextInt();
        		
        	}
        	
        	int ind = 0;
        	int i = 0;
        	double min_d = 2000000;
        	while(i < nb_cross) {
        		double d = distance(x, y, cross[i], cross[i+1]);
        		if(d < min_d) {
        			ind = i;
        			min_d = d;
        		}
        		
        		i+=3;
        	}
        	
        	
        	System.out.println(cross[ind] + " " + cross[i+1]);
        }
    }
}
