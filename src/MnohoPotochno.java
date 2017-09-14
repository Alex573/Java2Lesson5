/*
*
*
*
*
*
* */

public class MnohoPotochno {
    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];

    public static void main(String[] args) {
        MnohoPotochno mnohoPotochnovtor = new MnohoPotochno();
        mnohoPotochnovtor.pervi();
        System.out.println(mnohoPotochnovtor.arr[4999999]);
        mnohoPotochnovtor.removemass();
        System.out.println(mnohoPotochnovtor.arr[4999999]);
        mnohoPotochnovtor.vtoroi();
        System.out.println(mnohoPotochnovtor.arr[4999999]);



    }
    public void pervi(){
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println((System.currentTimeMillis() - a)+" Milisec");

    }
    public void vtoroi(){
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=1;
        }

        long a = System.currentTimeMillis();
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Runnable potokPer = () -> {
            for (int i = 0; i < a1.length; i++) {
                a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }

        };
        Runnable potokVtor = () -> {
            for (int i = 0; i < a2.length; i++) {
                a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }

        };
       Thread threadper = new Thread(potokPer);
       Thread threaddva = new Thread(potokVtor);
       threadper.start();
       threaddva.start();
        do {
            System.out.print("");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                System.out.println("Мain thread is interrupted.");
            }
        } while (threadper.isAlive() ||
                threadper.isAlive());



        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println((System.currentTimeMillis() - a)+" Milisec");


    }
    void removemass(){
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=0;
        }
    }




}
