package Test;

public class XpathPrint {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		////p[text()='brian@panoramatrack.com']
		String email= "brian@panoramatrack.com";
		String XPATH= "//p[text()='"+email+"']";
		System.out.println(XPATH);

	}

}
