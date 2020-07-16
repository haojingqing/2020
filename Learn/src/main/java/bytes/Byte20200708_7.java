package bytes;

/**
 * @author jingqing
 */
public class Byte20200708_7 {

//    整数反转
//    给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
//
//    示例 1:
//
//    输入: 123
//    输出: 321
//    示例 2:
//
//    输入: -123
//    输出: -321
//    示例 3:

//    输入: 120
//    输出: 21


    public static void main(String[] args) {
        System.out.println(reverseRight(-123));
    }

    public static int reverseSelf(int x){

        return 0;
    }


    public static int reverseRight(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            // 整数边界判断
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)) {
                rev = 0;
                break;
            } else if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10)) {
                rev = 0;
                break;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

}
