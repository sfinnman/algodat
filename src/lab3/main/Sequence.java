package lab3.main;

public class Sequence {
	String s;
	
	public String solve(Sequence seq){
		String s1 = " " + s;
		String s2 = " " + seq.s;
		ArrowCell[][] fm = new ArrowCell[s1.length()][s2.length()];
		genfm(fm, s1, s2, 0);
		int x = s1.length()-1;
		int y = s2.length()-1;
		String s1b = "";
		String s2b = "";
		while (x != 0 || y!= 0) {
			ArrowCell ac = fm[x][y];
			s1b = s1.substring(x + ac.x+1, x+1) + s1b;
			s2b = s2.substring(y + ac.y+1, y+1) + s2b;
			if (ac.x == 0) {
				s1b = "-" + s1b;
			} else if (ac.y == 0) {
				s2b = "-" + s2b;
			}
			x += ac.x;
			y += ac.y;
			
		}
		return s1b+"\n"+s2b;
	}
	
	private void genfm(ArrowCell[][] fm, String s1, String s2, int p) {
		if (p == 0) {
			for (int i = 0; i<s1.length(); i++) {
				fm[i][0] = new ArrowCell().setVal(-4*i).setX(-1);
			}
			for (int i = 0; i<s2.length(); i++) {
				fm[0][i] = new ArrowCell().setVal(-4*i).setY(-1);
			}
			fm[0][0] = new ArrowCell().setX(0).setY(0).setVal(0);
			genfm(fm, s1, s2, 1);
		} else {
			for(int i = p; i<s1.length(); i++) {
				fm[i][p] = genCell(fm[i-1][p].val, fm[i-1][p-1].val, fm[i][p-1].val, s1.substring(i,i+1) + s2.substring(p,p+1));
			}
			for(int i = p; i<s2.length(); i++) {
				fm[p][i] = genCell(fm[p-1][i].val, fm[p-1][i-1].val, fm[p][i-1].val, s1.substring(p,p+1) + s2.substring(i,i+1));
			}
			if (p+1 < s1.length() && p+1<s2.length()){
				genfm(fm, s1, s2, p+1);
			}
		}
	}
	
	private ArrowCell genCell(int left, int diag, int top, String comb){
		int l = -1;
		int t = -1;
		int w = diag + ResourceLoader.sim.get(comb);
		if (top - 4>w) {
			w = top - 4;
			l = 0;
		}
		if (left - 4>w) {
			w = left - 4;
			t = 0;
			l = -1;
		}
		return new ArrowCell().setVal(w).setX(l).setY(t);
	}
	
	public static class ArrowCell{
		public int val;
		public int x;
		public int y;
		
		public ArrowCell setVal(int val) {
			this.val = val;
			return this;
		}
		
		public ArrowCell setX(int x) {
			this.x = x;
			return this;
		}
		
		public ArrowCell setY(int y) {
			this.y = y;
			return this;
		}
	}
	
}
