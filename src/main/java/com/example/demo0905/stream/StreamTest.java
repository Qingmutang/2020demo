package com.example.demo0905.stream;

import com.example.demo0905.model.Student;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 这里是常用的部分stream使用，后面如果还有其他的再补充
 *
 * @author qinglin
 * @create 2020-09-05 09:13
 */
public class StreamTest {


    public static void main(String[] args) {
        StreamTest.testFilter();
    }

    public static void testFilter(){

        List<String> orgList = ImmutableList.of("aa", "bb", "cc");

        List<Student> studentList = ImmutableList.of(new Student("xiaoming","12345",12),
                new Student("xiaohong","12346",11),
                new Student("xiaohong","12344",18));



        /**
         * 1. filter的使用，从集合 [aa,bb,cc] 中过滤得到aa,或者bb，且返回第一个，如果没有返回null
         */
        String oneResult = orgList.stream()
                .filter(item -> StringUtils.equalsIgnoreCase(item,"aa")||StringUtils.equalsIgnoreCase(item,"bb"))
                .findFirst()
                .orElse(null);

        System.out.println("1:"+oneResult);

        /**
         * 2. filter的使用，从集合 [aa,bb,cc] 中过滤得到aa,或者bb，且返回集合
         */

        List<String> twoResult = orgList.stream()
                .filter(item -> StringUtils.equalsIgnoreCase(item,"aa")||StringUtils.equalsIgnoreCase(item,"bb"))
                .collect(toList());

        System.out.println("2:"+twoResult);

        /**
         * 3. anyMatch ,从集合[aa,bb,cc]中找到是否存在任意一个 dd, 有则返回true，没有返回false
         */


        boolean threeResult = orgList.stream()
                .anyMatch(item -> StringUtils.equalsIgnoreCase(item, "aa") || StringUtils.equalsIgnoreCase(item, "bb"));

        System.out.println("3:"+threeResult);


        /**
         * 4. 从集合 对象Student中，取出code，作为集合，用来后续处理
         */

        List<String> fourResult = studentList.stream()
                .map(item -> item.getCode())
                .collect(toList());

        System.out.println("4:"+fourResult);

        /**
         * 5. 从集合 对象Student中，取出name，且去重 作为集合，用来后续处理  ==》因为取出的是name，可能会有重复的
         */

        List<String> fiveResult = studentList.stream()
                .map(item -> item.getName())
                .distinct()
                .filter(StringUtils::isNotBlank)
                .collect(toList());

        System.out.println("5:"+fiveResult);


        /**
         * 6. 集合，对象Student中，按照age，然后按照name，排序 升序，年级由小到大，名字按字母顺序
         */

        List<Student> sixResult = studentList.stream()
                .sorted((t1, t2) -> {

                    if(t1.getAge()==t2.getAge()){

                        return t1.getName().compareTo(t2.getName());
                    }else {
                        return t1.getAge()-t2.getAge();
                    }

                }).collect(toList());

        System.out.println("6:"+sixResult);

    }
}
