import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        int f = 10;
        int h = f/2;
        int[] arr = new int[f];
        int[] a1 = new int[h];
        int[] a2 = new int[h];
        for (int i = 0; i <arr.length ; i++) {
            arr[i]= i;

        }
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            arr[i]=arr[i]*(i / 2);
        }
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            arr[i]=i;
        }

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        System.out.println();
        System.out.println(Arrays.toString(a1));
        System.out.println();
        System.out.println(Arrays.toString(a2));
        for (int i = 0; i < a1.length; i++) {
            a1[i]=a1[i]*(i / 2);
        }
        for (int i = 0; i < a2.length; i++) {
            a2[i]=a2[i]*(i / 2);
        }
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println();
        System.out.println(Arrays.toString(arr));
    }
    
}
