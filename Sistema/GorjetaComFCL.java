//package gorjeta;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

public class GorjetaComFCL
{
   public static void main(String[] args) throws Exception 
   {
      // Load from 'FCL' file
      String fileName = "fcl/gorjeta.fcl";
      FIS fis = FIS.load(fileName, true);
      
      if (fis == null) 
      { // Error while loading
         System.err.println("Can't load file: '" + fileName + "'");
         return;
      }
      
      // Show variables
      FunctionBlock functionBlock = fis.getFunctionBlock(null);
      JFuzzyChart.get().chart(functionBlock);
 
      // Set inputs
      functionBlock.setVariable("servico", 3);
      functionBlock.setVariable("refeicao", 7);
      
      // Evaluate 
      fis.evaluate();
      
      // Show output variables chart
      Variable gorjeta = functionBlock.getVariable("gorjeta");
      JFuzzyChart.get().chart(gorjeta, gorjeta.getDefuzzifier(), true);
      System.out.println("GORJETA:" + fis.getVariable("gorjeta").getValue());
      
      //Print ruleSet
      System.out.println("\n====================================\n");
      System.out.println(fis);
   }
}
