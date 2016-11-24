import java.util.Scanner;
import java.io.File;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JDialogFis;

public class DOS_ataque{

    public static void main(String args[]) throws Exception{
		double bitrate, entropia, hurst, tc;
        
        String fileName = "fcl/dos_attack.fcl";
        FIS fis = FIS.load(fileName, true);
        JDialogFis jdf = new JDialogFis(fis, 800, 600);
        
		Escritor saida=new Escritor("files/saida.csv");
		Scanner scan=new Scanner(new File("files/entrada.txt"));

		while(scan.hasNext()){
			bitrate=Double.parseDouble(scan.next());
			entropia=Double.parseDouble(scan.next());
			hurst=Double.parseDouble(scan.next());

            // Evaluate system using these parameters
            fis.getVariable("hst").setValue(hurst);
            fis.getVariable("ent").setValue(entropia);
            fis.getVariable("brt").setValue(bitrate);
            fis.evaluate();

            tc = fis.getVariable("tc").getValue();
            
            // Print result & update plot
            System.out.println("Bitrate: " + bitrate + " Entropia: " + entropia + " Hurst: " + hurst + " Classe: " + tc);
            jdf.repaint();

			saida.write(bitrate+"", entropia+"", hurst+"", tc+"");

			Thread.sleep(1000);
		}
		saida.close();
        System.out.println("Final da execução do loop da geração dos valores do controlador fuzzy.");            
    }
}
