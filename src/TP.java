public class TP {
    private int[] array = new int[64];

    private RAM ram;

    public TP(int numMarcos, RAM ram) {
        this.ram = ram;
        for (int i = 0; i < array.length; i++) {
            if (i < numMarcos) {
                array[i] = i;
            } else {
                array[i] = -1;
            }
        }
    }
}
