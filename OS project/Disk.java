public class Disk {

    String[] diskSpace = new String[2048];
    private int count = 0;

    public void saveData(String hex) {
        diskSpace[count] = hex;
        count++;
    }

    public String loadData(int index) {
        String hex = diskSpace[index];
        return hex;
    }

    public static synchronized Disk getInstance() {
        if (creflo == null) {
            creflo = new Disk();
        }
        return creflo;
    }
    private static Disk creflo;
}



