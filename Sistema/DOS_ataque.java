import java.util.Scanner;
import java.io.File;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JDialogFis;

public class DOS_ataque{

    public static void main(String args[]) throws Exception{
		double bitrate, entropia, hurst, tc;
		boolean traffic;

        System.out.println("O sistema ira ler 3 logs diferentes, e imprimir os resultados na saida padrão e nos arquivos de siada .csv, cada um correspondente aos cenarios descritos no artigo");            
        
        String fileName = "fcl/dos_attack.fcl";
        FIS fis = FIS.load(fileName, true);
        JDialogFis jdf = new JDialogFis(fis, 800, 600);

		String s="files/saida", entrada="files/entrada";

		for(int i=1; i<4; i++){
			Escritor saida=new Escritor(s+i+".csv");
			Scanner scan=new Scanner(new File(entrada+i+".txt"));
			System.out.println(i);

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
				System.out.print("Bitrate: " + bitrate + " Entropia: " + entropia + " Hurst: " + hurst + " Classe: " + tc);
				if(tc>0.5)
					System.out.println(" Ataque");
				else
					System.out.println(" Normal");

				jdf.repaint();

				saida.write(bitrate+"", entropia+"", hurst+"", tc+"");

				Thread.sleep(2000);
			}
			saida.close();
		}
        System.out.println("Final da execução do loop da geração dos valores do controlador fuzzy.");            
    }
}
