public class RAM {

    String[] RAM = new String[1024];
    private int count = 0;

    public void saveData(String hex) {
        RAM[count] = hex;
        count++;
    }

    public void saveByIndex(String hex, int index) {
        RAM[index] = hex;
    }

    public String loadData(int index) {
        String hex = RAM[index];
        return hex;
    }

    public static synchronized RAM getInstance() {
        if (refyo == null) {
            refyo = new RAM();
        }
        return refyo;
    }
    private static RAM refyo;
}


