import textRead.read;
public class main {
    public static void main(String[] args){
        String filePath = read.getPath();
        String dataDate = read.getDate();
        read.readText(filePath, dataDate);
    }
}
