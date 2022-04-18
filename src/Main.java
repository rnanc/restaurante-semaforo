import java.util.concurrent.Semaphore;

public class Main {
	private static long fullTime;
	private static long desistencia;

	public static void main(String[] args) throws InterruptedException {
		
		Semaphore comendo = new Semaphore(48);
		Semaphore servindo = new Semaphore(10);
		Semaphore espera = new Semaphore(12);
		
		
		for (int i = 0; i < 500; i++) {
			new Cliente(comendo, servindo, espera);
			System.out.println("Total de unidades: " + fullTime);
		}
		
		
	}
	
	public static long getDesistencia() {
		return desistencia;
	}

	public static void setDesistencia(long desistencia) {
		Main.desistencia = desistencia;
	}

	public static long getFullTime() {
		return fullTime;
	}

	public static void setFullTime(long fullTime) {
		Main.fullTime = fullTime;
	}
}
