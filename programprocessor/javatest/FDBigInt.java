package cn.yyx.research.language.programprocessor;

public class FDBigInt {
	
	int nWords; // number of words used
	/*int data[]; // value: data[0] is least significant
	
	private FDBigInt(int[] d, int n) {
		data = d;
		nWords = n;
	}
	
	public FDBigInt add(FDBigInt other) {
		int i;
		int a[], b[];
		int n, m;
		long c = 0L;
		// arrange such that a.nWords >= b.nWords;
		// n = a.nWords, m = b.nWords
		if (this.nWords >= other.nWords) {
			a = this.data;
			n = this.nWords;
			b = other.data;
			m = other.nWords;
		} else {
			a = other.data;
			n = other.nWords;
			b = this.data;
			m = this.nWords;
		}
		int r[] = new int[n];
		for (i = 0; i < n; i++) {
			c += (long) a[i] & 0xffffffffL;
			if (i < m) {
				c += (long) b[i] & 0xffffffffL;
			}
			r[i] = (int) c;
			c >>= 32; // signed shift.
		}
		if (c != 0L) {
			// oops -- carry out -- need longer result.
			int s[] = new int[r.length + 1];
			JVM.unchecked_int_arraycopy(r, 0, s, 0, r.length);
			s[i++] = (int) c;
			return new FDBigInt(s, i);
		}
		return new FDBigInt(r, i);
	}*/
	
}