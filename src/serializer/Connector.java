package serializer;

import java.io.*;

/**
 * Created by Denis on 12/20/2014.
 * Class-writer, writes and reads collections
 * from disk
 */
public class Connector {
    ObjectInputStream ois;
    ObjectOutputStream oos;
    public void writeObj(AirportDrive mc, String outputPath) throws IOException {
        oos = new ObjectOutputStream(new FileOutputStream(outputPath));
        oos.writeObject(mc);
        oos.flush();
        oos.close();
    }

    public AirportDrive readObj(String filePath) throws IOException, ClassNotFoundException {
        ois = new ObjectInputStream(new FileInputStream(filePath));
        return (AirportDrive)ois.readObject();
    }

    public void close()throws IOException{
        ois.close();
    }
}
