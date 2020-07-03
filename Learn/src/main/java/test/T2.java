package test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jingqing
 */
public class T2 {

    public static void main(String[] args) {
        //System.out.println(factories(3));
        //System.out.println(f(5));
        //hanNuoTower(2, 'A', 'B', 'C');
//        Map map=new HashMap<>();
//        map.put(null,null);
//        System.out.println(map.toString());
        HashMap map=new HashMap();
        map.put(1,1);
        map.put(1,2);
        System.out.println(map.toString());
    }


    // 汉诺塔问题
    public static void hanNuoTower(int level, char from, char inner, char to) {
        if (level == 1) {
            System.out.println("从" + from + "移动盘子" + level + "号到" + to);
        } else {
            hanNuoTower(level - 1, from, to, inner);
            System.out.println("------从" + from + "移动盘子" + level + "号到" + to);
            hanNuoTower(level - 1, inner, from, to);
        }
    }

    // 斐波那契数列
    public static int f(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return f(n - 1) + f(n - 2);
        }
    }

    // 阶乘
    public static int factories(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factories(n - 1);
        }
    }
}
