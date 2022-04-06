// Peterson's algorithm is a concurrent programming
// algorithm for mutual exclusion that allows two
// processes to share a single-use resource without
// conflict, using only shared memory for communication.
// It was formulated by Gary L. Peterson in 1981.
// The algorithm was later generalized for more than
// two processes.

// Two processes use a respective "flag" to
// indicate that they want to enter a critical
// section. However using just that could lead to
// a deadlock. So they use a tie-breaker "turn" to
// indicate whose turn it is to wait. So, each
// process says it wants to enter CS but also that
// it is its turn to wait. In the end, a process
// only waits if the other process wants to enter
// CS as well as it is its own turn to wait. This
// tie-breaker prevents deadlock.

class Main {
    static boolean[] flag = {false, false};
    static int turn = 0;
    static int N = 4;
    // flag: ith process wants to enter CS?
    // turn: whose turn to enter CS
    // N: number of loops


    // 1. I want to enter CS.
    // 2. Its the other process' turn.
    // 3. I wait if you want too and your turn.
    // 4. I enter CS (sleep).
    // 5. I am done with CS.
    static Thread process(int i) {
        return new Thread(() -> {
            int j = 1 - i;
            for (int n=0; n<N; n++) {
                log(i+": want CS"); // LOCK
                flag[i] = true; // 1
                turn = j;       // 2
                while (flag[j] && turn == j) Thread.yield(); // 3

                log(i+": in CS"+n);
                sleep(1000 * Math.random()); // 4

                log(i+": done CS"); // UNLOCK
                flag[i] = false; // 5
            }
        });
    }


    public static void main(String[] args) {
        try {
            log("Starting 2 processes (threads) ...");
            Thread p0 = process(0);
            Thread p1 = process(1);
            p0.start();
            p1.start();
            p0.join();
            p1.join();
        }
        catch (InterruptedException e) {}
    }

    static void sleep(double t) {
        try { Thread.sleep((long)t); }
        catch (InterruptedException e) {}
    }

    static void log(String x) {
        System.out.println(x);
    }
}