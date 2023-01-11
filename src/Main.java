import java.util.Arrays;

public class Main {

    private int[][] menPref;
    private int[][] womenPref;
    private int[] menPartner;
    private int[] womenPartner;
    private boolean[] menEngaged;
    private boolean[] womenEngaged;

    public void StableMarriage(int[][] menPref, int[][] womenPref) {
        this.menPref = menPref;
        this.womenPref = womenPref;
        int n = menPref.length;
        this.menPartner = new int[n];
        Arrays.fill(menPartner, -1);
        this.womenPartner = new int[n];
        Arrays.fill(womenPartner, -1);
        this.menEngaged = new boolean[n];
        this.womenEngaged = new boolean[n];
    }

    public int[] solve() {
        while (true) {
            boolean done = true;
            for (int m = 0; m < menPref.length; m++) {
                if (!menEngaged[m]) {
                    done = false;
                    int w = menPref[m][0];
                    menEngaged[m] = true;
                    if (womenPartner[w] == -1) {
                        womenPartner[w] = m;
                    } else {
                        int m1 = womenPartner[w];
                        if (prefers(w, m, m1)) {
                            womenPartner[w] = m;
                            menEngaged[m1] = false;
                        }
                    }
                }
            }
            if (done) {
                return womenPartner;
            }
        }
    }

    private boolean prefers(int w, int m, int m1) {
        for (int i = 0; i < womenPref[w].length; i++) {
            if (womenPref[w][i] == m1) {
                return false;
            }
            if (womenPref[w][i] == m) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] menPref = { { 1, 0, 3, 4, 2 }, { 3, 1, 0, 2, 4 }, { 1, 4, 2, 3, 0 }, { 0, 3, 2, 1, 4 } };
        int[][] womenPref = { { 4, 0, 1, 3, 2 }, { 2, 1, 3, 0, 4 }, { 3, 0, 2, 4, 1 }, { 0, 4, 3, 2, 1 } };
        StableMarriage stableMarriage = new StableMarriage(menPref, womenPref);
        int[] womenPartner = stableMarriage.solve();
        System.out.println("Woman    Man");
        for (int i = 0; i < womenPartner.length; i++) {
            System.out.println(i + "        " + womenPartner[i]);
        }
    }
}
