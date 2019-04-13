package cn.dazhiyy.concurrent.jdk8;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author dazhi
 * @projectName concurrent-start
 * @packageName cn.dazhiyy.concurrent.jdk8
 * @className Jdk
 * @description TODO
 * @date 2019/3/26 14:56
 */
public class Jdk {

    List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);

    public static void main(String[] args) {
        Jdk jdk = new Jdk();
        jdk.testLambda();
    }

    // 遍历
    public void testLambda(){
        list.forEach(System.out::println);
        list.forEach(e -> System.out.println("方式二："+e));
    }


}
