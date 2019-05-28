package validator;

import java.lang.annotation.Annotation;

public interface PojoValidator {
	public void validate(Object fieldValue, Annotation annotation);
}
