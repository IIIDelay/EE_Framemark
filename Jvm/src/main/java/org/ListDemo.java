package org;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iiidev.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ListDemo
 *
 * @author IIIDelay
 * @createTime 2023年03月15日 16:23:00
 */
public class ListDemo {
    public static void main(String[] args) {
        ArrayList<User> users = Lists.newArrayList(new User("aa", 11, hobbies()), new User("bb", 22, hobbies()));
        ArrayList<User> users1 = Lists.newArrayList(new User("aa", 11, hobbies()), new User("bb", 22, hobbies()));
        ArrayList<User> users2 = Lists.newArrayList(new User("aa", 11, hobbies()), new User("bb", 22, hobbies()));
        ArrayList<ArrayList<User>> in = Lists.newArrayList(users, users1, users2);
        String s = JsonUtil.objToStr(in);
        System.out.println("s = " + s);

        // key不重复不需要分组，两个toMap
        Map<String, Map<String, Hobby>> collect = users.stream().collect(Collectors.toMap(User::getName,
                user -> user.getHobbies().stream().collect(Collectors.toMap(Hobby::getHName, Function.identity()))));
        collect.forEach((k, v) -> {
            System.out.println(k + " - " + v);
            v.forEach((k1, v1) -> System.out.println(k1 + " = " + v1));
        });

        // key重复走group
        Map<String, Map<Integer, User>> collect1 = users.stream().collect(Collectors.groupingBy(User::getName,
                Collectors.toMap(User::getAge, Function.identity())));
    }

    private static List<Hobby> hobbies() {
        ArrayList<Hobby> hobbies = Lists.newArrayList(new Hobby("XX", 11), new Hobby("YY", 22));
        return hobbies;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
    private String name;
    private Integer age;
    List<Hobby> hobbies;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Hobby {
    private String hName;
    private Integer length;
}

