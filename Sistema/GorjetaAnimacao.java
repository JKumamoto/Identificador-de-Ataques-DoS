//package gorjeta;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JDialogFis;

public class GorjetaAnimacao 
{
    public static void main(String args[]) throws Exception 
    {          
        double valServico = 0.0, valRefeicao = 0.0, valGorjeta = 0.0;
        
        // Load from 'FCL' file
        String fileName = "fcl/gorjeta.fcl";
        FIS fis = FIS.load(fileName, true);

        // Create a plot
        JDialogFis jdf = new JDialogFis(fis, 800, 600);
        
        File arquivo = new File("files/saida.csv");
        //File[] arquivos = arquivo.listFiles();
 
        //Cria rquivo para escrita
        FileWriter fw = new FileWriter(arquivo, false);
        BufferedWriter bw = new BufferedWriter(fw);

        // Set different values for 'refeicao' and 'servico'. Evaluate the system
        // and show variables
        while (valServico < 9)
        {
            valServico  =  valServico + 0.1;
            valRefeicao = valRefeicao + 0.1;
            BigDecimal bdServico = new BigDecimal(valServico).setScale(3, RoundingMode.HALF_EVEN);
            BigDecimal bdRefeicao = new BigDecimal(valRefeicao).setScale(3, RoundingMode.HALF_EVEN);
            valServico = bdServico.doubleValue();
            valRefeicao = bdRefeicao.doubleValue();            
            
            // Evaluate system using these parameters
            fis.getVariable("servico").setValue(valServico);
            fis.getVariable("refeicao").setValue(valRefeicao);
            fis.evaluate();

            valGorjeta = fis.getVariable("gorjeta").getValue();
            BigDecimal bdGorjeta = new BigDecimal(valGorjeta).setScale(4, RoundingMode.HALF_EVEN);
            valGorjeta = bdGorjeta.doubleValue(); 
            
            // Print result & update plot
            System.out.println("Servico: " + valServico + " Refeição: " + valRefeicao + " Gorjeta: " + valGorjeta);
            jdf.repaint();

            try 
            {
                String gravar;
                gravar = String.valueOf(valServico).replace('.', ',');
                bw.write(gravar);
                bw.write(";");
                gravar = String.valueOf(valRefeicao).replace('.', ',');
                bw.write(gravar);
                bw.write(";");
                gravar = String.valueOf(valGorjeta).replace('.', ',');
                bw.write(gravar);
                bw.newLine();

            } catch (IOException ex) {
            ex.printStackTrace();
            }

            // Small delay
            Thread.sleep(100);
        }
        bw.close();
        fw.close();
        System.out.println("Final da execução do loop da geração dos valores do controlador fuzzy.");            
    }
}
