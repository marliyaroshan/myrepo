import java.io.IOException;

public class Hello {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		System.out.println("pop");
		long elapsedTime = System.currentTimeMillis() - start;

		System.out.println("elapsedTime" + elapsedTime);
	}
}
