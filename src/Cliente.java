import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cliente extends Thread {
	private Semaphore comendo;
	private Semaphore servindo;
	private Semaphore espera;
	private Long startedTime = System.currentTimeMillis();
	
	public Cliente(Semaphore comendo, Semaphore servindo, Semaphore espera) throws InterruptedException {
		this.comendo = comendo;
		this.servindo = servindo;
		this.espera = espera;
		
		start();
		
		join();
	}
	
	@Override
	public void run() {
		Random r = new Random();
		
		try {
			// ESPERANDO PARA ENTRAR
			Thread.sleep(40);
			
			if((int) Main.getFullTime() >= 3000) {
				Main.setDesistencia(Main.getDesistencia() + 1);
				System.out.println("Desistência: " + Main.getDesistencia());
			} else {
				espera.acquire();
				System.out.println("Na lista de espera");
				
				servindo.acquire();
				espera.release();
				System.out.println("Se servidndo");
				Thread.sleep(r.nextInt(6 - 5) + 5);
				
				comendo.acquire();
				servindo.release();
				System.out.println("Comendo prato principal");
				Thread.sleep(r.nextInt(30 - 25) + 25);
				System.out.println("Comendo sobremesa");
				Thread.sleep(r.nextInt(6 - 5) + 5);
				System.out.println("Saindo do Restaunte");
				comendo.release();
				
				Main.setFullTime(Main.getFullTime() + (System.currentTimeMillis() - startedTime));
			}
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

}
