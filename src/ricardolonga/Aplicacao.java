package ricardolonga;

import java.io.File;

/**
 * Esta classe está com muitas responsabilidades. E se for preciso uma nova reação à mudança do arquivo?
 * Vamos tentar melhorar isso...
 */
public class Aplicacao implements Runnable {
    
    private File file;
    
    private long lastUpdate;

    public static void main(String[] args) {
        new Thread(new Aplicacao()).start();
    }
    
    public Aplicacao() {
        file = new File("config.properties");
    }

    @Override
    public void run() {
        monitorarArquivo();
    }

    private void monitorarArquivo() {
        lastUpdate = file.lastModified();
        
        while (true) {
            if (lastUpdate != file.lastModified()) {
                recarregarConfiguracoes();
                recarregarContexto();
                lastUpdate = file.lastModified();
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void recarregarConfiguracoes() {
        System.out.println("Recarregando as configuraçoes...");
    }

    private void recarregarContexto() {
        System.out.println("Recarregando o contexto do framework...");
    }

}


