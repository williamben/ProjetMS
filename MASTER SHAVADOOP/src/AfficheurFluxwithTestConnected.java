import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


class AfficheurFluxwithTestConnected implements Runnable {

	private final InputStream inputStream;
	static String line1;
	public static boolean isconnected;

	AfficheurFluxwithTestConnected(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	private BufferedReader getBufferedReader(InputStream is) {
		return new BufferedReader(new InputStreamReader(is));
	}

	@Override
	public void run() {
		BufferedReader br = getBufferedReader(inputStream);
		String ligne = "";
		this.isconnected=false;
		try {

			while ((ligne = br.readLine()) != null) {
				if(ligne.contains("OK")){
					this.isconnected=true;
				}


			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}


