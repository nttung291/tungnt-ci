import java.util.ArrayList;

/**
 * Created by Nttung PC on 7/4/2017.
 */
public class Program {
    public static void main(String[] args) {
        float a=1.0f;
        float b=-2;
        float c=2.0f;
        float delta;
        float kq1,kq2;
        delta = b*b-4*a*c;
        float mau = 2*a;
        if(delta>0){
            float d = (float)Math.sqrt(delta);
            System.out.println("Phuong trinh co 2 nghiem: ");
            System.out.println((-b + d)/mau);
            System.out.println((-b - d)/mau);
        }
        else if(delta==0){
            System.out.println(String.format("Phuong trinh co nghiem kep: x =%s", -b/mau));
        }
        else{
            System.out.println("VO nghiem");
        }
        int[] arr = {1,3,4};
        for (int x : arr){
            System.out.println(x);
        }
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(11);
        numbers.add(3);
        numbers.add(5);
        numbers.add(5);
        numbers.add(6);
        for (int i = 0; i < numbers.size(); i++){
            int number = numbers.get(i);
            System.out.println(number);
        }
    }
}
