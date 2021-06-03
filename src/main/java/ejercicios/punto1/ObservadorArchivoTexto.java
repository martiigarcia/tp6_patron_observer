package ejercicios.punto1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;

public class ObservadorArchivoTexto implements ObservadorInterface{

    private File file;

    public ObservadorArchivoTexto(String path){
        this.file = new File(path);
    }
    @Override
    public void notificar(String temperatura) {

        try{

            Writer writer = new FileWriter(file, true);
            writer.write("Fecha: "+ LocalDate.now().toString()+
                    ", Temperatura: "+ temperatura+"\n");
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar en el archivo.", e);
        }

    }
}
