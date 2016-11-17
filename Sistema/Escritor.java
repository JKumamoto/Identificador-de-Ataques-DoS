import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Escritor{

	private File arquivo;
	private FileWriter fw;
	private BufferedWriter bw;

	public Escritor(String filename){
		try{
			arquivo=new File(filename);
			fw = new FileWriter(arquivo, false);
			bw = new BufferedWriter(fw);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private void escrever(String s) throws IOException{
		bw.write(s);
		bw.write(";");
	}

	public void write(String brt, String ent, String hst, String tc){
		try{
			escrever(brt.replace('.', ','));
			escrever(ent.replace('.', ','));
			escrever(hst.replace('.', ','));
			bw.write(tc.replace('.', ','));
			bw.newLine();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void close(){
		try{
			bw.close();
			fw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
