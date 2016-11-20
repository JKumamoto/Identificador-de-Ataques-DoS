import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JDialogFis;

public class DOS_ataque{

    public static void main(String args[]) throws Exception{
		double bitrate, entropia, hurst, tc;
        
        String fileName = "fcl/dos_attack.fcl";
        FIS fis = FIS.load(fileName, true);
        JDialogFis jdf = new JDialogFis(fis, 800, 600);
        
		Escritor saida=new Escritor("files/saida.csv");

			bitrate=5.33;
			entropia=0.0000058996;
			hurst=0.5264;

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

		saida.close();
        System.out.println("Final da execução do loop da geração dos valores do controlador fuzzy.");            
    }
}
