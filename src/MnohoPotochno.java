/*Если я правильно понял то задача дает понять преимущество поточного программирование.,
то есть если взять одну операцию и попробывать ее потоками и без какой получится результат.
но так как для этого должны получится одинаковые значения в ячейках конечного массива.
то формула которая дана в методичке не подходит во все методы
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
так как в ней берется число итерации(i)!!!
тогда я создал

* */

public class MnohoPotochno {
   private static final int size = 10000000;
   private static final int h = size / 2;
   private float[] arr = new float[size];
   private static int chislo1 = 9999999;
    private static int chislo2 = 4999999;

    public static void main(String[] args) {
        MnohoPotochno mnohoPotochnovtor = new MnohoPotochno();
        for (int i = 1; i <=5 ; i++) {                     //Proverka 5 raz
            System.out.println("Popitka "+i);
            mnohoPotochnovtor.pervi();                      // pervi variant
            System.out.println(" Kontrolnie chisla "+mnohoPotochnovtor.arr[chislo1]+" "+mnohoPotochnovtor.arr[chislo2]);
            // pechat chisla
            mnohoPotochnovtor.removemass();                    //ochistka massiva
            System.out.println("Kontrolnnie chisla "+mnohoPotochnovtor.arr[chislo1]+" "+mnohoPotochnovtor.arr[chislo2]+" Posle oshistki");//dli naglidnosti
            mnohoPotochnovtor.vtoroi();                       //vtoroi variant s potokami
            System.out.println(" Kontrolnie chisla "+mnohoPotochnovtor.arr[chislo1]+" "+mnohoPotochnovtor.arr[chislo2]);
            System.out.println();
        }




    }
    private void pervi(){
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.print("Bez potoka "+(System.currentTimeMillis() - a)+" Milisec");

    }
    private void vtoroi(){
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=1;
        }

        long a = System.currentTimeMillis();
        float[] a1 = new float[size];
        float[] a2 = new float[size];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, h, h);

        Runnable potokPer = () -> {
            for (int i = 0; i < h; i++) {
                a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }

        };
        Runnable potokVtor = () -> {
            for (int i = h; i < arr.length; i++) {
                a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }

        };
       Thread threadPer = new Thread(potokPer);
       Thread threadDva = new Thread(potokVtor);
       threadPer.start();
       threadDva.start();


        try {
            threadPer.join();
            threadDva.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, h, arr, h, h);
        System.out.print("Potochno "+(System.currentTimeMillis() - a)+" Milisec");


    }
    private void removemass(){
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=0;
        }
    }




}
