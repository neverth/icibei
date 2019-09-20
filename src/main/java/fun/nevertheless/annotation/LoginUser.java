package fun.nevertheless.annotation;

import java.lang.annotation.*;



@Target(value = {
        ElementType.METHOD,
        ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginUser {

}
