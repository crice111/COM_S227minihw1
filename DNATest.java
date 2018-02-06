package mini1;

public class DNATest {

	public static void main(String[] args) {
	 DNASequence a = new DNASequence("GxAyAzCw");
	 DNASequence b = new DNASequence("TTCGCATCTATCT");
	 System.out.println(b.isSubsequence(a));

	}

}
