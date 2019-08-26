package annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
public @interface SearchFieldFromReferenceTable {
	Class<?> entityClass();
	String operator();
	String entityColumnName();
	String fixedCondition() default "";
}
