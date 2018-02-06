package mini1;

/**
 * Simple model of a strand of DNA.  An instance of this class encapsulates
 * a string of characters.  A character is called <em>valid</em> if it
 * is one of the the letters 'A', 'C', 'G', 'T' (uppercase) and a DNASequence
 * object is called <em>valid</em> if all its characters are valid.
 * The characters 'A' and 'T' are said to be <em>complements</em> of each 
 * other and likewise the characters 'C' and 'G' are complements.  
 * Complementary characters are said to <em>bond</em> with each other.
 * The main operations on this class are for the purpose of determining the 
 * number and locations of bonds that one sequence can form with another
 * depending how they are aligned (shifted) with each other.
 * <p>
 * However, it is entirely possible to construct a DNASequence object
 * containing invalid characters, and all operations should work
 * correctly for arbitrary characters.  Note that a character other than
 * 'A', 'C', 'G', or 'T' is never considered to bond with another character.
 */
public class DNASequence
{
  /**
   * String of data for this sequence. 
   */
  private String data;
  
  /**
   * Constructs a DNASequence object with the given string of data;
   * this constructor does not check whether the given string
   * is valid (see the method allValid).
   * @param givenData
   *   string of characters for this DNASequence
   */
  public DNASequence(String givenData)
  {
    data = givenData;
  }
  
  /**
   * Returns a String representing the data for this
   * DNASequence.
   * @return
   *   the characters in this DNASequence
   */
  public String toString()
  {
    return data;
  }
  
  /**
   * Determines whether all characters in this sequence
   * are valid ('A', 'G', 'C', or 'T' in uppercase only).
   * @return
   *   true if all characters are valid, false otherwise
   */
  public boolean allValid()
  {
	boolean valid = true;

    for(int i=0; i < data.length();i++) {
    	if(data.charAt(i)!='G') {
    		if(data.charAt(i)!='A') {
    			if(data.charAt(i)!='C') {
    				if(data.charAt(i)!='T') {
    		    		valid=false;
    		    	}
    	    	}
        	}
    	}
    }
    return valid;
  }
  
  /**
   * Removes all invalid characters from this DNASequence.  For example, 
   * if this object's data is the string <code>"TaGxy*!  Cz"</code>, 
   * after calling this method, the data is <code>"TGC"</code>.
   */
  public void fix()
  {
    String a="";
    for (int i =0; i <data.length();i++) {
    	if (data.charAt(i)=='G') {
    		a=(a+'G');
    	}
    	if (data.charAt(i)=='A') {
    		a=(a+'A');
    	}
    	if (data.charAt(i)=='C') {
    		a=(a+'C');
    	}
    	if (data.charAt(i)=='T') {
    		a=(a+'T');
    	}
    }
    data=a;
  }
  
  /**
   * Determines whether the given sequence is a subsequence
   * of this one.  A string t is a subsequence of another
   * string s if all characters of t can be found in s in the
   * same order.  Equivalently, string t is a subsequence of s
   * if t can be obtained by deleting some of the characters of s.
   * Invalid characters in the given string are ignored.
   * <p>
   * For example "TxxAA" is a subsequence of "CTyyGCACA" but 
   * not of "CAAT" nor of "TA".
   * @param other
   *   the given DNASequence
   * @return
   *   true if the given sequence is a subsequence of this one, 
   *   false otherwise
   */
  public boolean isSubsequence(DNASequence other)
  {
    boolean sub=false;
    int count=0;
    int track=0;
    this.fix();
    other.fix();
    String small=other.toString();
    if (small.length()>data.length()) {
    	return false;
    }
    for (int i=0;i<small.length();i++) {
    	for (int w=track;w<data.length();w++) {
    		if (small.charAt(i)==data.charAt(w)) {
    			count=count+1;
    			track=w+1;
    			w=data.length();
    		}
    	}
    }
    if (count==small.length()) {
    	sub=true;
    }
    return sub;
  }
  
  /**
   * Returns true if the two characters are complementary
   * ('A' with 'T' or 'C' with 'G').
   * @param c1
   *   potential character for a base pair
   * @param c2
   *   potential character for a base pair
   * @return
   *   true if the two characters are 'A' and 'T' or 'C' and 'G';
   *   false otherwise
   */
  public boolean willBond(char c1, char c2)
  {
    boolean bond=false;
    if (c1=='A'&c2=='T') {
    	bond=true;
    }
    if (c1=='T'&c2=='A') {
    	bond=true;
    }
    if (c1=='G'&c2=='C') {
    	bond=true;
    }
    if (c1=='C'&c2=='G') {
    	bond=true;
    }
    return bond;
  }
    
  /**
   * Replaces this object's data with its complement;
   * that is, 'A' is replaced with 'T' and so on.
   * Invalid characters are not modified.
   * For example, if the data for this sequence is "AGTT", after
   * this method is called the data would be "TCAA".  
   */
  public void complement()
  {
	    String a="";
	    for (int i =0; i <data.length();i++) {
	    	if (data.charAt(i)=='G') {
	    		a=(a+'C');
	    	}
	    	if (data.charAt(i)=='A') {
	    		a=(a+'T');
	    	}
	    	if (data.charAt(i)=='C') {
	    		a=(a+'G');
	    	}
	    	if (data.charAt(i)=='T') {
	    		a=(a+'A');
	    	}
	    	if(data.charAt(i)!='G') {
	    		if(data.charAt(i)!='A') {
	    			if(data.charAt(i)!='C') {
	    				if(data.charAt(i)!='T') {
	    		    		a=(a+data.charAt(i));
	    		    	}
	    	    	}
	        	}
	    	}
	    }
	    data=a;
  }
  
  /**
   * Returns the maximum possible number of bonds that can be formed
   * with this sequence when the given sequence is shifted left or 
   * right by any amount.
   * @param other
   *   the DNASequence to align with this one
   * @return
   *   maximum possible number of bonds 
   */
  public int findMaxPossibleBonds(DNASequence other)
  {
	  int max=0;
	  for (int i=-data.length();i<data.length();i++) {
		  if (max<countBondsWithShift(other,i)) {
			  max=countBondsWithShift(other,i);
		  }
		  
	  }
	  return max;
  }
  
  /**
   * Returns the number of bonds that are formed with this sequence
   * when the given sequence is shifted right by the given number
   * of spaces (where a negative number
   * represents a left shift).
   * @param other
   *   the DNASequence to align with this one
   * @param shift
   *   number of spaces to the right that the other sequence is shifted
   * @return
   *   number of bonds formed when the given sequence is 
   *   aligned with this one, with the given shift
   */
  public int countBondsWithShift(DNASequence other, int shift)
  {
	 int bonds=0;
	 String top=other.toString();
	 String bottom=data;
	 if (shift>=0) {
	 for (int i=0;i<top.length()&&i+shift<bottom.length();i++) {
		 if (willBond(top.charAt(i),bottom.charAt(i+shift))) {
			 bonds=bonds+1;
		 }
	 }
	 }
	  
	 if (shift<0) {
	 for (int i=0;i<bottom.length()&&i-shift<top.length();i++) {
		 if (willBond(bottom.charAt(i),top.charAt(i-shift))) {
			 bonds=bonds+1;
		 }
	 }
	 }
	  

	  return bonds;

  }
  
  /**
   * Returns a string showing which characters in this sequence
   * are bonded when the given sequence is shifted right by the given number
   * of spaces (where a negative number represents a left shift).
   * Non-matching characters are shown as dashes.  For example,
   * if this sequence is "ATATGC" and the given sequence is "TCC",
   * shifted right by 2, then this method returns "--A-G-"
   * @param other
   *   the sequence to which this one is being matched
   * @param shift
   *   the number of spaces the other sequence is shifted to the right
   * @return
   *   string indicating where matches occur
   */
  public String findBondsWithShift(DNASequence other, int shift)
  {
	 String out="";
	 String top=other.toString();
	 String bottom=data;
	 
	 for (int i=0;i<shift;i++) {
		 out=out+'-';
	 }
	 
	 
	 if (shift>=0) {
	 for (int i=0;i<top.length()&&i+shift<bottom.length();i++) {
		 if (willBond(top.charAt(i),bottom.charAt(i+shift))) {
			 out=out+bottom.charAt(i+shift);
		 }
		 else {
			 out=out+'-';
		 }
	 }
	 }
	  
	 if (shift<0) {
	 for (int i=0;i<bottom.length()&&i-shift<top.length();i++) {
		 if (willBond(bottom.charAt(i),top.charAt(i-shift))) {
			 out=out+bottom.charAt(i);
		 }
		 else {
			 out=out+'-';
		 }
	 }
	 }
	 
	 
	for (int i=top.length()+shift;i<bottom.length();i++) {
		out=out+'-';
	}
	 
	return out; 
  }
  }

	  
	 
	 
