package com.example.concurrentAlgorithms;

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
            int j = 1 - i; // este calculo determina el calculo para el proximo hilo este ejemplo se restringe para dos hilos
            double time=0; // tiempo aleatorio en zona critica
            for (int n=0; n<N; n++) { //subproceso interno que realiza operaciones en la zona interta
                log(i+": want CS");   // bloqueo LOCK 
                flag[i] = true; // 1  //intento de acceso a la zona critica
                turn = j;       // 2  //asignacion siguiente turno  
                while (flag[j] && turn == j) Thread.yield(); // 3 se espera la asignación del flag de acceso y el turno aplicado en memoria
                //acceso zona critica
                log(i+": in CS"+n);
                time=1000 * Math.random(); //tiempo ramdon
                log(i+":process in CS  "+time); //marca 
                sleep(time); // 4 //emulación de operaciones complejas en procesos reales

                log(i+": done CS"); // desbloqueo UNLOCK
                flag[i] = false; // 5 restablecimiento de estado de acceso
            }
        });
    }


    public static void main(String[] args) {
        try {
            log("Starting 2 processes (threads) ...");
            Thread p0 = process(0);
            Thread p1 = process(1);
            p0.start(); //inicio de proceso
            p1.start(); //inicio de proceso
            //p0.join();
            //p1.join();
        }
        catch (Exception e) {}
    }
    
    

    static void sleep(double t) {
        try { Thread.sleep((long)t); }
        catch (InterruptedException e) {}
    }

    static void log(String x) {
        System.out.println(x);
    }
}