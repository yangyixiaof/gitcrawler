
public class TestB {

	int a = -1;
	int[] t = {1,2,3,4,5};
	
	public void foo() {
		int y0;
		int y1 = 1;
		int y2 = 1;
		int y3 = 4;
		int y4 = y1 + y2 + y3;
		// this.a = 100;
		t[y1 + y2 + y2 * y1 / y2 + y1] += 10;
	}
}